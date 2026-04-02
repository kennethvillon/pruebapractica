package com.parabank.automation.steps;

import com.parabank.automation.pages.HomePage;
import com.parabank.automation.pages.TransferPage;
import com.parabank.automation.utils.DriverManager;
import io.cucumber.java.es.*;
import org.junit.jupiter.api.Assertions;

public class TransferSteps {

    private TransferPage transferPage;
    private HomePage homePage;

    @Dado("el usuario navega a la sección Transfer Funds")
    public void navegarATransferencia() {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.goToTransferFunds();
        transferPage = new TransferPage(DriverManager.getDriver());
    }

    @Cuando("realiza una transferencia de {string} desde la cuenta en posición {int} hacia la posición {int}")
    public void realizarTransferencia(String amount, int from, int to) {
        transferPage.transfer(amount, from, to);
    }

    @Entonces("debería ver el mensaje \"Transfer Complete!\"")
    public void verificarTransferenciaExitosa() {
        Assertions.assertTrue(
                transferPage.isTransferSuccessful(),
                "La transferencia no se completó exitosamente"
        );
    }
}