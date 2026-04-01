# language: es
Feature: Transferencia de fondos en ParaBank
Como usuario registrado
Quiero transferir fondos entre cuentas
Para gestionar mi dinero

Scenario Outline: Transferencia de "<monto>" dolares
Given el usuario esta autenticado en ParaBank
And navega a la pagina de transferencia
When ingresa el monto "<monto>"
And selecciona la cuenta origen y destino
And hace clic en Transfer
Then el resultado de la transferencia deberia ser "<resultado>"

Examples:
| monto | resultado |
| 100   | exitoso   |
| 0     | fallido   |