# 🐔 Chicken Disease Classifier (Deteksi Penyakit Ayam via AI)

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org)
[![TensorFlow Lite](https://img.shields.io/badge/ML-TensorFlow%20Lite-orange.svg)](https://www.tensorflow.org/lite)
[![Material 3](https://img.shields.io/badge/UI-Material%203-blue.svg)](https://m3.material.io)

Aplikasi Android modern berbasis **Artificial Intelligence (Deep Learning)** yang dirancang untuk membantu para peternak mendeteksi kesehatan ayam secara dini melalui analisis visual pada kotoran ayam menggunakan model **TensorFlow Lite (TFLite)**.

---

## ✨ Fitur Utama

- 🚀 **Splash Screen Modern**: Halaman sambutan (*Splash Screen*) yang elegan dengan logo dan indikator *loading* saat aplikasi pertama kali dibuka.
- 🎨 **Antarmuka Material Design 3**: Desain UI yang bersih, responsif, dan otomatis mendukung *Light & Dark Mode*.
- 📷 **Kamera & Galeri In-App**: Kemudahan mengambil foto langsung menggunakan kamera perangkat atau memilih foto dari galeri.
- 🤖 **Klasifikasi TFLite *On-Device***: Proses inferensi Machine Learning berjalan secara lokal di perangkat tanpa memerlukan koneksi internet (*offline-capable*).
- 🛡️ **Validasi Gambar Pintar (*Confidence Threshold*)**: Fitur pengaman untuk menolak gambar yang tidak valid (seperti selfie, pemandangan, atau makanan) dengan ambang batas keyakinan minimal 60%.
- 📱 **Desain Adaptif (*Edge-to-Edge*)**: Mendukung tampilan layar penuh modern yang menyesuaikan dengan *system bars* secara mulus.

---

## 🛠️ Teknologi & Arsitektur

- **Bahasa Pemrograman**: 100% Kotlin
- **UI Toolkit**: XML Layout dengan Material Design 3 Components & ConstraintLayout
- **Machine Learning**: TensorFlow Lite (`org.tensorflow:tensorflow-lite`)
- **Konkurensi**: Kotlin Coroutines & Lifecycle Scope
- **Kompatibilitas**: Mendukung Android 7.0 (API 24) hingga Android versi terbaru (API 37+)

---

## 📂 Struktur Proyek

```text
ChickenDiseaseClassifier/
│
├── app/
│   ├── src/main/
│   │   ├── assets/
│   │   │   ├── model.tflite       # File model Deep Learning TFLite Anda
│   │   │   └── labels.txt         # Daftar kelas penyakit (Coccidiosis, New Castle Disease, dll.)
│   │   ├── java/com/example/chickendiseaseclassifier/
│   │   │   ├── MainActivity.kt    # Aktivitas utama & logika klasifikasi
│   │   │   └── SplashActivity.kt  # Aktivitas pembuka (Splash Screen)
│   │   ├── res/
│   │   │   ├── layout/            # Layout XML (activity_main.xml, activity_splash.xml)
│   │   │   └── values/            # Tema, warna, dan strings
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
└── README.md
```

---

## ⚙️ Cara Memasang Model TFLite Anda

1. Pastikan Anda memiliki file model berformat `.tflite`.
2. Ubah nama file model Anda menjadi **`model.tflite`**.
3. Letakkan file tersebut ke dalam direktori:
   ```text
   app/src/main/assets/model.tflite
   ```
4. Pastikan file daftar kelas penyakit Anda diatur di:
   ```text
   app/src/main/assets/labels.txt
   ```
   *(Setiap baris mewakili satu nama kelas, misal: Coccidiosis, New Castle Disease, Salmonella, Healthy).*

---

## 🚀 Cara Menjalankan Proyek

1. **Clone repositori ini**:
   ```bash
   git clone https://github.com/USERNAME/ChickenDiseaseClassifier.git
   ```
2. Buka **Android Studio** (versi Hedgehog / Iguana / Jellyfish atau yang lebih baru).
3. Pilih **Open an Existing Project** dan arahkan ke folder proyek ini.
4. Tunggu proses **Gradle Sync** selesai.
5. Sambungkan perangkat Android fisik Anda atau jalankan **Emulator** (disarankan API 24 ke atas).
6. Klik tombol **Run (▶)** untuk mengompilasi dan memasang aplikasi.

---

## 📌 Panduan Upload ke GitHub Anda

Jika Anda ingin mengunggah proyek ini ke repositori GitHub pribadi Anda, jalankan perintah berikut di terminal pada folder root proyek:

```bash
# 1. Inisialisasi git (jika belum)
git init

# 2. Tambahkan semua file proyek
git add .

# 3. Lakukan commit pertama
git commit -m "Initial commit: Chicken Disease Classifier with TFLite and Material 3"

# 4. Hubungkan ke repositori GitHub Anda
git remote add origin https://github.com/USERNAME/repository-name.git

# 5. Ubah branch utama ke main dan push ke GitHub
git branch -M main
git push -u origin main
```
*(Ganti `https://github.com/USERNAME/repository-name.git` dengan URL repositori GitHub Anda).*

---

## 📄 Lisensi

Proyek ini bersifat *open-source* dan bebas digunakan untuk keperluan edukasi, penelitian, maupun pengembangan aplikasi peternakan pintar.
