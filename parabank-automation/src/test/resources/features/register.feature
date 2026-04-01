# language: es
Feature: Registro en ParaBank
Como usuario nuevo
Quiero registrarme en el portal
Para acceder a mis cuentas bancarias

Scenario Outline: Registro con datos "<tipo>"
Given el usuario esta en la pagina de registro
When ingresa sus datos personales "<nombre>" "<apellido>" "<username>" "<password>"
And hace clic en Register
Then el resultado del registro deberia ser "<resultado>"

Examples:
| tipo    | nombre | apellido | username  | password  | resultado |
| valido  | John   | Doe      | john_new  | demo1234  | exitoso   |
| invalido| John   | Doe      | john      | demo      | fallido   |