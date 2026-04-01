package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By botonCarrito     = By.cssSelector("#cart button");
    private final By linkVerCarrito   = By.cssSelector("#cart ul li:last-child a");
    private final By filasProducto    = By.cssSelector("#content table tbody tr");
    private final By botonCheckout    = By.cssSelector("a[href*='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void abrirCarrito() {
        click(botonCarrito);
        click(linkVerCarrito);
    }

    public int obtenerCantidadProductos() {
        return driver.findElements(filasProducto).size();
    }

    public void procederAlCheckout() {
        click(botonCheckout);
    }
}