package com.opencart.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features  = "src/test/resources/features",
        glue      = {"com.opencart.steps", "com.opencart.hooks"},
        plugin    = {
                "pretty",
                "json:test-output/cucumber.json",
                "html:test-output/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class RunnerTest {}