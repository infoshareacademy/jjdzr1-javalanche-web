package UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseClass {
    public static final String BASE_URL = "http://localhost:8080/login";
    public static final String TEST_LOGIN = "admin@admin.pl";
    public static final String TEST_PASSWORD= "admin";
    public static final String TEST_FIRST_NAME= "admin";
    public static final String TEST_LAST_NAME= "admin";
    public static WebDriver driver = new ChromeDriver();

    public static void beforeLogin() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.findElement(By.name("username")).sendKeys(TEST_LOGIN);
        driver.findElement(By.name("password")).sendKeys(TEST_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"login\"]/form/fieldset/div/span[2]/input")).click();
    }

}
