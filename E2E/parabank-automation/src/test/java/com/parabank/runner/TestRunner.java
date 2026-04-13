package com.parabank.runner;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.suite.api.*;

@Suite
@Execution(ExecutionMode.SAME_THREAD)
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = "cucumber.glue",
        value = "com.parabank.steps, com.parabank.hooks"
)
@ConfigurationParameter(
        key = "cucumber.publish.quiet",
        value = "true"
)
@ConfigurationParameter(
        key = "cucumber.plugin",
        value = "pretty, json:target/cucumber.json, html:target/cucumber-native.html"
)
public class TestRunner {
}