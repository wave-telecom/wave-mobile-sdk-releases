# Inicialización

La entrada recomendada del flujo es por `flowId`.

## Entrada recomendada (`flowId`)

```kotlin
RenderBlock(
    flowId = "YOUR_FLOW_ID",
    modifier = Modifier.fillMaxSize(),
    onEvent = { event -> /* host */ },
)
```

Ejemplo usado en el proyecto: `flowId = "subscription-management"`.

## Entrada por `componentId`

Usa `componentId` cuando el host ya conoce el destino específico (por ejemplo, continuidad de navegación en un canvas interno):

```kotlin
RenderBlock(
    componentId = "YOUR_COMPONENT_ID",
    modifier = Modifier.fillMaxSize(),
    onEvent = { event -> /* host */ },
)
```

## Firma del composable

```kotlin
RenderBlock(
    componentId: String? = null,
    flowId: String? = null,
    onEvent: ((SDKEvent) -> Unit)? = null,
    modifier: Modifier = Modifier,
)
```
