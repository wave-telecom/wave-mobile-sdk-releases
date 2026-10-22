# Wave Flows SDK (KMP)

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
>
> The supporting modules (`flows-sdk`, `core`, `runtime`, `composeblocks`, `webblocks`) are resolved transitively through Gradle module metadata — you do not need to declare them explicitly.

### 3. Android — Internet permission

Add the internet permission to your Android app's `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

## Usage

Initialize the SDK once (e.g. from `Application.onCreate()` on Android, or from your app entry point on iOS):

```kotlin
import br.com.wave.flow_wrapper_kmp.FlowWrapper

FlowWrapper.start(
    // Issued by your own backend. A JWT whose payload carries `base_url` (the BFF),
    // `env` (DEV/PRD, for the WebView host) and `externalCode` (the subscriber
    // identifier) — all three required.
    // Required, and never persisted: the SDK holds it in memory for the process.
    waveToken = "<wave token>",
)
```

Render a flow or component with the `RenderBlock` composable:

```kotlin
import br.com.wave.flow_wrapper_kmp.RenderBlock
import br.com.wave.flow_wrapper_kmp.SDKEvent

@Composable
fun MyScreen() {
    RenderBlock(
        componentId = "balance-summary-1",
        onEvent = { event ->
            when (event) {
                is SDKEvent.ComponentLoaded -> Unit
                is SDKEvent.Error -> Unit
                is SDKEvent.Callback -> Unit
                is SDKEvent.ActionTracked -> Unit
                SDKEvent.Unauthorized -> Unit
            }
        },
    )
}
```
