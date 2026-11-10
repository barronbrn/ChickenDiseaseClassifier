package com.example.chickendiseaseclassifier

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

data class ClassificationResult(
    val label: String,
    val confidence: Float
)

class ChickenClassifier(
    private val context: Context,
    private val modelFileName: String = "model.tflite",
    private val labelFileName: String = "labels.txt"
) {
    private var interpreter: Interpreter? = null
    private var labels: List<String> = emptyList()
    private var inputImageWidth: Int = 224
    private var inputImageHeight: Int = 224
    private var isModelQuantized: Boolean = false

    init {
        try {
            val modelBuffer = loadModelFile(context, modelFileName)
            interpreter = Interpreter(modelBuffer)
            labels = loadLabels(context, labelFileName)

            val inputTensor = interpreter?.getInputTensor(0)
            val inputShape = inputTensor?.shape()
            if (inputShape != null && inputShape.size >= 3) {
                inputImageHeight = inputShape[1]
                inputImageWidth = inputShape[2]
            }

            val dataType = inputTensor?.dataType()
            isModelQuantized = dataType == org.tensorflow.lite.DataType.UINT8
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadModelFile(context: Context, modelName: String): ByteBuffer {
        val assetFileDescriptor = context.assets.openFd(modelName)
        val inputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun loadLabels(context: Context, labelName: String): List<String> {
        return try {
            context.assets.open(labelName).bufferedReader().readLines().filter { it.isNotBlank() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun classify(bitmap: Bitmap): ClassificationResult {
        val interpreter = this.interpreter ?: return ClassificationResult("Model belum dimuat", 0f)
        if (labels.isEmpty()) return ClassificationResult("Label belum dimuat", 0f)

        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, inputImageWidth, inputImageHeight, true)
        val byteBuffer = convertBitmapToByteBuffer(resizedBitmap)

        val outputArray = Array(1) { FloatArray(labels.size) }
        interpreter.run(byteBuffer, outputArray)

        val probabilities = outputArray[0]
        var maxIndex = 0
        var maxConfidence = 0f

        for (i in probabilities.indices) {
            if (probabilities[i] > maxConfidence) {
                maxConfidence = probabilities[i]
                maxIndex = i
            }
        }

        val label = if (maxIndex in labels.indices) labels[maxIndex] else "Tidak diketahui"
        return ClassificationResult(label, maxConfidence)
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val typeSize = if (isModelQuantized) 1 else 4
        val byteBuffer = ByteBuffer.allocateDirect(typeSize * 1 * inputImageHeight * inputImageWidth * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(inputImageWidth * inputImageHeight)
        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        var pixel = 0
        for (i in 0 until inputImageHeight) {
            for (j in 0 until inputImageWidth) {
                val val_ = intValues[pixel++]
                if (isModelQuantized) {
                    byteBuffer.put(((val_ shr 16) and 0xFF).toByte())
                    byteBuffer.put(((val_ shr 8) and 0xFF).toByte())
                    byteBuffer.put((val_ and 0xFF).toByte())
                } else {
                    byteBuffer.putFloat(((val_ shr 16) and 0xFF) / 255.0f)
                    byteBuffer.putFloat(((val_ shr 8) and 0xFF) / 255.0f)
                    byteBuffer.putFloat((val_ and 0xFF) / 255.0f)
                }
            }
        }
        return byteBuffer
    }

    fun close() {
        interpreter?.close()
        interpreter = null
    }
}
