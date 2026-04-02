Objetivo: Evaluar la capacidad de respuesta de una API pública bajo una ráfaga de peticiones concurrentes.

Configuración Técnica:
Número de Hilos: 250 usuarios.

Ramp-up Period: 5 segundos (50 usuarios entrando por segundo).

Loop Count: 1.

Endpoint: GET /api/v2/pokemon/pikachu.

Componentes de Análisis:
View Results Tree: Validación de la estructura JSON de respuesta.

Summary Report: Cálculo de promedios y porcentajes de error bajo estrés.

Graph Results: Visualización de la dispersión de datos y desviación estándar.