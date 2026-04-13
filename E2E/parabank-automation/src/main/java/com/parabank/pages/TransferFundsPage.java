package com.parabank.pages;

import com.parabank.config.ConfigManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage extends BasePage {

    @FindBy(id = "amount")
    private WebElement amountInput;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountSelect;

    @FindBy(id = "toAccountId")
    private WebElement toAccountSelect;

    @FindBy(xpath = "//input[@value='Transfer']")
    private WebElement transferButton;

    public void navigateToTransfer() {
        navigateTo(ConfigManager.getBaseUrl() + "/transfer.htm");
    }

    public void enterAmount(String amount) {
        type(amountInput, amount);
    }

    public void selectFromAccountByIndex(int index) {
        Select select = new Select(fromAccountSelect);
        select.selectByIndex(index);
    }

    public void selectToAccountByIndex(int index) {
        Select select = new Select(toAccountSelect);
        if (select.getOptions().size() <= index) {
            throw new IllegalStateException("No existe la cuenta con índice " + index + ". Solo hay " + select.getOptions().size() + " cuentas.");
        }
        select.selectByIndex(index);
    }

    public void selectToAccountByVisibleText(String accountNumber) {
        Select select = new Select(toAccountSelect);
        select.selectByVisibleText(accountNumber);
    }

    public TransferConfirmationPage confirmTransfer() {
        click(transferButton);
        return new TransferConfirmationPage();
    }
}