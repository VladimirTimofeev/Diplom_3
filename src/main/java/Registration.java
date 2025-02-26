import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Registration {

    private final WebDriver driver;

    private String actualText;

    //Ссылка Зарегистрироваться
    private final By linkRegistration = By.xpath(".//a[text()='Зарегистрироваться']");
    //Ссылка Войти
    private final By linkEnter = By.xpath(".//a[text()='Войти']");
    //Поле для ввода имени
    private final By nameField = By.xpath("(.//input[@name='name'])[1]");
    //Поле для ввода email при регистрации
    private final By emailRegistrationField = By.xpath("(.//input[@name='name'])[2]");
    //Поле для ввода email при регистрации
    private final By emailEnterField = By.xpath(".//input[@name='name']");
    //Поле для ввода пароля
    private final By passswordField = By.xpath(".//input[@name='Пароль']");
    //Кнопка Зарегистрироваться
    private final By buttonRegistration = By.xpath("//button[text()='Зарегистрироваться']");
    //Ошибка некорректного пароля
    private final By errorPassword = By.xpath(".//p[text()='Некорректный пароль']");
    //Кнопка войти
    private final By buttonEnter = By.xpath(".//button[text()='Войти']");
    //Надпись Вход
    private final By heading = By.xpath("//*[@id=\"root\"]/div/main/div/h2");
    //Ссылка Восстановить пароль
    private final By linkRecoverThePassword = By.xpath(".//a[text()='Восстановить пароль']");

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание кликабельности элемента на странице
    public void waitElementClicable(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    //Ожидание появение элемента на странице
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
    public void enterRegistrationFieldEmail(User user) {
        waitElementLocated(emailRegistrationField);
        driver.findElement(emailRegistrationField).click();
        driver.findElement(emailRegistrationField).sendKeys(user.getEmail());
    }

    //Введение email в поле
    public void enterFieldEmail(User user) {
        waitElementLocated(emailEnterField);
        driver.findElement(emailEnterField).click();
        driver.findElement(emailEnterField).sendKeys(user.getEmail());
    }

    //Введение пароля в поле
    public void enterPassword(User user) {
        waitElementLocated(passswordField);
        driver.findElement(passswordField).click();
        driver.findElement(passswordField).sendKeys(user.getPassword());
    }

    //Нажате на кнопку Зарегистрироваться
    public String clickButtonRegistration() {
        waitElementLocated(buttonRegistration);
        String actualText = driver.findElement(buttonRegistration).getText();
        driver.findElement(buttonRegistration).click();
        return actualText;
    }

    //Проверка текста ошибки некорректного пароля
    public String chechTextErrorPassword() {
        waitElementLocated(errorPassword);
        String actualText = driver.findElement(errorPassword).getText();
        return actualText;
    }

    //Нажатие кнопки Войти
    public String clickButtonEnter() {
        waitElementLocated(buttonEnter);
        actualText = driver.findElement(buttonEnter).getText();
        driver.findElement(buttonEnter).click();
        return actualText;
    }

    //Нажатие на кнопку Вход на странице регистрации
    public String ckickButtonEnterFromRegistrationPage() {
        waitElementClicable(linkEnter);
        actualText = driver.findElement(linkEnter).getText();
        driver.findElement(linkEnter).click();
        return actualText;
    }

    //Получение текста заголовка Вход
    public String checkHeaderText() {
        waitElementLocated(heading);
        return driver.findElement(heading).getText();
    }

    //Проверка и нажатие ссылки Восстановление пароля
    public String clickAndCheckLinkRecoverThePassword() {
        waitElementLocated(linkRecoverThePassword);
        String actualText = driver.findElement(linkRecoverThePassword).getText();
        driver.findElement(linkRecoverThePassword).click();
        return actualText;
    }
}