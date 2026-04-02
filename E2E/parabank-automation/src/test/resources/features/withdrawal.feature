# language: es
Feature: Retiro de fondos en ParaBank
Como usuario registrado
Quiero retirar fondos de mi cuenta
Para disponer de mi dinero

Scenario Outline: Retiro de "<monto>" dolares
Given el usuario esta autenticado en ParaBank
And navega a la pagina de retiro
When ingresa el monto a retirar "<monto>"
And confirma el retiro
Then el resultado del retiro deberia ser "<resultado>"

Examples:
| monto | resultado |
| 50    | exitoso   |
| 99999 | fallido   |