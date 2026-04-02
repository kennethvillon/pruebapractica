package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Map;

public class CheckoutPage extends BasePage {

    // Paso 1: Guest checkout
    private final By radioGuest      = By.cssSelector("input[value='guest']");
    private final By botonContinuar1 = By.id("button-account");

    // Paso 2: Datos personales — el formulario aparece tras el paso 1
    private final By formularioBilling = By.id("input-payment-firstname");
    private final By inputNombre       = By.id("input-payment-firstname");
    private final By inputApellido     = By.id("input-payment-lastname");
    private final By inputEmail        = By.id("input-payment-email");
    private final By inputTelefono     = By.id("input-payment-telephone");
    private final By inputDireccion    = By.id("input-payment-address-1");
    private final By inputCiudad       = By.id("input-payment-city");
    private final By inputCodigoPostal = By.id("input-payment-postcode");
    private final By selectPais        = By.id("input-payment-country");
    private final By selectRegion      = By.id("input-payment-zone");
    private final By botonContinuar2   = By.id("button-guest");

    // Paso 3: Método de envío
    private final By botonContinuar3   = By.id("button-shipping-method");

    // Paso 4: Términos y pago
    private final By checkTerminos     = By.name("agree");
    private final By botonContinuar4   = By.id("button-payment-method");

    // Paso 5: Confirmar
    private final By botonConfirmar    = By.id("button-confirm");

    // Confirmación final
    private final By mensajeExito      = By.cssSelector("#content h1");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void seleccionarGuestCheckout() {
        // ✅ Espera el radio directamente, elimina el waitForElement(By.id("account"))
        waitForElement(By.cssSelector("input[value='guest']"));

        org.openqa.selenium.WebElement radio =
                driver.findElement(By.cssSelector("input[value='guest']"));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", radio);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", radio);

        click(botonContinuar1);

        waitForElement(By.id("input-payment-firstname"));
    }

    public void completarDatosEnvio(Map<String, String> datos) {
        type(inputNombre,       datos.get("nombre"));
        type(inputApellido,     datos.get("apellido"));
        type(inputEmail,        datos.get("email"));
        type(inputTelefono,     datos.get("telefono"));
        type(inputDireccion,    datos.get("direccion"));
        type(inputCiudad,       datos.get("ciudad"));
        type(inputCodigoPostal, datos.get("codigopost"));

        // Seleccionar país — dispara AJAX que recarga el dropdown de región
        selectByVisibleText(selectPais, datos.get("pais"));

        // ✅ Re-encuentra el dropdown en CADA intento para evitar StaleElementReference
        wait.until(d -> {
            try {
                org.openqa.selenium.support.ui.Select s =
                        new org.openqa.selenium.support.ui.Select(
                                d.findElement(By.id("input-payment-zone")));  // ← re-find cada vez

                return s.getOptions().stream()
                        .anyMatch(opt -> opt.getText().trim().equals(datos.get("region")));

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                return false;  // ← dropdown aún se está recargando, reintentar
            }
        });

        selectByVisibleText(selectRegion, datos.get("region"));
        click(botonContinuar2);
    }

    public void seleccionarMetodoEnvio() {
        // ✅ Esperar que el panel de shipping esté visible y habilitado
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("button-shipping-method")));

        // ✅ Scroll + JS click para evitar que esté tapado por otro elemento
        org.openqa.selenium.WebElement btn =
                driver.findElement(By.id("button-shipping-method"));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", btn);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);
    }

    public void aceptarTerminosYCondiciones() {
        // ✅ Esperar que el panel de payment method sea visible
        wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));

        org.openqa.selenium.WebElement chk = driver.findElement(By.name("agree"));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", chk);

        // ✅ Esperar que el botón de continuar esté habilitado
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("button-payment-method")));

        org.openqa.selenium.WebElement btn =
                driver.findElement(By.id("button-payment-method"));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);
    }

    public void confirmarOrden() {
        // ✅ Esperar que el botón confirm sea visible Y clickeable
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("button-confirm")));

        org.openqa.selenium.WebElement btn =
                driver.findElement(By.id("button-confirm"));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", btn);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);
    }

    public String obtenerMensajeConfirmacion() {
        // ✅ Esperar que la URL cambie (indica que la orden fue procesada)
        wait.until(ExpectedConditions.urlContains("route=checkout/success"));

        // ✅ Luego esperar el h1 con el mensaje
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#content h1")));

        return getText(mensajeExito);
    }
}