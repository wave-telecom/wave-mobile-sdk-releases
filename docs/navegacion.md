# Navegación

La navegación es controlada por la aplicación host a partir de callbacks de la SDK.

## Evento de navegación

El host recibe navegación mediante `SDKEvent.Callback`.

```kotlin
if (event is SDKEvent.Callback && event.type == "RENDER_BLOCK_NAVIGATE") {
    // Resolver la acción desde event.payload
}
```

En el runtime actual, `event.payload` llega como JSON string con este formato:

```json
{
  "type": "RENDER_BLOCK_NAVIGATE",
  "payload": {
    "type": "navigation",
    "nextComponentId": "catalog-1"
  }
}
```

Campos mínimos a considerar:

- `payload.type`: `navigation`, `back`, `close`, `external`.
- destino interno: `nextComponentId` (también pueden llegar `targetComponentId` o `destinationComponentId`).
- destino externo: `url` (también puede llegar `externalUrl`).

## Patrón recomendado

1. Renderiza un `flowId` de entrada.
2. Cuando llegue una navegación, empuja un destino en tu stack del host.
3. Renderiza ese destino con `componentId`.
4. Permite volver al estado anterior removiendo el tope del stack.

## Ejemplo base

```kotlin
val stack = remember { mutableStateListOf<String>() }
val currentComponentId = stack.lastOrNull()

if (currentComponentId == null) {
    RenderBlock(
        flowId = "YOUR_FLOW_ID",
        onEvent = { event ->
            if (event is SDKEvent.Callback) {
                val action = resolveNavigationAction(event.payload) // helper del host
                when (action) {
                    "navigation" -> pushFromPayload(event.payload)
                    "back", "close" -> stack.removeLastOrNull()
                    "external" -> openExternalFromPayload(event.payload)
                }
            }
        },
    )
} else {
    RenderBlock(
        componentId = currentComponentId,
        onEvent = { event ->
            if (event is SDKEvent.Callback && isBackOrClose(event.payload)) { // helper del host
                stack.removeLastOrNull()
            }
        },
    )
}
```

## Back del host (Android)

Para reproducir el comportamiento actual del app host, integra el back nativo con el stack:

```kotlin
BackHandler(enabled = stack.isNotEmpty()) {
    stack.removeLastOrNull()
}
```

Implementación canónica usada en el proyecto:
[sampleApp App.kt](../sampleApp/composeApp/src/commonMain/kotlin/br/com/wave/sample/App.kt)

## Nota de UX

Integrar el back del host al stack de navegación mejora la continuidad del flujo en móvil.
