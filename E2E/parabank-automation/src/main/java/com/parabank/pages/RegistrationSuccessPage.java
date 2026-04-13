package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationSuccessPage extends BasePage {

    public boolean isSuccessMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightPanel")));
            WebElement rightPanel = driver.findElement(By.id("rightPanel"));
            String panelText = rightPanel.getText();
            System.out.println("=== CONTENIDO DEL PANEL DERECHO ===");
            System.out.println(panelText);
            System.out.println("===================================");

            // Verificar indicadores de éxito (inglés y español)
            return panelText.contains("created successfully")
                    || panelText.contains("Your account was created")
                    || panelText.contains("Welcome")
                    || panelText.contains("cuenta fue creada")
                    || panelText.contains("bienvenido");
        } catch (TimeoutException e) {
            System.err.println("No se encontró el panel derecho: " + e.getMessage());
            return false;
        }
    }
}