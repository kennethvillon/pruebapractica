package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    // Primer resultado de búsqueda
    private final By primerResultado  = By.cssSelector(".product-thumb h4 a");
    // Botón "Add to Cart" en detalle del producto
    private final By botonAddToCart   = By.id("button-cart");
    // Alerta de confirmación
    private final By alertaExito      = By.cssSelector(".alert-success");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickPrimerProducto() {
        click(primerResultado);
    }

    public void agregarAlCarrito() {
        click(botonAddToCart);
        // Esperar confirmación
        waitForElement(alertaExito);
    }
}