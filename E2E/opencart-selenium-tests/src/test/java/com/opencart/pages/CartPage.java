package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By botonCarrito   = By.cssSelector("#cart button");
    private final By linkVerCarrito = By.cssSelector("#cart ul li:last-child p a");
    private final By filasProducto  = By.cssSelector("#content table tbody tr");
    private final By botonCheckout = By.xpath("//a[contains(@href,'checkout') and contains(@class,'btn')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void abrirCarrito() {
        click(botonCarrito);
        waitForElement(linkVerCarrito);
        click(linkVerCarrito);
    }

    public int obtenerCantidadProductos() {
        waitForElement(filasProducto);
        return driver.findElements(filasProducto).size();
    }

    public void procederAlCheckout() {
        waitForElement(botonCheckout);
        // Scroll + JS click por seguridad
        org.openqa.selenium.WebElement btn = driver.findElement(botonCheckout);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", btn);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);

        // Esperar que la página de checkout cargue
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .urlContains("checkout"));
    }
}