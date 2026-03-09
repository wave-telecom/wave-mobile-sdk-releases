# Navegación

La estrategia de navegación es híbrida: la lógica de transición de pantallas se delega a la aplicación host, mientras que el SDK solo gestiona la renderización del contract.

- Los componentes inline permanecen en la pantalla actual
- Los componentes full-screen emiten un evento al host
- El host redirige a una ruta genérica de canvas
- El canvas instancia un nuevo `RenderBlock` con el `componentId` de destino

El SDK nunca navega de forma autónoma. Toda la navegación se comunica mediante callbacks para que el host pueda gestionar el navigation stack.

## Contrato público actual

En la versión `0.5.3` la navegación no llega como `SDKEvent.Navigate`.

Llega a través de:

```kotlin
SDKEvent.Callback(
    type = "...",
    payload = "...",
    origin = "...",
)
```

Para navegación interna, el evento observado en runtime fue:

```text
type = RENDER_BLOCK_NAVIGATE
payload = {"type":"RENDER_BLOCK_NAVIGATE","payload":{"type":"navigation","nextComponentId":"catalog-1"}}
```

## Android — Host con canvas genérico

```kotlin
val canvasStack = remember { mutableStateListOf<String>() }
val currentCanvas = canvasStack.lastOrNull()

if (currentCanvas == null) {
    RenderBlock(
        flowId = "subscription-management",
        onEvent = { event ->
            when (event) {
                is SDKEvent.Callback -> {
                    if (event.type == "RENDER_BLOCK_NAVIGATE") {
                        val nextComponentId = extractNextComponentId(event.payload)
                        if (nextComponentId != null) {
                            canvasStack += nextComponentId
                        }
                    }
                }
                else -> Unit
            }
        }
    )
} else {
    RenderBlock(
        componentId = currentCanvas,
    )
}
```

## Back nativo

Cuando el host mantiene un canvas stack, el back nativo debe ser redirigido al stack del host:

- si hay pantallas apiladas, remover la última y volver al bloque anterior
- si no hay pantallas apiladas, mantener el comportamiento normal del sistema

Comportamiento validado en Android:

- home SDK -> callback de navegación -> canvas
- back nativo en canvas -> vuelve a la home SDK
- back nativo en home -> sale al launcher

## iOS

El mismo principio aplica en iOS: el host debe capturar el callback, hacer `push` de una pantalla canvas genérica y usar el stack nativo para volver cuando corresponda.

La responsabilidad del stack sigue siendo del host, no del SDK.

