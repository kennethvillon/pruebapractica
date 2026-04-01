package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Map;

public class CheckoutPage extends BasePage {

    // Paso 1: Guest checkout
    private final By radioGuest         = By.id("input-account-guest");
    private final By botonContinuar1    = By.id("button-account");

    // Paso 2: Datos personales
    private final By inputNombre        = By.id("input-payment-firstname");
    private final By inputApellido      = By.id("input-payment-lastname");
    private final By inputEmail         = By.id("input-payment-email");
    private final By inputTelefono      = By.id("input-payment-telephone");
    private final By inputDireccion     = By.id("input-payment-address-1");
    private final By inputCiudad        = By.id("input-payment-city");
    private final By inputCodigoPostal  = By.id("input-payment-postcode");
    private final By selectPais         = By.id("input-payment-country");
    private final By selectRegion       = By.id("input-payment-zone");
    private final By botonContinuar2    = By.id("button-guest");

    // Paso 3: Método de envío
    private final By botonContinuar3    = By.id("button-shipping-method");

    // Paso 4: Método de pago / términos
    private final By checkTerminos      = By.name("agree");
    private final By botonContinuar4    = By.id("button-payment-method");

    // Paso 5: Confirmar orden
    private final By botonConfirmar     = By.id("button-confirm");

    // Confirmación final
    private final By mensajeExito       = By.cssSelector("#content h1");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void seleccionarGuestCheckout() {
        click(radioGuest);
        click(botonContinuar1);
    }

    public void completarDatosEnvio(Map<String, String> datos) {
        type(inputNombre,       datos.get("nombre"));
        type(inputApellido,     datos.get("apellido"));
        type(inputEmail,        datos.get("email"));
        type(inputTelefono,     datos.get("telefono"));
        type(inputDireccion,    datos.get("direccion"));
        type(inputCiudad,       datos.get("ciudad"));
        type(inputCodigoPostal, datos.get("codigopost"));

        selectByVisibleText(selectPais,   datos.get("pais"));
        // Esperar que cargue la región después de seleccionar país
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        selectByVisibleText(selectRegion, datos.get("region"));

        click(botonContinuar2);
    }

    public void seleccionarMetodoEnvio() {
        click(botonContinuar3);
    }

    public void aceptarTerminosYCondiciones() {
        click(checkTerminos);
        click(botonContinuar4);
    }

    public void confirmarOrden() {
        click(botonConfirmar);
    }

    public String obtenerMensajeConfirmacion() {
        return getText(mensajeExito);
    }
}