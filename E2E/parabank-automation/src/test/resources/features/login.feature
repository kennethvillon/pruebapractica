Feature: Login en ParaBank
Como usuario registrado
Quiero iniciar sesion en el portal
Para acceder a mis cuentas bancarias

Scenario Outline: Login con credenciales "<tipo>"
Given el usuario esta en la pagina principal de ParaBank
When ingresa el usuario "<username>" y contrasena "<password>"
And hace clic en Log In
Then el resultado del login deberia ser "<resultado>"

Examples:
| tipo    | username | password | resultado |
| valido  | john     | demo     | exitoso   |
| invalido| wrong    | wrong    | fallido   |