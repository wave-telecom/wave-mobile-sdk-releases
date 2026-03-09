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

## Formato de payload observado

`SDKEvent.Callback.payload` es un JSON serializado en `String`.

Para navegación, el formato observado es:

```json
{
  "type": "RENDER_BLOCK_NAVIGATE",
  "payload": {
    "type": "navigation",
    "nextComponentId": "catalog-1"
  }
}
```

Reglas mínimas para parsear callbacks:

- Para navegación, parsea primero el objeto interno `payload`.
- Normaliza `event.type` (por ejemplo, `lowercase()`) antes de evaluar reglas.
- Usa `payload.type` para decidir acción del host.
- Para navegación interna, lee `nextComponentId` (fallback: `targetComponentId`, `destinationComponentId`).
- Para navegación externa, lee `url` (fallback: `externalUrl`).
- Si `payload.type` no está disponible para back/close, usa fallback por `event.type`.
- Para `RENDER_BLOCK_ERROR`, lee `code` y `name` del payload interno.

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
                "RENDER_BLOCK_ERROR" -> {
                    val callbackPayload = extractJsonObjectField(event.payload, "payload") ?: event.payload
                    val code = extractJsonStringField(callbackPayload, "code")
                    val name = extractJsonStringField(callbackPayload, "name")
                    handleBlockError(code, name, event.payload)
                }
                "RENDER_BLOCK_CLICK" -> handleAction(event.payload)
            }
        }
    }
}
```

Implementación canónica usada en el proyecto:
[sampleApp App.kt](../sampleApp/composeApp/src/commonMain/kotlin/br/com/wave/sample/App.kt)

## Acción esperada del host

- `ComponentLoaded`: confirmar estado de carga.
- `Error`: mostrar fallback o reintento.
- `RENDER_BLOCK_NAVIGATE`: resolver transición.
- `RENDER_BLOCK_ERROR`: tratar error funcional del flujo (ejemplo observado: `ADAPTER_NOT_FOUND`).
- `RENDER_BLOCK_CLICK`: ejecutar acción del host asociada al flujo.

Nota operativa:

- `RENDER_BLOCK_ERROR` (ejemplo observado: `ADAPTER_NOT_FOUND`) puede coexistir con eventos de navegación; no asumir por sí solo que la navegación del host falló.
- Mantén logging de `SDKEvent.Callback.type` y `payload` durante integración para validar comportamiento real en runtime.
