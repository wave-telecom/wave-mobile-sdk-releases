# Wave Flow Wrapper KMP

A Kotlin Multiplatform SDK for rendering Wave flows and components inside native apps using Compose Multiplatform.

---

## Requirements

- Kotlin **2.0+**
- Compose Multiplatform **1.7+**
- Android **API 24+**

---

## Installation

### 1. Add the Maven repository

In your project's `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://wave-telecom.github.io/wave-mobile-sdk-releases") }
    }
}
```

### 2. Add the dependency

In your shared module's `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("br.com.wave:flow-wrapper-kmp:<version>")
        }
    }
}
```

> Replace `<version>` with the latest release available in this repository.

### 3. Android — Internet permission

Add the internet permission to your Android app's `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```
