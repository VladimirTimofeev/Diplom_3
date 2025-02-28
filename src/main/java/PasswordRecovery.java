import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

import java.time.Duration;

public class PasswordRecovery {

    private final WebDriver driver;

    //Заголовок
    private final By headerText = By.xpath("//*[@id=\"root\"]/div/main/div/h2");
    //Ссылка Войти
    private final By linkEnter = By.xpath(".//a[text()='Войти']");


    public PasswordRecovery(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание появение элемента на странице
    public void waitElementLocated(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    //Проверка текста заголовка Восстановление пароля
    public void checkHeader() {
        waitElementLocated(headerText);
        assertEquals("Восстановление пароля", driver.findElement(headerText).getText());
    }

    //Проверка текста и нажатие ссылки Войти
    public void clickAndCheckLinkEnter() {
        waitElementLocated(linkEnter);
        assertEquals("Войти", driver.findElement(linkEnter).getText());
        driver.findElement(linkEnter).click();
    }
}