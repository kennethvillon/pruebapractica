package com.parabank.automation.runner;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")          // ← apunta directo a la carpeta
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.parabank.automation.steps,com.parabank.automation.hooks"
)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty,html:target/cucumber-report.html,com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
)
public class TestRunner {
}