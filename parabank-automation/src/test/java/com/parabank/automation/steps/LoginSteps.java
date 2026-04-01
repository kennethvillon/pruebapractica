package com.parabank.automation.steps;

import com.parabank.automation.pages.HomePage;
import com.parabank.automation.pages.LoginPage;
import com.parabank.automation.utils.DriverManager;
import io.cucumber.java.es.*;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    private LoginPage loginPage;
    private HomePage homePage;

    @Dado("el usuario está en la página principal de ParaBank")
    public void navegarAInicio() {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateTo();
    }

    @Cuando("ingresa el usuario {string} y contraseña {string}")
    public void ingresarCredenciales(String username, String password) {
        loginPage.login(username, password);
    }

    @Y("hace clic en Log In")
    public void clickLogin() {
        // El click ya ocurre dentro de login(), step para claridad narrativa
    }

    @Entonces("el resultado del login debería ser {string}")
    public void verificarResultadoLogin(String resultado) {
        homePage = new HomePage(DriverManager.getDriver());
        if ("exitoso".equals(resultado)) {
            Assertions.assertTrue(homePage.isLoggedIn(), "El login no fue exitoso");
        } else {
            Assertions.assertTrue(loginPage.isErrorVisible(), "No se mostró error de login");
        }
    }

    // Step reutilizado por Background de otros features
    @Dado("el usuario ha iniciado sesión con usuario {string} y contraseña {string}")
    public void loginDirecto(String username, String password) {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateTo();
        loginPage.login(username, password);
        homePage = new HomePage(DriverManager.getDriver());
        Assertions.assertTrue(homePage.isLoggedIn(), "No se pudo iniciar sesión para el test");
    }
}