package UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseClass {
    public static final String BASE_URL = "http://localhost:8080/login";
    public static final String TEST_LOGIN = "admin@admin.pl";
    public static final String TEST_PASSWORD= "admin";
    static WebDriver driver = new ChromeDriver();

}
