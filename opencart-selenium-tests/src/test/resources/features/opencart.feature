Feature: Compra en OpenCart
  Como usuario invitado
  Quiero agregar productos al carrito y completar el checkout
  Para confirmar mi pedido exitosamente

  Background:
    Given el usuario está en la página principal de OpenCart

  Scenario: Agregar dos productos al carrito y completar checkout como invitado
    When busca el producto "MacBook"
    And agrega el producto al carrito
    When busca el producto "iPhone"
    And agrega el producto al carrito
    Then el carrito debe mostrar 2 productos

    When el usuario accede al carrito
    And procede al checkout
    And selecciona "Guest Checkout"
    And completa los datos de envío con:
      | campo      | valor            |
      | nombre     | Juan             |
      | apellido   | Perez            |
      | email      | juan@test.com    |
      | telefono   | 0999999999       |
      | direccion  | Av. Amazonas 123 |
      | ciudad     | Quito            |
      | pais       | Ecuador          |
      | region     | Pichincha        |
      | codigopost | 170150           |
    And selecciona método de envío
    And acepta los términos y condiciones
    And confirma la orden
    Then debe ver el mensaje "Your order has been placed!"