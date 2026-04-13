package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class BillPayConfirmationPage extends BasePage {

    public boolean isConfirmationDisplayed() {
        System.out.println("URL después del pago: " + driver.getCurrentUrl());
        try {
            WebElement successTitle = waitForVisibility(By.xpath("//h1[contains(text(),'Bill Payment Complete')]"));
            return successTitle.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("No se encontró el título de éxito.");
            return false;
        }
    }

    public String getConfirmationText() {
        waitForVisibility(By.xpath("//h1[contains(text(),'Bill Payment Complete')]"));
        WebElement rightPanel = driver.findElement(By.id("rightPanel"));
        String text = rightPanel.getText();
        System.out.println("Texto de confirmación: " + text);
        return text;
    }
}