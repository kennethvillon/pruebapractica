package com.parabank.automation.steps;

import com.parabank.automation.pages.RegisterPage;
import com.parabank.automation.utils.DriverManager;
import io.cucumber.java.es.*;
import org.junit.jupiter.api.Assertions;

public class RegisterSteps {

    private RegisterPage registerPage;

    @Dado("el usuario navega a la página de registro")
    public void navegarARegistro() {
        registerPage = new RegisterPage(DriverManager.getDriver());
        registerPage.navigateTo();
    }

    @Cuando("completa el formulario con nombre {string} {string}, dirección {string}, ciudad {string}, estado {string}, zip {string}, teléfono {string}, SSN {string}, usuario {string} y contraseña {string}")
    public void completarFormulario(String firstName, String lastName, String address,
                                    String city, String state, String zip,
                                    String phone, String ssn,
                                    String username, String password) {
        // Garantiza unicidad del username con timestamp
        String uniqueUsername = username.replace("<ts>", String.valueOf(System.currentTimeMillis()));
        registerPage.fillForm(firstName, lastName, address, city, state,
                zip, phone, ssn, uniqueUsername, password);
    }

    @Y("hace clic en el botón Register")
    public void clickRegister() {
        registerPage.clickRegister();
    }

    @Entonces("debería ver el mensaje de cuenta creada exitosamente")
    public void verificarRegistroExitoso() {
        Assertions.assertTrue(
                registerPage.isRegistrationSuccessful(),
                "El registro no mostró el mensaje de éxito esperado"
        );
    }
}
