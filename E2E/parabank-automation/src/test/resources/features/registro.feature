Feature: Registro en el sistema

  Background:
    Given que el usuario navega a la pagina de registro

  @registro_exitoso
  Scenario: Registro exitoso con datos validos
    When el usuario completa el formulario con los siguientes datos:
      | campo        | valor        |
      | nombre       | Juan         |
      | apellido     | Perez        |
      | direccion    | 123 Main St  |
      | ciudad       | New York     |
      | estado       | NY           |
      | codigoPostal | 10001        |
      | telefono     | 5551234567   |
      | ssn          | 123456789    |
      | usuario      | juan_test_   |
      | contrasena   | Test1234     |
    And el usuario hace clic en Registrarse
    Then el sistema muestra el mensaje de registro exitoso

  @registro_duplicado
  Scenario: Registro con usuario ya existente
    When el usuario completa el formulario con los siguientes datos:
      | campo        | valor        |
      | nombre       | Maria        |
      | apellido     | Lopez        |
      | direccion    | 456 Oak Ave  |
      | ciudad       | New York     |
      | estado       | NY           |
      | codigoPostal | 10001        |
      | telefono     | 5559876543   |
      | ssn          | 987654321    |
      | usuario      | DUPLICATE    |
      | contrasena   | Test1234     |
    And el usuario hace clic en Registrarse
    Then el sistema muestra un error de usuario duplicado