package com.parabank.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TransferPage extends BasePage {

    @FindBy(id = "amount")
    private WebElement amountInput;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountSelect;

    @FindBy(id = "toAccountId")
    private WebElement toAccountSelect;

    @FindBy(css = "input[value='Transfer']")
    private WebElement transferButton;

    @FindBy(id = "showResult")
    private WebElement resultPanel;

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public void transfer(String amount, int fromIndex, int toIndex) {
        type(amountInput, amount);
        new Select(fromAccountSelect).selectByIndex(fromIndex);
        new Select(toAccountSelect).selectByIndex(toIndex);
        click(transferButton);
    }

    public boolean isTransferSuccessful() {
        return wait.until(ExpectedConditions.visibilityOf(resultPanel))
                .getText().contains("Transfer Complete!");
    }
}