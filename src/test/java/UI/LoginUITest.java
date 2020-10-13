package UI;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class LoginUITest extends BaseClass implements Setup {

    @Test
    public void login() {
        beforeLogin();
        boolean isVisible = driver.findElement(By.id("menu-toggle")).isDisplayed();
        Assertions.assertTrue(isVisible);
    }


    @Override
    @After
    public void tearDown() {
        driver.close();
    }
}
