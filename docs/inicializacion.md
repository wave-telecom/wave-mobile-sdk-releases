# Inicialización

La inicialización funcional del flujo define qué componente será renderizado al inicio. El SDK admite dos escenarios:

- Con `componentId`: renderización directa de un componente conocido
- Con `flowId`: resolución dinámica del primer componente basada en el perfil del usuario

## Escenario 1 — Con Component ID

Utilizar cuando el identificador del componente es conocido en tiempo de ejecución:

```kotlin
@Composable
fun MyScreen() {
    RenderBlock(
        componentId = "balance-summary",
    )
}
```

El SDK llama a la API con el `componentId` proporcionado y renderiza el contract retornado.

## Escenario 2 — Con Flow ID

Utilizar cuando el componente inicial depende del perfil del usuario y necesita ser resuelto por backend:

```kotlin
@Composable
fun MyScreen() {
    RenderBlock(
        flowId = "subscription-management",
    )
}
```

En este escenario, el `flowId` define el punto de entrada del flujo y el backend resuelve el contenido inicial adecuado para el usuario autenticado.

## Cuándo usar Flow ID

Utiliza `flowId` cuando `componentId = null`, es decir, cuando el punto de entrada depende del perfil del usuario o de reglas de negocio definidas en servidor.

## Parámetros del composable RenderBlock

| Property | Type | Required | Description |
| --- | --- | --- | --- |
| `componentId` | `String?` | Conditional | ID of the component to render. Required when `flowId` is not provided |
| `flowId` | `String?` | Conditional | Flow ID. Used as fallback when `componentId` is `null` |
| `onEvent` | `((SDKEvent) -> Unit)?` | No | Callback to receive SDK events |
| `modifier` | `Modifier` | No | Standard Compose modifier for layout customization |

## Observaciones de runtime

- `flowId = "subscription-management"` renderizó una home interactiva validada en Android
- `componentId = "balance-summary"` cargó correctamente, pero en el escenario validado renderizó un placeholder textual

