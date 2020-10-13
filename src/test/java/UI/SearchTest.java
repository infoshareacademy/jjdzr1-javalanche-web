package UI;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;

public class SearchTest extends BaseClass implements Setup {

    @ParameterizedTest
    @CsvSource({
            TEST_FIRST_NAME,
            TEST_LAST_NAME
    })
    public void searchEmployeeByFirstName(String testValue) {
        beforeLogin();
        driver.findElement(By.id("myInput")).sendKeys(testValue);
        String text = driver.findElement(By.xpath("//*[@id=\"calendarTable\"]/tr[1]/td[1]/button")).getText();
        Assertions.assertThat(text)
                .isNotEmpty()
                .contains(testValue);
    }

    @Override
    @After
    public void tearDown() {
        driver.close();
    }
}
