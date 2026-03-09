# Instalación

## Requisitos

| Dependencia | Versión mínima |
| --- | --- |
| Kotlin | `>= 2.0` |
| Compose Multiplatform | `>= 1.7` |
| Android minSdk | `24` |
| iOS | `16.0+` |
| Gradle | `8.x` |

## Repositorio Maven

En `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://wave-telecom.github.io/wave-mobile-sdk-releases")
        }
    }
}
```

## Dependencia

En el módulo compartido:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("br.com.wave:flow-wrapper-kmp:0.5.3")
        }
    }
}
```

## AndroidManifest (Android)

Agrega permiso de red:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Imports

```kotlin
import br.com.wave.flow_wrapper_kmp.FlowWrapper
import br.com.wave.flow_wrapper_kmp.RenderBlock
import br.com.wave.flow_wrapper_kmp.SDKEvent
```
