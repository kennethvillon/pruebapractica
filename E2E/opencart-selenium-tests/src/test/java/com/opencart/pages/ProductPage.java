package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    // Primer resultado en página de búsqueda
    private final By primerResultado = By.cssSelector(".product-thumb h4 a");
    // Botón "Add to Cart" en detalle del producto
    private final By botonAddToCart  = By.id("button-cart");
    // Alerta de confirmación
    private final By alertaExito     = By.cssSelector(".alert-success");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickPrimerProducto() {
        // Esperar que los resultados de búsqueda carguen
        waitForElement(primerResultado);
        click(primerResultado);
    }

    public void agregarAlCarrito() {
        // Esperar que el botón esté listo en la página de detalle
        waitForElement(botonAddToCart);
        click(botonAddToCart);
        // Esperar confirmación visual
        waitForElement(alertaExito);
        // Esperar también que el contador del carrito se actualice
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.cssSelector("#cart-total"), "0 item")));
    }
}