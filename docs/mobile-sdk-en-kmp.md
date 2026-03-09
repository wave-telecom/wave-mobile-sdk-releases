# Mobile SDK en KMP

La Mobile SDK para Kotlin Multiplatform permite renderizar componentes Wave en Android e iOS utilizando un host Compose/KMP.

Esta documentación describe el contrato público validado para la versión `0.5.3`.

## Secciones

- [Instalación](./instalacion.md)
- [Autenticación](./autenticacion.md)
- [Setup](./setup.md)
- [Inicialización](./inicializacion.md)
- [Navegación](./navegacion.md)
- [Callbacks](./callbacks.md)

## Resumen

- Distribución vía Maven público en GitHub Pages
- Compatible con Android e iOS mediante Kotlin Multiplatform
- Inicialización única mediante `FlowWrapper.start(apiKey, msisdn)`
- Renderización mediante `RenderBlock(componentId = ...)` o `RenderBlock(flowId = ...)`
- Navegación delegada al host mediante `onEvent`
- Back nativo controlado por la aplicación host

## Contrato público actual

Imports principales:

```kotlin
import br.com.wave.flow_wrapper_kmp.FlowWrapper
import br.com.wave.flow_wrapper_kmp.RenderBlock
import br.com.wave.flow_wrapper_kmp.SDKEvent
```

Inicialización:

```kotlin
FlowWrapper.start(
    apiKey = "YOUR_API_KEY",
    msisdn = "+5511999999999",
)
```

Renderización:

```kotlin
RenderBlock(
    flowId = "subscription-management",
    onEvent = { event -> /* host */ },
)
```

## Notas de versión

Esta guía corrige referencias antiguas que no corresponden al artefacto publicado `br.com.wave:flow-wrapper-kmp:0.5.3`.

No forman parte del contrato público actual:

- `RenderBlockSDK.start(apiKey, token)`
- `SDKEvent.Click`
- `SDKEvent.Navigate`

