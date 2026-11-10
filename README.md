# 🐔 Chicken Disease Classifier (Deteksi Penyakit Ayam via AI)

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org)
[![TensorFlow Lite](https://img.shields.io/badge/ML-TensorFlow%20Lite-orange.svg)](https://www.tensorflow.org/lite)
[![Material 3](https://img.shields.io/badge/UI-Material%203-blue.svg)](https://m3.material.io)

Aplikasi Android modern berbasis **Artificial Intelligence (Deep Learning)** yang dirancang untuk membantu para peternak mendeteksi kesehatan ayam secara dini melalui analisis visual pada kotoran ayam menggunakan model **TensorFlow Lite (TFLite)**.

---

## ✨ Fitur Utama

- 🚀 **Splash Screen Modern**: Halaman sambutan elegan dengan logo dan indikator *loading* saat aplikasi dibuka.
- 🎨 **Antarmuka Material Design 3**: Desain UI yang bersih, responsif, dan mendukung *Light & Dark Mode* otomatis.
- 📷 **Kamera & Galeri**: Pengambilan foto langsung via kamera perangkat atau pemilihan dari galeri.
- 🤖 **Klasifikasi TFLite *On-Device***: Inferensi Machine Learning berjalan secara lokal di perangkat tanpa koneksi internet (*offline-capable*).
- 🛡️ **Validasi Gambar Pintar (*Confidence Threshold*)**: Menolak gambar yang tidak valid (seperti selfie atau pemandangan) dengan ambang batas keyakinan minimal 60%.
- 📱 **Desain Adaptif (*Edge-to-Edge*)**: Tampilan layar penuh modern yang menyesuaikan dengan *system bars* secara mulus.

---

## 🛠️ Teknologi & Arsitektur

- **Bahasa Pemrograman**: 100% Kotlin
- **UI Toolkit**: XML Layout, Material Design 3 Components, & ConstraintLayout
- **Machine Learning**: TensorFlow Lite (`org.tensorflow:tensorflow-lite`)
- **Konkurensi**: Kotlin Coroutines & Lifecycle Scope
- **Kompatibilitas**: Android 7.0 (API 24) hingga Android terbaru (API 37+)

---

## 📂 Struktur Proyek

```text
ChickenDiseaseClassifier/
│
├── app/
│   ├── src/main/
│   │   ├── assets/
│   │   │   ├── model.tflite       # File model Deep Learning TFLite
│   │   │   └── labels.txt         # Daftar kelas penyakit (Coccidiosis, Salmonella, dll.)
│   │   ├── java/com/example/chickendiseaseclassifier/
│   │   │   ├── MainActivity.kt    # Aktivitas utama & logika klasifikasi
│   │   │   └── SplashActivity.kt  # Aktivitas pembuka (Splash Screen)
│   │   ├── res/
│   │   │   ├── layout/            # Layout XML
│   │   │   └── values/            # Tema, warna, dan strings
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
└── README.md
```

---

## ⚙️ Konfigurasi Model TFLite

1. Siapkan file model berformat `.tflite`.
2. Ubah nama file model menjadi **`model.tflite`**.
3. Letakkan file tersebut ke dalam direktori:
   ```text
   app/src/main/assets/model.tflite
   ```
4. Pastikan daftar kelas penyakit Anda diatur di:
   ```text
   app/src/main/assets/labels.txt
   ```
   *(Setiap baris mewakili satu nama kelas, contoh: Coccidiosis, New Castle Disease, Salmonella, Healthy).*

---

## 🚀 Cara Menjalankan Proyek

1. Clone atau unduh repositori ini.
2. Buka **Android Studio** (versi Hedgehog / Iguana / Jellyfish atau yang lebih baru).
3. Pilih **Open an Existing Project** dan arahkan ke folder proyek ini.
4. Tunggu proses **Gradle Sync** selesai.
5. Sambungkan perangkat Android fisik atau jalankan **Emulator** (API 24+).
6. Klik tombol **Run (▶)** untuk mengompilasi dan memasang aplikasi.

---

## 📄 Lisensi

Proyek ini bersifat *open-source* dan bebas digunakan untuk keperluan edukasi, penelitian, maupun pengembangan aplikasi peternakan pintar.
