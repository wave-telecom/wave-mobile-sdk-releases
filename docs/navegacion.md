# Navegación

La navegación es controlada por la aplicación host a partir de callbacks de la SDK.

## Evento de navegación

El host recibe navegación mediante `SDKEvent.Callback`.

```kotlin
if (event is SDKEvent.Callback && event.type == "RENDER_BLOCK_NAVIGATE") {
    // Resolver la acción desde event.payload
}
```

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
            if (event is SDKEvent.Callback && event.type == "RENDER_BLOCK_NAVIGATE") {
                val nextComponentId = extractNextComponentId(event.payload) // helper del host
                if (nextComponentId != null) stack += nextComponentId
            }
        },
    )
} else {
    RenderBlock(
        componentId = currentComponentId,
        onEvent = { event ->
            if (event is SDKEvent.Callback && isBackAction(event.payload)) { // helper del host
                stack.removeLastOrNull()
            }
        },
    )
}
```

## Nota de UX

Integrar el back del host al stack de navegación mejora la continuidad del flujo en móvil.
