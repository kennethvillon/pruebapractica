package com.parabank.pages;

import org.openqa.selenium.By;

public class TransferConfirmationPage extends BasePage {
    private static final By SUCCESS_MESSAGE = By.xpath("//h1[contains(text(),'Transfer Complete')]");

    public boolean isTransferComplete() {
        return waitForVisibility(SUCCESS_MESSAGE).isDisplayed();
    }
}