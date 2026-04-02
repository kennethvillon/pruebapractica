package com.parabank.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {

    // ThreadLocal permite ejecución paralela futura sin conflictos
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() { }  // no instanciar directamente

    public static void initDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        // Descomenta la siguiente línea para headless (sin ventana):
        // options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverThread.set(driver);
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driverThread.remove();
        }
    }
}