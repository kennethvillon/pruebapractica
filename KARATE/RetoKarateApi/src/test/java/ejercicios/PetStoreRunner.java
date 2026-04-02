package ejercicios;

import com.intuit.karate.junit5.Karate;

class PetStoreRunner {

    @Karate.Test
    Karate testPetStore() {
        // Ejecuta solo el archivo petstore.feature
        return Karate.run("petstore").relativeTo(getClass());
    }
}