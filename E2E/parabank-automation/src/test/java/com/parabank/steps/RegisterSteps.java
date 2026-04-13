package com.parabank.steps;

import com.parabank.pages.RegisterPage;
import com.parabank.pages.RegistrationSuccessPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import java.util.Map;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterSteps {

    private final RegisterPage registerPage = new RegisterPage();
    private RegistrationSuccessPage successPage;
    private static String lastCreatedUsername;

    @Given("que el usuario navega a la pagina de registro")
    public void navigateToRegisterPage() {
        registerPage.navigateToRegisterPage();
    }

    @When("el usuario completa el formulario con los siguientes datos:")
    public void fillForm(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String username = data.get("usuario");
        if ("DUPLICATE".equals(username) && lastCreatedUsername != null) {
            username = lastCreatedUsername;
            System.out.println("Usando username duplicado: " + username);
        } else if (!"john".equals(username)) {
            // Usar UUID para garantizar unicidad absoluta
            username = username + "_" + UUID.randomUUID().toString().substring(0, 8);
            lastCreatedUsername = username;
            System.out.println("Username único generado: " + username);
        } else {
            System.out.println("Usando username fijo: " + username);
        }

        registerPage.fillRegistrationForm(
                data.get("nombre"), data.get("apellido"),
                data.get("direccion"), data.get("ciudad"),
                data.get("estado"), data.get("codigoPostal"),
                data.get("telefono"), data.get("ssn"),
                username,
                data.get("contrasena")
        );
    }

    @And("el usuario hace clic en Registrarse")
    public void clickRegister() {
        successPage = registerPage.clickRegister();
    }

    @Then("el sistema muestra el mensaje de registro exitoso")
    public void verifySuccess() {
        assertTrue(successPage.isSuccessMessageDisplayed(), "No se mostró el mensaje de éxito");
    }

    @Then("el sistema muestra un error de usuario duplicado")
    public void verifyDuplicateError() {
        assertTrue(registerPage.hasUsernameError(), "No se mostró el error de duplicado");
    }
}