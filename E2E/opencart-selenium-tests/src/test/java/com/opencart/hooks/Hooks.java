package com.opencart.hooks;

import com.opencart.utils.ConfigReader;
import com.opencart.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private int stepCounter = 0;

    @Before
    public void setUp() {
        stepCounter = 0;
        DriverFactory.getDriver().get(ConfigReader.getProperty("base.url"));
    }

    @AfterStep
    public void captureScreenshotAfterStep(Scenario scenario) {
        try {
            stepCounter++;
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            // ✅ ExtentCucumberAdapter recoge esto automáticamente
            scenario.attach(screenshot, "image/png", "Step " + stepCounter);
        } catch (Exception e) {
            System.err.println("Error capturando screenshot en step " + stepCounter + ": " + e.getMessage());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        DriverFactory.quitDriver();
    }
}