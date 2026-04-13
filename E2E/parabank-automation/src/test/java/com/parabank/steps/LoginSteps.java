package com.parabank.steps;

import com.parabank.pages.LoginPage;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Given("que el usuario navega a la pagina de login")
    public void navigateToLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @Given("que el usuario esta autenticado como {string} con contrasena {string}")
    public void userIsAuthenticated(String username, String password) {
        loginPage.login(username, password);
    }

    @When("el usuario ingresa el usuario {string} y contrasena {string}")
    public void enterCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("el usuario hace clic en Ingresar")
    public void clickLogin() {
        loginPage.clickLogin();
    }

    @Then("el sistema muestra la vista de resumen de cuentas")
    public void verifyLoginSuccess() {
        assertTrue(loginPage.isLoginSuccessful());
    }

    @Then("el sistema muestra un mensaje de error de credenciales")
    public void verifyLoginError() {
        assertTrue(loginPage.isErrorDisplayed());
    }
}