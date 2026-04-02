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
        // Esperar que la sección de cuenta sea visible
        waitForElement(By.id("account"));

        // Scroll al radio para asegurar que esté en viewport
        org.openqa.selenium.WebElement radio =
                driver.findElement(By.cssSelector("input[value='guest']"));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", radio);

        // Usar JavaScript click por si el elemento está cubierto
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", radio);

        click(botonContinuar1);

        // Esperar que el formulario de billing aparezca
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

        // Seleccionar país y esperar que el dropdown de región se pueble
        selectByVisibleText(selectPais, datos.get("pais"));

        // Esperar explícitamente que la región tenga más de 1 opción (la opción vacía)
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("#input-payment-zone option"), 1));

        selectByVisibleText(selectRegion, datos.get("region"));

        click(botonContinuar2);
    }

    public void seleccionarMetodoEnvio() {
        // Esperar que el panel de shipping method sea visible
        waitForElement(By.id("button-shipping-method"));
        click(botonContinuar3);
    }

    public void aceptarTerminosYCondiciones() {
        // Esperar que el panel de payment method sea visible
        waitForElement(checkTerminos);
        click(checkTerminos);
        click(botonContinuar4);
    }

    public void confirmarOrden() {
        // Esperar que el botón de confirmar sea clickeable
        waitForElement(botonConfirmar);
        click(botonConfirmar);
    }

    public String obtenerMensajeConfirmacion() {
        return getText(mensajeExito);
    }
}