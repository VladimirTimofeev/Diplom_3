import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecovery {

    private final WebDriver driver;

    private String actualText;

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

    //Проверка заголовка
    public String checkHeader() {
        waitElementLocated(headerText);
        return driver.findElement(headerText).getText();
    }

    //Проверка и нажатие ссылки Войти
    public String clickAndCheckLinkEnter() {
        waitElementLocated(linkEnter);
        actualText = driver.findElement(linkEnter).getText();
        driver.findElement(linkEnter).click();
        return actualText;
    }
}
