# 🏦 ParaBank Automation — Evaluación Práctica QA 2026

Framework de automatización de pruebas funcionales E2E sobre la aplicación web **ParaBank**,
desarrollado como evaluación práctica del curso de QA Automation.

---

## DESCRIPCIÓN

Proyecto de automatización funcional end-to-end implementado con **Selenium + Cucumber + JUnit 5**,
aplicando el patrón **Page Object Model (POM)** para validar los flujos críticos de una
aplicación bancaria demo.

**Aplicación bajo prueba:** ParaBank — https://parabank.parasoft.com

Los escenarios cubren los siguientes flujos:

- Registro de usuario nuevo en el sistema
- Inicio de sesión con credenciales registradas
- Transferencia de fondos entre dos cuentas propias
- Retiro mediante pago de factura (Bill Pay)

---

## PRERREQUISITOS

Antes de ejecutar el proyecto, asegúrese de tener instalado:

**1. Java Development Kit (JDK) 21**
```
Verificar:  java -version
Descargar:  https://adoptium.net/
```

**2. Apache Maven 3.6 o superior**
```
Verificar:  mvn -version
Descargar:  https://maven.apache.org/download.cgi
```

**3. Google Chrome (versión reciente)**
El proyecto usa WebDriverManager, por lo que el ChromeDriver se descarga
automáticamente al ejecutar las pruebas. No es necesario instalarlo manualmente.

**4. Conexión a internet activa**
Las pruebas acceden al sitio en línea: https://parabank.parasoft.com

**5. Variables de entorno configuradas**
- `JAVA_HOME` apuntando al directorio de instalación del JDK
- Maven incluido en el `PATH` del sistema

---

## TECNOLOGÍAS UTILIZADAS

| Herramienta           | Versión   | Propósito                                    |
|-----------------------|-----------|----------------------------------------------|
| Java                  | 21 LTS    | Lenguaje base del framework                  |
| Maven                 | 3.6+      | Gestión de dependencias y ciclo de vida      |
| Selenium WebDriver    | 4.21.0    | Automatización del navegador                 |
| WebDriverManager      | 5.8.0     | Descarga automática del ChromeDriver         |
| Cucumber              | 7.18.0    | Framework BDD — escritura en Gherkin         |
| JUnit 5               | 5.10.2    | Motor de ejecución de pruebas                |
| Commons IO            | 2.15.1    | Utilidades de entrada/salida                 |
---

## ESTRUCTURA DEL PROYECTO

```
parabank-automation/
├── src/
│   ├── main/java/com/parabank/
│   │   ├── config/
│   │   │   └── ConfigManager.java              # Tiempos de espera configurables
│   │   ├── driver/
│   │   │   └── DriverManager.java              # Singleton ThreadLocal del WebDriver
│   │   └── pages/                              # Page Objects (POM)
│   │       ├── BasePage.java                   # Clase base con métodos comunes
│   │       ├── LoginPage.java
│   │       ├── RegisterPage.java
│   │       ├── RegistrationSuccessPage.java
│   │       ├── BillPayPage.java
│   │       ├── BillPayConfirmationPage.java
│   │       ├── TransferFundsPage.java
│   │       └── TransferConfirmationPage.java
│   └── test/java/com/parabank/
│       ├── hooks/
│       │   └── Hooks.java                      # @Before, @AfterStep, @After + capturas
│       ├── steps/                              # Step Definitions por módulo
│       │   ├── LoginSteps.java
│       │   ├── RegisterSteps.java
│       │   ├── TransferSteps.java
│       │   └── WithdrawSteps.java
│       └── runner/
│           └── TestRunner.java                 # Configuración de la suite JUnit Platform
├── src/test/resources/
│   ├── features/                               # Escenarios en lenguaje Gherkin
│   │   ├── login.feature
│   │   ├── registro.feature
│   │   ├── transferencia.feature
│   │   └── retiro.feature
│   └── extent-config.properties
├── target/
│   ├── cucumber.json                           # JSON fuente para los reportes
│   ├── cucumber-native.html                    # Reporte nativo de Cucumber
│   └── pom.xml
```

---

## INSTRUCCIONES DE EJECUCIÓN

### PASO 1 — Clonar o descomprimir el repositorio

Si descargó el ZIP, descomprima el contenido en una carpeta local.
Si clona desde Git:
```bash
git clone <URL_DEL_REPOSITORIO>
```

### PASO 2 — Navegar al directorio raíz del proyecto

```bash
cd "ruta/al/proyecto/parabank-automation"
```

### PASO 3 — Compilar el proyecto

```bash
mvn compile test-compile
```

### PASO 4 — Ejecutar las pruebas y generar reportes

```bash
mvn verify
```

Este comando ejecuta las pruebas y genera automáticamente los tres reportes HTML.
Si desea limpiar compilaciones anteriores antes de ejecutar:

```bash
mvn clean verify
```

### PASO 5 — Visualizar los reportes

| Reporte           | Ruta                                        |
|-------------------|---------------------------------------------|
| Nativo Cucumber   | `target/cucumber-native.html`               |
---

## NAVEGADORES SOPORTADOS

El navegador por defecto es **Google Chrome**. WebDriverManager gestiona
automáticamente la descarga del driver correspondiente a la versión instalada.

| Navegador       | Soporte       | Observación                                      |
|-----------------|---------------|--------------------------------------------------|
| Chrome          | ✅ Automático  | Configuración por defecto, sin pasos adicionales |
| Firefox         | ⚙️ Manual      | Requiere ajuste en `DriverManager.java`          |
| Edge            | ⚙️ Manual      | Requiere ajuste en `DriverManager.java`          |

Para cambiar de navegador, modificar la instanciación del driver en
`src/main/java/com/parabank/driver/DriverManager.java`.

---

## CAPTURAS DE PANTALLA

Las capturas se toman automáticamente en tres momentos de cada escenario
mediante la clase `Hooks.java`:

| Anotación    | Momento de captura                          |
|--------------|---------------------------------------------|
| `@Before`    | Al inicio del escenario (pantalla inicial)  |
| `@AfterStep` | Después de cada paso Given / When / Then    |
| `@After`     | Al finalizar (marcado como EXITOSO/FALLIDO) |

Las imágenes se adjuntan con `scenario.attach()` y quedan embebidas en el
`cucumber.json`, permitiendo que los tres reportes las visualicen sin
configuración adicional.

---

## NOTAS IMPORTANTES

- El servidor de ParaBank (`parabank.parasoft.com`) es un entorno de demostración
  público que puede presentar lentitud o inestabilidad ocasional.

- Para ejecutar el escenario de transferencia, el usuario debe contar con al
  menos dos cuentas registradas en ParaBank.

- El proyecto incluye `<testFailureIgnore>true</testFailureIgnore>` en Surefire,
  lo que garantiza que los reportes se generen aunque existan escenarios fallidos.

- El `DriverManager` utiliza `ThreadLocal` para aislar la instancia del driver
  por hilo, preparando el framework para ejecución en paralelo.