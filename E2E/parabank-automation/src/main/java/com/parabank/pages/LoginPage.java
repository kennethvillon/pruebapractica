package com.parabank.pages;

import com.parabank.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Log In']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"leftPanel\"]/h2")
    private WebElement accountsOverviewTitle;

    public void navigateToLoginPage() {
        navigateTo(ConfigManager.getBaseUrl() + "/index.htm");
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        navigateToLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        return waitForVisibility(accountsOverviewTitle).isDisplayed();
    }

    public boolean isErrorDisplayed() {
        return isElementPresent(By.xpath("//p[@class='error']"));
    }
}