# Callbacks

El SDK expone un modelo de eventos unificado para ambas plataformas a través del callback `onEvent` en `RenderBlock`.

## Estructura del Event

La API pública actual expone:

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

## Eventos observados

### ComponentLoaded

Emitido cuando el componente fue cargado exitosamente.

### Error

Emitido por la SDK cuando ocurre un error asociado a un componente específico.

### Callback

Emitido por el bridge web/native para eventos funcionales del frontend.

Tipos observados:

- `RENDER_BLOCK_NAVIGATE`
- `RENDER_BLOCK_ERROR`
- `RENDER_BLOCK_CLICK`

Para `RENDER_BLOCK_NAVIGATE`, el payload observado fue:

```json
{
  "type": "RENDER_BLOCK_NAVIGATE",
  "payload": {
    "type": "navigation",
    "nextComponentId": "catalog-1"
  }
}
```

Subtipos observados dentro de `payload.payload.type`:

- `navigation`
- `back`
- `external`

## Consumo de eventos

```kotlin
@Composable
fun SubscriptionScreen() {
    RenderBlock(
        componentId = "subscription-detail-1",
        onEvent = { event ->
            when (event) {
                is SDKEvent.ComponentLoaded -> {
                    logger.info("Component loaded: ${event.componentId}")
                }

                is SDKEvent.Error -> {
                    logger.error(
                        "SDK Error [${event.componentId}]: ${event.message}"
                    )
                }

                is SDKEvent.Callback -> {
                    logger.info(
                        "SDK Callback [${event.type}]: ${event.payload}"
                    )
                }
            }
        }
    )
}
```

## Recomendación para navegación host-driven

Para navegación, el host debe interpretar `SDKEvent.Callback`:

```kotlin
if (event is SDKEvent.Callback &&
    event.type == "RENDER_BLOCK_NAVIGATE"
) {
    val nextComponentId = extractNextComponentId(event.payload)
    if (nextComponentId != null) {
        navController.navigate("canvas/$nextComponentId")
    }
}
```

## Manejo de errores y resiliencia

Los errores se manejan de manera individual por componente, asegurando que fallas aisladas no afecten la experiencia general:

- Falla de servicio: el bloque que falló puede ser reemplazado por un fallback definido por la plataforma
- Cache del BFF: puede mantenerse la última respuesta exitosa para escenarios de reintento
- Reintento: el usuario puede recargar el componente fallido sin afectar el resto de la pantalla

## Nota de compatibilidad

Las referencias antiguas a:

- `SDKEvent.Click`
- `SDKEvent.Navigate`

no corresponden al contrato público actual de la versión `0.5.3`.

