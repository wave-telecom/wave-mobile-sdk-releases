# Callbacks

`RenderBlock` entrega eventos al host mediante `onEvent`.

## Modelo de eventos

```kotlin
sealed class SDKEvent {
    data class Error(
        val componentId: String,
        val code: String,
        val message: String,
    ) : SDKEvent()

    data class ComponentLoaded(
        val componentId: String,
    ) : SDKEvent()

    data class Callback(
        val type: String,
        val payload: String,
        val origin: String,
    ) : SDKEvent()
}
```

## Tipos de callback observados

- `RENDER_BLOCK_NAVIGATE`
- `RENDER_BLOCK_ERROR`
- `RENDER_BLOCK_CLICK`

## Manejo recomendado

```kotlin
onEvent = { event ->
    when (event) {
        is SDKEvent.ComponentLoaded -> {
            // bloque listo
        }

        is SDKEvent.Error -> {
            // error de bloque
        }

        is SDKEvent.Callback -> {
            when (event.type) {
                "RENDER_BLOCK_NAVIGATE" -> handleNavigation(event.payload)
                "RENDER_BLOCK_ERROR" -> handleBlockError(event.payload)
                "RENDER_BLOCK_CLICK" -> handleAction(event.payload)
            }
        }
    }
}
```

## Helpers mínimos de payload

```kotlin
private fun extractNextComponentId(payload: String): String? {
    val regex = Regex("\"nextComponentId\"\\s*:\\s*\"([^\"]+)\"")
    return regex.find(payload)?.groupValues?.getOrNull(1)
}

private fun isBackAction(payload: String): Boolean {
    return payload.contains("\"type\":\"back\"") || payload.contains("\"type\": \"back\"")
}
```

## Acción esperada del host

- `ComponentLoaded`: confirmar estado de carga.
- `Error`: mostrar fallback o reintento.
- `RENDER_BLOCK_NAVIGATE`: resolver transición.
- `RENDER_BLOCK_ERROR`: tratar error funcional del flujo.
- `RENDER_BLOCK_CLICK`: ejecutar acción del host asociada al flujo.
