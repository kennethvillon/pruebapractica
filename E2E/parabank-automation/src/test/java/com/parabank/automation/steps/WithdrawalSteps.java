package com.parabank.automation.steps;

import com.parabank.automation.pages.BillPayPage;
import com.parabank.automation.pages.HomePage;
import com.parabank.automation.utils.DriverManager;
import io.cucumber.java.es.*;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class WithdrawalSteps {

    private BillPayPage billPayPage;
    private HomePage homePage;

    @Dado("el usuario navega a la sección Bill Pay")
    public void navegarABillPay() {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.goToBillPay();
        billPayPage = new BillPayPage(DriverManager.getDriver());
    }

    @Cuando("completa el formulario de pago con los siguientes datos:")
    public void completarFormularioPago(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        billPayPage.fillPaymentForm(
                data.get("payee"),
                data.get("street"),
                data.get("city"),
                data.get("state"),
                data.get("zip"),
                data.get("phone"),
                data.get("account"),
                data.get("amount")
        );
    }

    @Y("hace clic en Send Payment")
    public void clickSendPayment() {
        billPayPage.sendPayment();
    }

    @Entonces("debería ver el mensaje {string}")
    public void verificarMensaje(String mensajeEsperado) {
        if (mensajeEsperado.contains("Bill Payment")) {
            Assertions.assertTrue(
                    billPayPage.isPaymentSuccessful(),
                    "No se confirmó el pago/retiro"
            );
        }
    }
}