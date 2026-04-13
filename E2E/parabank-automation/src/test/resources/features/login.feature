@login
Feature: Login en el sistema


  Scenario: Login exitoso con credenciales validas
    Given que el usuario navega a la pagina de login
    When el usuario ingresa el usuario "john" y contrasena "demo"
    And el usuario hace clic en Ingresar
    Then el siste@login_exitosoma muestra la vista de resumen de cuentas

  @login_fallido
  Scenario: Login fallido con credenciales incorrectas
    Given que el usuario navega a la pagina de login
    When el usuario ingresa el usuario "invalido" y contrasena "incorrecta"
    And el usuario hace clic en Ingresar
    Then el sistema muestra un mensaje de error de credenciales