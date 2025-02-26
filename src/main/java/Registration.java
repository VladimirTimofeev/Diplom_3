import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Registration {

    private final WebDriver driver;

    //Ссылка Зарегистрироваться
    private final By linkRegistration = By.xpath(".//a[text()='Зарегистрироваться']");
    //Поле для ввода имени
    private final By nameField = By.xpath("(.//input[@name='name'])[1]");
    //Поле для ввода email
    private final By emailField = By.xpath("(.//input[@name='name'])[2]");
    //Поле для ввода пароля
    private final By passswordField = By.xpath(".//input[@name='Пароль']");
    //Кнопка Зарегистрироваться
    private final By buttonRegistration = By.xpath("//button[text()='Зарегистрироваться']");
    //Ошибка некорректного пароля
    private final By errorPassword = By.xpath(".//p[text()='Некорректный пароль']");

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    public void waitElementClicable(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementLocated(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    //Нажатие на сссылку Зарегистрироваться
    public void clickLinkRegistration() {
        waitElementClicable(linkRegistration);
        driver.findElement(linkRegistration).click();
    }

    //Введение имени в поля
    public void enterFieldName(User user) {
        waitElementLocated(nameField);
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(user.getName());
    }

    //Введение email в поле
    public void enterFieldEmail(User user) {
        waitElementLocated(emailField);
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(user.getEmail());
    }

    //Введение пароля в поле
    public void enterPassword(User user) {
        waitElementLocated(passswordField);
        driver.findElement(passswordField).click();
        driver.findElement(passswordField).sendKeys(user.getPassword());
    }

    //Нажате на кнопку Зарегистрироваться
    public void clickButtonRegistration() {
        waitElementLocated(buttonRegistration);
        driver.findElement(buttonRegistration).click();
    }

    //Проверка текста ошибки некорректного пароля
    public String chechTextErrorPassword() {
        waitElementLocated(errorPassword);
        String actualText = driver.findElement(errorPassword).getText();
        return actualText;
    }
}