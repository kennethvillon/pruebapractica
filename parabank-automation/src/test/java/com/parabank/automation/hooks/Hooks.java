package com.parabank.automation.hooks;

import com.parabank.automation.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before(order = 1)
    public void setUp() {
        DriverManager.initDriver();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        // Captura screenshot automático si el test falla
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Fallo - " + scenario.getName());
        }
        DriverManager.quitDriver();
    }
}