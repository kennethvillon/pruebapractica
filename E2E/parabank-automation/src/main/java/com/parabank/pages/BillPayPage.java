package com.parabank.pages;

import com.parabank.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BillPayPage extends BasePage {

    @FindBy(name = "payee.name")
    private WebElement payeeName;

    @FindBy(name = "payee.address.street")
    private WebElement address;

    @FindBy(name = "payee.address.city")
    private WebElement city;

    @FindBy(name = "payee.address.state")
    private WebElement state;

    @FindBy(name = "payee.address.zipCode")
    private WebElement zipCode;

    @FindBy(name = "payee.phoneNumber")
    private WebElement phone;

    @FindBy(name = "payee.accountNumber")
    private WebElement accountNumberInput;

    @FindBy(name = "verifyAccount")
    private WebElement verifyAccountInput;

    @FindBy(name = "amount")
    private WebElement amount;

    @FindBy(xpath = "//input[@value='Send Payment']")
    private WebElement sendPaymentButton;

    public void navigateToBillPay() {
        navigateTo(ConfigManager.getBaseUrl() + "/billpay.htm");
        waitForVisibility(By.name("payee.name"));
    }

    public String getFirstAccountNumber() {
        driver.get(ConfigManager.getBaseUrl() + "/overview.htm");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Obtener el primer número de cuenta visible en la tabla
        WebElement accountLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//table[@id='accountTable']//tbody/tr[1]/td[1]/a")));
        String accountNumber = accountLink.getText().trim();
        System.out.println("Número de cuenta REAL obtenido: " + accountNumber);
        driver.get(ConfigManager.getBaseUrl() + "/billpay.htm");
        waitForVisibility(By.name("payee.name"));
        return accountNumber;
    }

    public void fillPaymentForm(String payee, String addr, String cityVal,
                                String stateVal, String zip, String phoneVal,
                                String account, String amt) {
        type(payeeName, payee);
        type(address, addr);
        type(city, cityVal);
        type(state, stateVal);
        type(zipCode, zip);
        type(phone, phoneVal);
        type(accountNumberInput, account);
        type(verifyAccountInput, account);
        type(amount, amt);
    }

    public BillPayConfirmationPage submitPayment() {
        click(sendPaymentButton);
        return new BillPayConfirmationPage();
    }
}