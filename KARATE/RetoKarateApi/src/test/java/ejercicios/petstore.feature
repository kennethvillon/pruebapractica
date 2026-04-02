Feature: Gestion de Mascotas en Swagger PetStore

  Background:
    # Definimos la URL base para no repetirla en cada paso
    * url 'https://petstore.swagger.io/v2'
    * def petId = 12345
    * def payload = { "id": #(petId), "name": "Snoopy", "status": "available" }

  Scenario: Ciclo de vida completo de una mascota

    # 1. Añadir una mascota a la tienda
    Given path 'pet'
    And request payload
    When method post
    Then status 200
    And match response.name == 'Snoopy'

    # 2. Consultar la mascota ingresada previamente (Búsqueda por ID)
    Given path 'pet', petId
    When method get
    Then status 200
    And match response.id == petId
    And match response.name == 'Snoopy'

    # 3. Actualizar el nombre de la mascota y el status a “Sold”
    # Usamos PUT para actualizar el recurso existente
    Given path 'pet'
    And request { "id": #(petId), "name": "Snoopy Editado", "status": "sold" }
    When method put
    Then status 200
    And match response.status == 'sold'
    And match response.name == 'Snoopy Editado'

    # 4. Consultar la mascota modificada por status (Búsqueda por Status)
    Given path 'pet', 'findByStatus'
    And param status = 'sold'
    When method get
    Then status 200
    # Validamos que en la lista de resultados aparezca nuestro petId
    And match response[*].id contains petId