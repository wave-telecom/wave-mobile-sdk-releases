# Instalación

## Distribución

El SDK se distribuye a través de un repositorio Maven público en GitHub Pages, compatible con Android e iOS mediante Kotlin Multiplatform.

## Requisitos

| Dependencia | Versión mínima |
| --- | --- |
| Kotlin | `>= 2.0` |
| Compose Multiplatform | `>= 1.7` |
| Android minSdk | `24` |
| iOS | `16.0+` |
| Gradle | `8.x` |

## Agregar repositorio

En el archivo `settings.gradle.kts` del proyecto:

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

## Agregar dependencia

Reemplaza `<version>` por la versión que deseas utilizar. La versión más reciente validada en esta guía es `0.5.3`.

En el módulo compartido (`shared/build.gradle.kts`):

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("br.com.wave:flow-wrapper-kmp:0.5.3")
        }
    }
}
```

## Permiso de Internet — solo Android

Agrega lo siguiente en el `AndroidManifest.xml` de tu app Android:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

Esto es necesario porque el SDK utiliza una `WebView` para renderizar los componentes Wave.

En iOS no se requiere ninguna configuración equivalente en la integración validada.

## Imports principales

```kotlin
import br.com.wave.flow_wrapper_kmp.FlowWrapper
import br.com.wave.flow_wrapper_kmp.RenderBlock
import br.com.wave.flow_wrapper_kmp.SDKEvent
```

