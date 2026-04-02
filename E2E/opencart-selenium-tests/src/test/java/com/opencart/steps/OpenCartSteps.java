package com.opencart.steps;

import com.opencart.pages.*;
import com.opencart.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class OpenCartSteps {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final HomePage homePage;
    private final ProductPage productPage;
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;

    private static final String BASE_URL = "http://opencart.abstracta.us/";

    public OpenCartSteps() {
        this.driver       = DriverFactory.getDriver();
        this.wait         = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.homePage     = new HomePage(driver);
        this.productPage  = new ProductPage(driver);
        this.cartPage     = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    @Given("el usuario está en la página principal de OpenCart")
    public void el_usuario_está_en_la_página_principal() {
        wait.until(ExpectedConditions.titleContains("Your Store"));
        Assert.assertTrue(driver.getTitle().contains("Your Store"));
    }

    @When("busca el producto {string}")
    public void busca_el_producto(String producto) {
        homePage.buscarProducto(producto);
    }

    @When("agrega el producto al carrito")
    public void agrega_el_producto_al_carrito() {
        productPage.clickPrimerProducto();
        productPage.agregarAlCarrito();

        // Esperar confirmación visual de que el producto fue agregado
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".alert-success")));

        // Navegar al inicio de forma directa y determinista
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.titleContains("Your Store"));
    }

    @Then("el carrito debe mostrar {int} productos")
    public void el_carrito_debe_mostrar_productos(Integer cantidad) {
        // Esperar que el contador del carrito refleje la cantidad correcta
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("#cart-total"),
                cantidad + " item"));

        String textoCarrito = driver
                .findElement(By.cssSelector("#cart-total"))
                .getText(); // Ej: "2 item(s) - $1,698.00"

        Assert.assertTrue(
                "Carrito muestra: '" + textoCarrito
                        + "' pero se esperaban " + cantidad + " productos",
                textoCarrito.startsWith(String.valueOf(cantidad))
        );
    }

    @When("el usuario accede al carrito")
    public void el_usuario_accede_al_carrito() {
        cartPage.abrirCarrito();
    }

    @When("procede al checkout")
    public void procede_al_checkout() {
        cartPage.procederAlCheckout();
    }

    @When("selecciona {string}")
    public void selecciona(String opcion) {
        if (opcion.equals("Guest Checkout")) {
            checkoutPage.seleccionarGuestCheckout();
        }
    }

    @When("completa los datos de envío con:")
    public void completa_los_datos_de_envío_con(DataTable dataTable) {
        Map<String, String> datos = dataTable.asMap(String.class, String.class);
        checkoutPage.completarDatosEnvio(datos);
    }

    @When("selecciona método de envío")
    public void selecciona_método_de_envío() {
        checkoutPage.seleccionarMetodoEnvio();
    }

    @When("acepta los términos y condiciones")
    public void acepta_los_términos_y_condiciones() {
        checkoutPage.aceptarTerminosYCondiciones();
    }

    @When("confirma la orden")
    public void confirma_la_orden() {
        checkoutPage.confirmarOrden();
    }

    @Then("debe ver el mensaje {string}")
    public void debe_ver_el_mensaje(String mensajeEsperado) {
        String mensajeReal = checkoutPage.obtenerMensajeConfirmacion();
        Assert.assertTrue(
                "Se esperaba: '" + mensajeEsperado
                        + "' pero se obtuvo: '" + mensajeReal + "'",
                mensajeReal.contains(mensajeEsperado)
        );
    }
}