package com.parabank.pages;

import com.parabank.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage extends BasePage {

    @FindBy(id = "customer.firstName")
    private WebElement firstNameInput;

    @FindBy(id = "customer.lastName")
    private WebElement lastNameInput;

    @FindBy(id = "customer.address.street")
    private WebElement addressInput;

    @FindBy(id = "customer.address.city")
    private WebElement cityInput;

    @FindBy(id = "customer.address.state")
    private WebElement stateInput;

    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCodeInput;

    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneInput;

    @FindBy(id = "customer.ssn")
    private WebElement ssnInput;

    @FindBy(id = "customer.username")
    private WebElement usernameInput;

    @FindBy(id = "customer.password")
    private WebElement passwordInput;

    @FindBy(id = "repeatedPassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@value='Register']")
    private WebElement registerButton;

    public void navigateToRegisterPage() {
        navigateTo(ConfigManager.getBaseUrl() + "/register.htm");
    }

    public void fillRegistrationForm(String firstName, String lastName,
                                     String address, String city, String state, String zipCode,
                                     String phone, String ssn, String username, String password) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(addressInput, address);
        type(cityInput, city);
        type(stateInput, state);
        type(zipCodeInput, zipCode);
        type(phoneInput, phone);
        type(ssnInput, ssn);
        type(usernameInput, username);
        type(passwordInput, password);
        type(confirmPasswordInput, password);
    }

    public RegistrationSuccessPage clickRegister() {
        click(registerButton);
        // No esperamos cambio de URL, solo devolvemos la página de éxito
        return new RegistrationSuccessPage();
    }

    public boolean hasUsernameError() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightPanel")));
            WebElement rightPanel = driver.findElement(By.id("rightPanel"));
            String text = rightPanel.getText();
            return text.contains("already exists") || text.contains("This username already exists");
        } catch (Exception e) {
            return false;
        }
    }
}