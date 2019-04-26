package ru.srqa.training.selenium;

import org.junit.Test;
import ru.srqa.training.selenium.model.UserData;
import util.PropertyLoader;

import java.util.UUID;

public class SignUpTest extends TestBase {
    @Test
    public void testSignUp() {
        driver.get(PropertyLoader.loadProperty("mainpage.url"));
        String email = UUID.randomUUID().toString().substring(0,5) + "user@gmail.com";

        signUp(new UserData().withFisrtName("Maria").withLastName("Murashkina").withAddress("4970 El Camino Real # 110")
                .withPostcode("94022").withCity("Los Altos").withCountry("US").withState("CA")
                .withEmail(email).withPhone("9237600746"));
        logout();
        loginToUserProfile(email, PropertyLoader.loadProperty("user.password"));
    }
}
