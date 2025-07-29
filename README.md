# 🏍️ KMP Clothing Store

A simple cross-platform **clothing store app** built with **Kotlin Multiplatform (KMP)** and **JetBrains Compose Multiplatform UI**.
The app fetches product data from a REST API and displays it using a shared codebase across multiple platforms.

---

## 🚀 Supported Platforms

This project builds and runs on:

* ✅ **Android phones**
* ✅ **Android smartwatches (WearOS)**
* ✅ **iOS (iPhone / iPad)**
* ✅ **Windows desktop**
* ✅ **Web (WASM)**

---

## 📱 Screenshots

*Coming soon...*

---

## 🧹 Tech Stack & Libraries

### ✅ Kotlin Multiplatform + Compose

* [`kotlin`](https://kotlinlang.org/) (`2.2.0`)
* [`compose-multiplatform`](https://www.jetbrains.com/lp/compose-multiplatform/) (`1.8.2`)
* [`compose-material`](https://developer.android.com/jetpack/compose/components/material) (`1.2.1`)
* [`navigation-compose`](https://developer.android.com/jetpack/compose/navigation) (`2.9.0-beta01`)
* Hot reload: `compose-hot-reload` (`1.0.0-alpha11`)

### ✅ Networking

* [`ktor`](https://ktor.io/) (`3.1.3`)

  * `ktor-client-core`
  * `ktor-client-okhttp`
  * `ktor-client-content-negotiation`
  * `ktor-serialization-kotlinx-json`

### ✅ Image Loading

* [`coil`](https://github.com/coil-kt/coil) (`3.2.0`)

  * `coil-compose`
  * `coil-network-ktor3`

### ✅ Dependency Injection

* [`Koin`](https://insert-koin.io/) (`4.1.0-Beta11`)

  * `koin-core`
  * `koin-compose-viewmodel`

### ✅ State & Lifecycle

* `androidx.lifecycle-viewmodel` (`2.9.1`)
* `androidx.lifecycle-runtime-compose`

### ✅ Platform-Specific Libraries

* `androidx.activity-compose`
* `androidx.core-splashscreen`
* `play-services-wearable` (`19.0.0`) (for WearOS)
* `androidx.wear.compose.*` (for watch UI)

---

## 📁 Project Structure

```
composeApp/
├── androidMain/       # Android-specific implementations
├── iosMain/           # iOS-specific implementations
├── desktopMain/       # Windows (desktop) UI
├── wasmJsMain/        # Web (WASM) UI
└── commonMain/        # Shared business logic, models, UI, etc.
```

---

## 🥪 Features

* 📦 Fetch and display products from [FakeStoreAPI](https://fakestoreapi.com/)
* 📸 Product image rendering via Coil
* 🔍 Product details screen with navigation
* 🔀 Shared ViewModels across platforms
* 🫠 WearOS support with dedicated UI layout
* 🌐 WebAssembly build using `wasmJsBrowserDevelopmentRun`

---

## 🔧 Build Instructions

### Android / Android Watch:

```bash
./gradlew :androidApp:installDebug
```

### Desktop (Windows):

```bash
./gradlew :composeApp:run
```

### iOS:

Open with Xcode and run the `iosApp` target.

### Web (WASM):

```bash
./gradlew wasmJsBrowserDevelopmentRun
```

Then open the served link in your browser (e.g. [http://localhost:8080](http://localhost:8080)).

---

## 🤔 Watch Detection

Device-specific logic is handled using Kotlin's `expect`/`actual` mechanism.
For example, detecting if the app is running on a smartwatch is done using:

```kotlin
expect fun isWatchDevice(): Boolean
```

Implemented separately for each platform.

---

## 💡 Notes

* The app uses Koin for dependency injection and Compose Multiplatform for declarative UI across platforms.
* WearOS uses a simplified UI, optimized for small screen sizes.
* The business logic is fully shared; only the UI layer is platform-specific.

---

## 📄 License

MIT License – feel free to fork, use, and contribute!

---

## 👤 Author

Developed by \[Moslem Khanbeiki]
GitHub: [github.com/yourusername](https://github.com/skhanbeiki/)
