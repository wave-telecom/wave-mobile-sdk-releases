# Mobile SDK en KMP

La SDK permite integrar en una app KMP/Compose un bloque visual que ejecuta flujos de casos de uso y expone eventos para que la aplicación host controle transiciones y acciones.

Esta guía describe el contrato de integración para `br.com.wave:flow-wrapper-kmp:0.5.3`.

## Secciones

- [Instalación](./instalacion.md)
- [Autenticación](./autenticacion.md)
- [Setup](./setup.md)
- [Inicialización](./inicializacion.md)
- [Navegación](./navegacion.md)
- [Callbacks](./callbacks.md)

## Resumen de integración

1. Configura el repositorio Maven y la dependencia.
2. Inicializa la SDK con `FlowWrapper.start(apiKey, msisdn)`.
3. Renderiza `RenderBlock` (entrada recomendada: `flowId`).
4. Maneja `SDKEvent` en `onEvent`.
5. Implementa navegación del host a partir de callbacks.

## Imports principales

```kotlin
import br.com.wave.flow_wrapper_kmp.FlowWrapper
import br.com.wave.flow_wrapper_kmp.RenderBlock
import br.com.wave.flow_wrapper_kmp.SDKEvent
```
