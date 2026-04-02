package com.parabank.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "a[href*='logout']")
    private WebElement logoutLink;

    @FindBy(css = "div.title")
    private WebElement pageTitle;

    @FindBy(linkText = "Transfer Funds")
    private WebElement transferFundsLink;

    @FindBy(linkText = "Bill Pay")
    private WebElement billPayLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedIn() {
        try {
            return logoutLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void goToTransferFunds() {
        click(transferFundsLink);
    }

    public void goToBillPay() {
        click(billPayLink);
    }

    public void logout() {
        click(logoutLink);
    }
}