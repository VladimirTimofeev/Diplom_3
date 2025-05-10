package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

import java.time.Duration;

public class RegistrationObject {

    private final WebDriver driver;

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
    //Заголовок Регистрация
    private final By headerRegistration = By.xpath(".//h2[text()='Регистрация']");
    //Ошибка некорректного пароля
    private final By errorPassword = By.xpath(".//p[text()='Некорректный пароль']");
    //Кнопка войти
    private final By buttonEnter = By.xpath(".//button[text()='Войти']");
    //Надпись Вход
    private final By heading = By.xpath("//*[@id=\"root\"]/div/main/div/h2");
    //Ссылка Восстановить пароль
    private final By linkRecoverThePassword = By.xpath(".//a[text()='Восстановить пароль']");
    //Заголовоу Профиль
    private final By headerProfile = By.xpath(".//a[text()='Профиль']");
    //Кнопка выход
    private final By buttonExit = By.xpath(".//button[text()='Выход']");

    public RegistrationObject(WebDriver driver) {
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

    //Нажатие и проверка текста ссылки Зарегистрироваться
    public void clickLinkRegistration() {
        waitElementClicable(linkRegistration);
        assertEquals("Зарегистрироваться", driver.findElement(linkRegistration).getText());
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

    //Нажате на кнопку Зарегистрироваться и проверка текста кнопки
    public void clickButtonRegistration() {
        waitElementLocated(buttonRegistration);
        assertEquals("Зарегистрироваться", driver.findElement(buttonRegistration).getText());
        driver.findElement(buttonRegistration).click();
    }

    //Проверка текста заголовка Регистрация
    public void checkHeaderRegistration() {
        waitElementLocated(headerRegistration);
        assertEquals("Регистрация", driver.findElement(headerRegistration).getText());
    }

    //Проверка текста ошибки некорректного пароля
    public void chechTextErrorPassword() {
        waitElementLocated(errorPassword);
        assertEquals("Некорректный пароль", driver.findElement(errorPassword).getText());
    }

    //Нажатие кнопки Войти и проверка текста кнопки
    public void clickButtonEnter() {
        waitElementLocated(buttonEnter);
        assertEquals("Войти", driver.findElement(buttonEnter).getText());
        driver.findElement(buttonEnter).click();
    }

    //Нажатие и проверка текста на кнопке Вход на странице регистрации
    public void ckickButtonEnterFromRegistrationPage() {
        waitElementClicable(linkEnter);
        assertEquals("Войти", driver.findElement(linkEnter).getText());
        driver.findElement(linkEnter).click();
    }

    //Получение текста заголовка Вход
    public void checkHeaderTextEnter() {
        waitElementLocated(heading);
        assertEquals("Вход", driver.findElement(heading).getText());
    }

    //Нажатие ссылки Восстановление пароля и проверка текста кнопки
    public void clickAndCheckLinkRecoverThePassword() {
        waitElementLocated(linkRecoverThePassword);
        assertEquals("Восстановить пароль", driver.findElement(linkRecoverThePassword).getText());
        driver.findElement(linkRecoverThePassword).click();
    }

    //Проверка текста заголовка Профиль
    public void checkHeaderProfile() {
        waitElementLocated(headerProfile);
        assertEquals("Профиль", driver.findElement(headerProfile).getText());
    }

    //Нажатие на кнопку Выход и проверка текста кнопки
    public void clickButtonExitAndCheckText() {
        waitElementLocated(buttonExit);
        assertEquals("Выход", driver.findElement(buttonExit).getText());
        driver.findElement(buttonExit).click();
    }
}