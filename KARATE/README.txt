Este repositorio contiene una suite de pruebas para dos APIs distintas:

ReqRes: Pruebas de CRUD de usuarios donde corregimos errores de acceso (403).

PetStore: Flujo real de una mascota, desde su creación hasta su eliminación.

-Stack Tecnológico
Lenguaje: Java 21 (Amazon Corretto).

Framework: Karate DSL 1.4.1.

Build Tool: Maven.

Runner: JUnit 5.

-¿Cómo ejecutar las pruebas?
Seleccionando click derecho en la clase y por consiguietne en RUN "UserRunner" o
desde la terminal en la raíz del proyecto, usa: mvn test -Dtest=UserRunner

Adicional para ver el reporte en html, se genera al momento de ejecutar las pruebas en la carpeta target/karate-reports/karate-summary.html 
Luego clic derecho OPEN IN --- BROWSER ---  y el navegador que se desee. Ahi clic en "users/users.feature" para visualizar el contenido
o por su defecto de casi al final ejecutar la prueba sale un link que reedirecciona al mismo link.