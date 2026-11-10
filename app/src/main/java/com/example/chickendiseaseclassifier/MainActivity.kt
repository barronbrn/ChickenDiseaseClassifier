package com.example.chickendiseaseclassifier

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var ivPreview: ImageView
    private lateinit var btnCamera: Button
    private lateinit var btnGallery: Button
    private lateinit var tvResult: TextView
    private lateinit var tvConfidence: TextView

    private lateinit var classifier: ChickenClassifier

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val bitmap = getBitmapFromUri(it)
            if (bitmap != null) {
                ivPreview.setImageBitmap(bitmap)
                runClassification(bitmap)
            } else {
                Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        bitmap?.let {
            ivPreview.setImageBitmap(it)
            runClassification(it)
        } ?: Toast.makeText(this, "Kamera dibatalkan atau gagal", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivPreview = findViewById(R.id.ivPreview)
        btnCamera = findViewById(R.id.btnCamera)
        btnGallery = findViewById(R.id.btnGallery)
        tvResult = findViewById(R.id.tvResult)
        tvConfidence = findViewById(R.id.tvConfidence)

        // Initialize classifier (expects model.tflite and labels.txt in assets)
        classifier = ChickenClassifier(this)

        btnGallery.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        btnCamera.setOnClickListener {
            cameraLauncher.launch(null)
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(contentResolver, uri)
                ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
                    decoder.isMutableRequired = true
                }
            } else {
                @Suppress("DEPRECATION")
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun runClassification(bitmap: Bitmap) {
        val result = classifier.classify(bitmap)
        val threshold = 0.60f // 60% minimum confidence threshold
        val confidencePercent = result.confidence * 100f

        if (result.confidence < threshold) {
            tvResult.text = "⚠️ Gambar Tidak Valid"
            tvConfidence.text = String.format(Locale.getDefault(), "Keyakinan terlalu rendah (%.2f%%).\nMohon unggah foto kotoran ayam yang jelas.", confidencePercent)
            Toast.makeText(this, "Foto tidak dikenali sebagai kotoran ayam!", Toast.LENGTH_LONG).show()
        } else {
            tvResult.text = "Hasil: ${result.label}"
            tvConfidence.text = String.format(Locale.getDefault(), "Tingkat Keyakinan: %.2f%%", confidencePercent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        classifier.close()
    }
}
