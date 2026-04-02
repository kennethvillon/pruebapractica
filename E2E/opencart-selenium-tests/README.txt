--------------------------------------------------------------------------------
EJECUTAR LAS PRUEBAS
--------------------------------------------------------------------------------

  OPCIÓN A — Terminal (recomendado):
    mvn clean test

  OPCIÓN B — Desde IntelliJ:
    1. Abrir el proyecto en IntelliJ IDEA
    2. Click derecho sobre TestRunner.java
    3. Seleccionar "Run 'TestRunner'"

  OPCIÓN C — Escenario individual desde el .feature:
    1. Abrir src/test/resources/features/opencart.feature
    2. Click en el ícono verde al lado del Scenario
    3. Seleccionar "Run Scenario"

  OPCIÓN D — Solo con tag específico:
    mvn clean test -Dcucumber.filter.tags="@smoke"

--------------------------------------------------------------------------------
CASOS DE PRUEBA IMPLEMENTADOS
--------------------------------------------------------------------------------

  Scenario: Agregar dos productos al carrito y completar checkout como invitado
    - Acceder a http://opencart.abstracta.us/
    - Buscar y agregar "MacBook" al carrito
    - Buscar y agregar "iPhone" al carrito
    - Verificar que el carrito muestra 2 productos
    - Acceder al carrito y proceder al checkout
    - Seleccionar "Guest Checkout"
    - Completar formulario de datos de envío
    - Seleccionar método de envío (Flat Shipping Rate)
    - Aceptar términos y condiciones
    - Confirmar la orden
    - Verificar mensaje: "Your order has been placed!"

--------------------------------------------------------------------------------
VER LOS REPORTES
--------------------------------------------------------------------------------

  Después de ejecutar mvn clean test, los reportes se generan en reports/

  4. SCREENSHOTS AUTOMÁTICOS:
       test-output/screenshots/
     
       - Se genera una captura si el escenario FALLA

Adicional para ver el reporte en html, se genera al momento de ejecutar las pruebas en la carpeta  test-output/screenshots/extent-report.html
Luego clic derecho OPEN IN --- BROWSER ---  y el navegador que se desee.

  NOTA: La carpeta reports/ se recrea en cada ejecución con mvn clean test.
        Si quieres conservar reportes anteriores, cópialos antes de ejecutar.

--------------------------------------------------------------------------------
NOTAS IMPORTANTES
--------------------------------------------------------------------------------

  - WebDriverManager gestiona ChromeDriver automáticamente.
    No es necesario configurar rutas ni descargar drivers manualmente.

  - El SUT (http://opencart.abstracta.us/) es un ambiente compartido de pruebas.
    Puede presentar lentitud ocasional o datos previos en el carrito.
