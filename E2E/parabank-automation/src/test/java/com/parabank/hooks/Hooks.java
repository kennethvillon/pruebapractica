package com.parabank.hooks;

import com.parabank.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        DriverManager.initDriver();
        WebDriver driver = DriverManager.getDriver();

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Inicio: " + scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Paso: " + scenario.getName());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Fin - "
                    + (scenario.isFailed() ? "FALLIDO" : "EXITOSO")
                    + ": " + scenario.getName());

            DriverManager.quitDriver();
        }
    }
}