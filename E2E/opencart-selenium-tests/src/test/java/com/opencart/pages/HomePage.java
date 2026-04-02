package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By searchBox    = By.name("search");
    private final By searchButton = By.cssSelector("#search button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void buscarProducto(String producto) {
        type(searchBox, producto);
        click(searchButton);
    }
}