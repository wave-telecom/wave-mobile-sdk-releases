# Setup

El SDK expone un único método de inicialización en el módulo shared. Debe llamarse una sola vez, antes de cualquier uso del composable `RenderBlock`.

## Inicialización del SDK

```kotlin
FlowWrapper.start(
    apiKey = "YOUR_API_KEY",
    msisdn = "+5511999999999",
)
```

## Parámetros

| Property | Type | Description |
| --- | --- | --- |
| `apiKey` | `String` | Authentication key provided by the Wave project administrator |
| `msisdn` | `String` | User phone number in E.164 format |

## Reglas de uso

- La inicialización debe ocurrir antes del primer `RenderBlock`
- Debe ejecutarse una sola vez por ciclo de vida del host
- Si `RenderBlock` se usa sin inicialización previa, el SDK falla por configuración faltante

## Ejemplo en Compose

```kotlin
LaunchedEffect(Unit) {
    FlowWrapper.start(
        apiKey = "YOUR_API_KEY",
        msisdn = "+5511999999999",
    )
}
```

## Seguridad

El host es responsable de proteger la `API Key` y de no imprimir valores sensibles en logs de producción.

## Nota sobre documentación previa

En versiones antiguas de la documentación se mostraba:

```kotlin
RenderBlockSDK.start(
    apiKey = "YOUR_API_KEY",
    token = userToken,
)
```

Ese contrato no corresponde a la API pública validada en `0.5.3`.

