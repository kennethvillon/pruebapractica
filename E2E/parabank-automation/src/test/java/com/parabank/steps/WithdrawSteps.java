package com.parabank.steps;

import com.parabank.pages.BillPayPage;
import com.parabank.pages.BillPayConfirmationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class WithdrawSteps {

    private BillPayPage billPayPage = new BillPayPage();
    private BillPayConfirmationPage confirmationPage;
    private String accountNumber;
    private String expectedPayeeName;

    @And("el usuario navega a la pagina de Bill Pay")
    public void navigateToBillPay() {
        this.accountNumber = billPayPage.getFirstAccountNumber();
        System.out.println("Cuenta a usar: " + this.accountNumber);
    }

    @When("el usuario completa el formulario de pago:")
    public void fillPayForm(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        this.expectedPayeeName = data.get("beneficiario");
        String monto = data.get("monto");
        billPayPage.fillPaymentForm(
                expectedPayeeName,
                data.get("direccion"),
                data.get("ciudad"),
                data.get("estado"),
                data.get("codigoPostal"),
                data.get("telefono"),
                this.accountNumber,
                monto
        );
    }

    @And("el usuario envia el pago")
    public void sendPayment() {
        confirmationPage = billPayPage.submitPayment();
    }

    @Then("el sistema confirma el pago exitoso al beneficiario {string}")
    public void verifyPayment(String expectedPayeeNameFromFeature) {
        assertTrue(confirmationPage.isConfirmationDisplayed(), "No se mostró la confirmación");
        String panelText = confirmationPage.getConfirmationText();
        assertTrue(panelText.contains(this.expectedPayeeName),
                "El beneficiario '" + this.expectedPayeeName + "' no aparece en: " + panelText);
    }
}