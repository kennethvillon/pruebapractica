package com.opencart.steps;

import com.opencart.pages.*;
import com.opencart.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class OpenCartSteps {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public OpenCartSteps() {
        this.driver      = DriverFactory.getDriver();
        this.homePage    = new HomePage(driver);
        this.productPage = new ProductPage(driver);
        this.cartPage    = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    @Given("el usuario está en la página principal de OpenCart")
    public void el_usuario_está_en_la_página_principal() {
        // El Hooks.java ya navega a la URL base antes de cada escenario
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
        // Volver a la página principal para buscar el siguiente producto
        driver.navigate().back();
        driver.navigate().back();
    }

    @Then("el carrito debe mostrar {int} productos")
    public void el_carrito_debe_mostrar_productos(Integer cantidad) {
        // El número en el ícono del carrito
        String textoCarrito = DriverFactory.getDriver()
                .findElement(org.openqa.selenium.By.cssSelector("#cart button"))
                .getText();
        Assert.assertTrue(textoCarrito.contains(String.valueOf(cantidad)));
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
                "Se esperaba: " + mensajeEsperado + " pero se obtuvo: " + mensajeReal,
                mensajeReal.contains(mensajeEsperado)
        );
    }
}