package com.parabank.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    private static final String URL = "https://parabank.parasoft.com/parabank/register.htm";

    @FindBy(id = "customer.firstName")     private WebElement firstNameInput;
    @FindBy(id = "customer.lastName")      private WebElement lastNameInput;
    @FindBy(id = "customer.address.street") private WebElement addressInput;
    @FindBy(id = "customer.address.city")  private WebElement cityInput;
    @FindBy(id = "customer.address.state") private WebElement stateInput;
    @FindBy(id = "customer.address.zipCode") private WebElement zipInput;
    @FindBy(id = "customer.phoneNumber")   private WebElement phoneInput;
    @FindBy(id = "customer.ssn")           private WebElement ssnInput;
    @FindBy(id = "customer.username")      private WebElement usernameInput;
    @FindBy(id = "customer.password")      private WebElement passwordInput;
    @FindBy(id = "repeatedPassword")       private WebElement confirmPasswordInput;

    @FindBy(css = "input[value='Register']")
    private WebElement registerButton;

    @FindBy(id = "rightPanel")
    private WebElement resultPanel;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        driver.get(URL);
    }

    public void fillForm(String firstName, String lastName, String address,
                         String city, String state, String zip,
                         String phone, String ssn,
                         String username, String password) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(addressInput, address);
        type(cityInput, city);
        type(stateInput, state);
        type(zipInput, zip);
        type(phoneInput, phone);
        type(ssnInput, ssn);
        type(usernameInput, username);
        type(passwordInput, password);
        type(confirmPasswordInput, password);
    }

    public void clickRegister() {
        click(registerButton);
    }

    public boolean isRegistrationSuccessful() {
        return getText(resultPanel).contains("Your account was opened successfully");
    }
}