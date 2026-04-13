OpenCart
Test Automation Framework
README --- Guía de Configuración y Uso

===========================================================================
ATRIBUTO                     DETALLE
===========================================================================
Proyecto                     OpenCart E-Commerce Automation
Lenguaje                     Java 21
Framework de pruebas         Selenium 4 + Cucumber 7 + JUnit
Reporte                      ExtentReports + ExtentCucumberAdapter
Build tool                   Maven
IDE recomendado              IntelliJ IDEA
URL base                     http://opencart.abstracta.us
===========================================================================

1. DESCRIPCIÓN DEL PROYECTO

Este proyecto implementa un framework de automatización de pruebas funcionales end-to-end sobre la aplicación web OpenCart. Está construido sobre el patrón Page Object Model (POM) y utiliza Cucumber para definir escenarios en lenguaje Gherkin (BDD), permitiendo que tanto el equipo técnico como los stakeholders no técnicos comprendan el alcance de las pruebas.

2. ESTRUCTURA DEL PROYECTO (VISUAL)

📁 opencart-selenium-tests
│
├── 📁 .idea                     # Configuración del IDE (IntelliJ)
│
├── 📁 src
│   └── 📁 test
│       ├── 📁 java
│       │   └── 📁 com.opencart
│       │       ├── 📁 hooks
│       │       │   └── 📄 Hooks.java           # @Before, @AfterStep, @After
│       │       ├── 📁 pages                    # Page Object Model (POM)
│       │       ├── 📁 runner
│       │       │   └── 📄 RunnerTest.java      # TestRunner con CucumberOptions
│       │       ├── 📁 steps                    # Step Definitions de Cucumber
│       │       └── 📁 utils
│       │           ├── 📄 ConfigReader.java    # Lectura de config.properties
│       │           └── 📄 DriverFactory.java   # WebDriver con ThreadLocal
│       │
│       └── 📁 resources
│           ├── 📁 features
│           │   └── 📄 opencart.feature         # Escenarios Gherkin (único archivo)
│           ├── 📄 config.properties            # URL base, navegador, etc.
│           └── 📄 extent.properties            # Configuración de ExtentReports
│
├── 📁 target                     # Generado por Maven (compilación, reports)
│
├── 📁 test-output                # Reportes generados por las pruebas
│   ├── 📁 screenshots            # Capturas de pantalla por step
│   ├── 📄 cucumber.html          # Reporte Cucumber (HTML)
│   ├── 📄 cucumber.json          # Reporte Cucumber (JSON)
│   ├── 📄 extent-report.html     # Reporte principal de ExtentReports
│   └── 📄 extent-report.json     # Reporte Extent en JSON
│
├── 📄 .gitignore
└── 📄 pom.xml                    # Dependencias Maven

Nota: A diferencia de otros proyectos con múltiples archivos .feature, este concentra todos los escenarios en un único archivo `opencart.feature`.

3. TECNOLOGÍAS Y DEPENDENCIAS

3.1 Stack principal

-----------------------------------------------------------------------
DEPENDENCIA               VERSIÓN       USO
-----------------------------------------------------------------------
Java                      21            Lenguaje base
Selenium WebDriver        4.x           Automatización del navegador
Cucumber                  7.x           BDD (escenarios Gherkin)
JUnit                     4.x           Runner de pruebas
ExtentReports             5.x           Reportes HTML
ExtentCucumberAdapter     5.x           Integración Cucumber ↔ Extent
WebDriverManager          5.x           Gestión automática de drivers
Maven                     3.x           Build y dependencias
-----------------------------------------------------------------------

4. CONFIGURACIÓN DEL ENTORNO

4.1 Requisitos previos

✔ Java 21 instalado y JAVA_HOME configurado
✔ Maven 3.6+ instalado
✔ IntelliJ IDEA (recomendado)
✔ Google Chrome (el driver se gestiona automáticamente con WebDriverManager)

4.2 Clonar y ejecutar el proyecto

1. Clonar el repositorio:
   git clone <url-del-repositorio>

2. Ejecutar todas las pruebas con Maven:
   mvn clean test

3. Ejecutar un tag específico (si se definió en el feature):
   mvn test -Dcucumber.filter.tags="@Smoke"

4.3 Archivo config.properties

Ubicación: src/test/resources/config.properties
Contenido ejemplo:
   base.url=http://opencart.abstracta.us
   browser=chrome

5. SISTEMA DE REPORTES

5.1 ExtentReports + Screenshots por step

El proyecto genera automáticamente:
- 📄 test-output/extent-report.html (principal)
- 📄 test-output/extent-report.json
- 📄 test-output/cucumber.html y cucumber.json (reporte nativo de Cucumber)

Cada step del escenario incluye un screenshot adjunto en el reporte Extent.

5.2 Funcionamiento de los screenshots

-----------------------------------------------------------------------
HOOK           MOMENTO                       ACCIÓN
-----------------------------------------------------------------------
@Before        Antes del escenario           Inicializa el driver y navega a la URL base
@AfterStep     Después de cada step          Captura screenshot y lo adjunta al reporte
@After         Al finalizar el escenario     Cierra el driver y hace flush del reporte
-----------------------------------------------------------------------

5.3 extent.properties

Ubicación: src/test/resources/extent.properties
Líneas clave:
   extent.reporter.html.out=test-output/extent-report.html
   screenshot.encoding=base64
   extent.reporter.html.config.enableScreenshotsInBase64=true

6. ESCENARIOS AUTOMATIZADOS

Todos los escenarios están definidos en un solo archivo:
   📄 src/test/resources/features/opencart.feature

Los escenarios cubren (ejemplos típicos):
- ✅ Login exitoso y fallido
- ✅ Registro de nuevo usuario
- ✅ Búsqueda de productos
- ✅ Gestión del carrito de compras
- ✅ Checkout como invitado

7. PATRONES Y BUENAS PRÁCTICAS

- 🧩 Page Object Model (POM): cada página tiene su propia clase en `pages/`.
- 🧵 DriverFactory con ThreadLocal: aislamiento del WebDriver en ejecuciones paralelas.
- ⚙️ ConfigReader: lectura centralizada de config.properties.
- ⏱️ Esperas explícitas (WebDriverWait): evita StaleElementReferenceException.
- 📸 Hooks con @AfterStep: captura automática de screenshot.
- 🏷️ Tags de Cucumber: opcionales (@Smoke, @Regression) para filtrar ejecución.

8. CONTACTO Y AUTORÍA

Proyecto desarrollado como parte del programa de formación en QA Automation.
Framework: Java 21 + Selenium 4 + Cucumber 7 + ExtentReports 5