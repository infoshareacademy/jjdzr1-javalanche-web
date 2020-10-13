package UI;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;


public class LoginUITest extends BaseClass implements Setup {

    @Test
    public void login() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.findElement(By.name("username")).sendKeys(TEST_LOGIN);
        driver.findElement(By.name("password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"login\"]/form/fieldset/div/span[2]/input")).click();
        boolean isVisible = driver.findElement(By.id("menu-toggle")).isDisplayed();
        Assertions.assertTrue(isVisible);
    }

    @Override
    public void setup() {
    }

    @Override
    @After
    public void tearDown() {
    driver.close();
    }
}
