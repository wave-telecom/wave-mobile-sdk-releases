# Autenticación

La Mobile SDK utiliza autenticación basada en `API Key` de la aplicación, proporcionada por la plataforma y asociada a la aplicación integradora.

Esta clave identifica a la aplicación cliente y permite que el backend valide y autorice el consumo de los servicios de la SDK.

## Cómo funciona

La `API Key` se pasa en la inicialización del SDK junto con el número de teléfono del usuario (`MSISDN`).

El entorno (`DEV` o `PRD`) se extrae automáticamente del payload del JWT de la `API Key`, por lo que no es necesario configurarlo de forma explícita.

## Parámetros obligatorios

| Campo | Descripción |
| --- | --- |
| `apiKey` | Llave de identificación de la aplicación. |
| `msisdn` | Número de teléfono del usuario en formato E.164. Ejemplo: `+5511999999999` |

```kotlin
FlowWrapper.start(
    apiKey = "YOUR_API_KEY",
    msisdn = "+5511999999999",
)
```

## Responsabilidades de la aplicación

La aplicación móvil debe:

- Almacenar la `API Key` de forma segura
- Evitar su exposición en logs
- No compartir la clave públicamente
- Utilizar ambientes separados (`dev`, `staging`, `prod`)

## Seguridad

La `API Key` representa la identidad de la aplicación, no del usuario.

Por lo tanto:

- No debe tratarse como token de sesión
- No reemplaza la autenticación del usuario
- Debe protegerse contra ingeniería inversa

## Nota de compatibilidad

La versión publicada `0.5.3` utiliza `apiKey + msisdn` como contrato de inicialización.

No se encontró en la API pública actual un método equivalente a:

```kotlin
RenderBlockSDK.start(
    apiKey = "YOUR_API_KEY",
    token = userToken,
)
```

