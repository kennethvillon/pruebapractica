package com.parabank.steps;

import com.parabank.pages.TransferFundsPage;
import com.parabank.pages.TransferConfirmationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class TransferSteps {

    private TransferFundsPage transferPage = new TransferFundsPage();
    private TransferConfirmationPage confirmationPage;

    @Given("el usuario navega a la pagina de transferencia")
    public void navigateToTransfer() {
        transferPage.navigateToTransfer();
    }

    @When("el usuario ingresa el monto {string}")
    public void enterAmount(String amount) {
        transferPage.enterAmount(amount);
    }

    @And("el usuario selecciona la cuenta origen")
    public void selectFromAccount() {
        // Suponemos que la primera cuenta (índice 0) es la origen
        transferPage.selectFromAccountByIndex(0);
    }

    @And("el usuario selecciona la cuenta destino")
    public void selectToAccount() {
        // Intentamos seleccionar la segunda cuenta (índice 1)
        // Si falla, mostramos un mensaje claro
        try {
            transferPage.selectToAccountByIndex(1);
        } catch (IllegalStateException e) {
            fail("No hay una segunda cuenta para transferir. Asegúrate de que el usuario tenga al menos dos cuentas. " + e.getMessage());
        }
    }

    @And("el usuario confirma la transferencia")
    public void confirmTransfer() {
        confirmationPage = transferPage.confirmTransfer();
    }

    @Then("el sistema muestra la confirmacion de transferencia exitosa")
    public void verifyTransfer() {
        assertTrue(confirmationPage.isTransferComplete(), "No se mostró la confirmación de transferencia");
    }
}