package com.parabank.pages;

import com.parabank.config.ConfigManager;
import com.parabank.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigManager.getExplicitWait())
        );
        PageFactory.initElements(driver, this);
    }

    // Wait for WebElement
    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for By locator
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(WebElement element) {
        waitForClickable(element);
        element.click();
    }

    protected void selectByVisibleText(WebElement element, String text) {
        waitForVisibility(element);
        new Select(element).selectByVisibleText(text);
    }

    protected String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText().trim();
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}