# Setup

Implementa el composable `RenderBlock` en la pantalla que defina tu host, preferentemente ocupando toda el área disponible.

## Regla de orden

Inicializa la SDK primero y renderiza `RenderBlock` después.

```kotlin
var sdkStarted by remember { mutableStateOf(false) }

LaunchedEffect(Unit) {
    FlowWrapper.start(
        apiKey = "YOUR_API_KEY",
        msisdn = "+5215512345678",
    )
    sdkStarted = true
}

if (sdkStarted) {
    RenderBlock(
        flowId = "YOUR_FLOW_ID",
        modifier = Modifier.fillMaxSize(),
        onEvent = { event -> /* host */ },
    )
}
```

## Recomendaciones de host

- Mantener la inicialización en un único punto por ciclo de vida de la app.
- Manejar transiciones y callbacks en el host.
- Usar un contenedor full-screen para el bloque cuando el flujo sea pantalla principal.
