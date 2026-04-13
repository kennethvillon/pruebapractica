package com.parabank.driver;

import com.parabank.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {

    // ThreadLocal para ejecución paralela (cada hilo tiene su propio driver)
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Constructor privado para evitar instanciación (patrón Singleton)
    private DriverManager() {}

    /**
     * Obtiene el driver del hilo actual. Si no existe, lo inicializa.
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initDriver();
        }
        return driverThreadLocal.get();
    }

    /**
     * Inicializa el driver con configuración de Chrome,
     * usando WebDriverManager para gestionar el binario del driver.
     */
    public static void initDriver() {
        // Automáticamente descarga y configura el ChromeDriver adecuado
        WebDriverManager.chromedriver().setup();

        // Opciones de Chrome (puedes añadir más si es necesario)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");               // necesario en entornos Linux/CI
        options.addArguments("--disable-dev-shm-usage");    // evita problemas de memoria compartida
        options.addArguments("--remote-allow-origins=*");   // evita bloqueos CORS en algunas versiones
        // options.addArguments("--headless");              // si quieres ejecución sin interfaz gráfica
        // options.addArguments("--disable-gpu");           // útil en headless

        // Crear el driver
        WebDriver driver = new ChromeDriver(options);

        // Maximizar ventana
        driver.manage().window().maximize();

        // Configurar tiempos de espera desde ConfigManager
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(ConfigManager.getImplicitWait()));
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(ConfigManager.getPageLoadTimeout()));
        driver.manage().timeouts()
                .scriptTimeout(Duration.ofSeconds(ConfigManager.getScriptTimeout()));

        // Guardar en ThreadLocal
        driverThreadLocal.set(driver);
    }

    /**
     * Cierra el driver del hilo actual y lo remueve del ThreadLocal.
     * Útil para limpiar después de cada escenario.
     */
    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}