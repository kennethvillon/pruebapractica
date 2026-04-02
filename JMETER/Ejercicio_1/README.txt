Objetivo: Simular un escenario de carga de 20 TPS para el endpoint de autenticación, validando el comportamiento con múltiples credenciales.

Configuración Técnica:
Target Throughput: 20 TPS (1200 samples/min).

Hilos (Threads): 150 usuarios concurrentes.

Parametrización: Uso de CSV Data Set Config para 5 cuentas de usuario.

Validaciones (Assertions): * Tiempo de respuesta < 1500ms.

Código de respuesta HTTP 200.

Hallazgos:
Se alcanzó un Throughput estable de 19.7 TPS.

Incidencia: El servidor presentó errores 523 (Origin Unreachable) y latencias superiores a 3s, indicando una limitación en la infraestructura del SUT.