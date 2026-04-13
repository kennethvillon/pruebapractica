@transferencia
Feature: Transferir dinero entre dos cuentas

  Background:
    Given que el usuario esta autenticado como "john" con contrasena "demo"
    And el usuario navega a la pagina de transferencia

  @transferencia_exitosa
  Scenario: Transferencia exitosa entre cuentas
    When el usuario ingresa el monto "100"
    And el usuario selecciona la cuenta origen
    And el usuario selecciona la cuenta destino
    And el usuario confirma la transferencia
    Then el sistema muestra la confirmacion de transferencia exitosa