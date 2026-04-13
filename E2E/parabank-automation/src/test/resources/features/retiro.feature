@retiro
Feature: Realizar un retiro via Bill Pay

  Background:
    Given que el usuario esta autenticado como "john" con contrasena "demo"
    And el usuario navega a la pagina de Bill Pay

  @retiro_exitoso
  Scenario: Retiro exitoso mediante Bill Pay
    When el usuario completa el formulario de pago:
      | campo        | valor          |
      | beneficiario | Empresa ABC    |
      | direccion    | 789 Pay Street |
      | ciudad       | New York       |
      | estado       | NY             |
      | codigoPostal | 10001          |
      | telefono     | 5551234567     |
      | cuenta       | 13344          |
      | monto        | 50             |
    And el usuario envia el pago
    Then el sistema confirma el pago exitoso al beneficiario "Empresa ABC"