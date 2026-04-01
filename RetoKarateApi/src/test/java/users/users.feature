Feature: Ejercicio 2 - Gestion de Usuarios con Autenticacion

  Background:
    * url 'https://reqres.in/api'

  Scenario: Flujo completo de usuario
    # 1. Consultar usuario por ID
    Given path 'users', 2
    When method get
    Then status 200

    # 2. Actualizar datos del usuario
    Given path 'users', 2
    And request { "name": "Kenn", "job": "QA Automation" }
    When method put
    Then status 200
    And match response.name == 'Kenn'

    # 3. Eliminar usuario
    Given path 'users', 2
    When method delete
    Then status 204

    # 4. Consultar lista y verificar ausencia
    Given path 'users'
    When method get
    Then status 200
    And match response.data[*].id !contains 2