package com.parabank.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BillPayPage extends BasePage {

    @FindBy(name = "payee.name")               private WebElement payeeNameInput;
    @FindBy(name = "payee.address.street")      private WebElement streetInput;
    @FindBy(name = "payee.address.city")        private WebElement cityInput;
    @FindBy(name = "payee.address.state")       private WebElement stateInput;
    @FindBy(name = "payee.address.zipCode")     private WebElement zipInput;
    @FindBy(name = "payee.phoneNumber")         private WebElement phoneInput;
    @FindBy(name = "payee.accountNumber")       private WebElement accountInput;
    @FindBy(name = "verifyAccount")             private WebElement verifyAccountInput;
    @FindBy(name = "amount")                    private WebElement amountInput;

    @FindBy(css = "input[value='Send Payment']")
    private WebElement sendPaymentButton;

    @FindBy(id = "billpayResult")
    private WebElement resultPanel;

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    public void fillPaymentForm(String payee, String street, String city,
                                String state, String zip, String phone,
                                String accountNumber, String amount) {
        type(payeeNameInput, payee);
        type(streetInput, street);
        type(cityInput, city);
        type(stateInput, state);
        type(zipInput, zip);
        type(phoneInput, phone);
        type(accountInput, accountNumber);
        type(verifyAccountInput, accountNumber);
        type(amountInput, amount);
    }

    public void sendPayment() {
        click(sendPaymentButton);
    }

    public boolean isPaymentSuccessful() {
        return wait.until(ExpectedConditions.visibilityOf(resultPanel))
                .getText().contains("Bill Payment Complete");
    }
}