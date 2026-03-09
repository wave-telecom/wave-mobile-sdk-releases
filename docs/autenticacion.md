# Autenticación

La inicialización requiere `apiKey` de la aplicación y `msisdn` del usuario.

## Parámetros

| Campo | Descripción |
| --- | --- |
| `apiKey` | Credencial de la aplicación. |
| `msisdn` | Número en formato E.164. |

## Inicialización

```kotlin
FlowWrapper.start(
    apiKey = "YOUR_API_KEY",
    msisdn = "+5215512345678",
)
```

Ejemplo usado en el proyecto: `msisdn = "+5511999999999"`.

## JWT de prueba (desarrollo)

```text
eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImR1MFVjYWxyZlNQckRCNU1qZ3VvMyJ9.eyJjbGllbnQiOiJ0ZWxjZWwtc3BlZWR5IiwiZW52aXJvbm1lbnQiOiJERVYiLCJpc3MiOiJodHRwczovL3dhdmUtdGVjaC1kZXYudXMuYXV0aDAuY29tLyIsInN1YiI6InJ0UFNJeTByOFlJT3hnYjJwakRWSzNZcFN3VmdQTGtRQGNsaWVudHMiLCJhdWQiOiJodHRwczovL2l6emktYWN0aXZhdGlvbi1kZXYtMGVkMi51Yy5yLmFwcHNwb3QuY29tLyIsImlhdCI6MTc3MjcxNzMxMCwiZXhwIjoxNzcyODAzNzEwLCJndHkiOiJjbGllbnQtY3JlZGVudGlhbHMiLCJhenAiOiJydFBTSXkwcjhZSU94Z2IycGpEVkszWXBTd1ZnUExrUSJ9.FRG72ttH0iPM0tXDx_G0nqjjwAXhKPXjmes20yVmfqP0pyhRkX5j_3hDcIUWZzV0sQ728voygxkTrN2evHh-FYfvrHIvkJ7W2QMBApogIwvjv6AeNLtuqK1NEEpi1vKlqAd6Er8Qn1cofOmlcWwL1qj71HBlmklPkwjNzL_oAWWDzOYYTwF5j4grgKHQKAGP5UjZvVPiCMkblkFjzp2FmFJUXIb_2I6BpRAbR166fner_C0tt_yqy0xRqYd8S6D4zqMHZ5qngyMNKl8VmXKo2O65dCDofxDYb2YqkUMwE_A88_Tlxhf-mBP3AJps305DseTj-L9N9c9M_WSQi_dY0g
```
