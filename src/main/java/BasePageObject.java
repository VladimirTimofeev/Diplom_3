import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class BasePageObject {

    private final WebDriver driver;

    //Кнопка Личный кабинет
    private final By personalCabinet = By.xpath("//p[text()='Личный Кабинет']");
    //Кнопка входа в личный кабинет
    private final By buttonEnterInAccaunt = By.xpath(".//button[text()='Войти в аккаунт']");
    //Кнопка Оформить заказ
    private final By buttonPlaceAnOrder = By.xpath(".//button[text()='Оформить заказ']");
    //Кнопка конструктор
    private final By buttonConstructor = By.xpath(".//p[text()='Конструктор']");
    //Заголовок соберите бургер
    private final By headerAssembleBurger = By.xpath("//h1[text()='Соберите бургер']");
    //логотип
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");


    public BasePageObject(WebDriver driver) {
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

    //Нажатие и проверка текста кнопки Личный кабинет
    public void ckickLoginButton() {
        waitElementClicable(personalCabinet);
        assertEquals("Личный Кабинет", driver.findElement(personalCabinet).getText());
        driver.findElement(personalCabinet).click();
    }

    //Нажать на кнопку Войти в аккаунт и проверка текста кнопки
    public void ckickButtonPersonalCabinet() {
        waitElementLocated(buttonEnterInAccaunt);
        assertEquals("Войти в аккаунт", driver.findElement(buttonEnterInAccaunt).getText());
        driver.findElement(buttonEnterInAccaunt).click();
    }

    //Проверка текста кнопки Оформить заказ
    public void CheckActiveButtonPlaceAnOrder() {
        waitElementLocated(buttonPlaceAnOrder);
        assertEquals("Оформить заказ", driver.findElement(buttonPlaceAnOrder).getText());
    }

    //Нажатие на кнопку Оформить заказ и проверка тектв кнопки
    public void clickAndCheckActiveButtonPlaceAnOrder() {
        waitElementLocated(buttonPlaceAnOrder);
        assertEquals("Оформить заказ", driver.findElement(buttonPlaceAnOrder).getText());
        driver.findElement(buttonPlaceAnOrder).click();
    }

    //Проверка текста кнопки Войти в аккаунт и проверка текста кнопки
    public void checkTextButtonEnterInAccaunt() {
        waitElementLocated(buttonEnterInAccaunt);
        assertEquals("Войти в аккаунт", driver.findElement(buttonEnterInAccaunt).getText());
    }

    //Нажатие и проверка текста кнопки Конструктор
    public void clickAndCheckButtonConstructor() {
        waitElementLocated(buttonConstructor);
        assertEquals("Конструктор", driver.findElement(buttonConstructor).getText());
        driver.findElement(buttonConstructor).click();
    }

    //Проверка текста заголовка Соберите бургер
    public void checkHeaderAssembleBurger() {
        waitElementLocated(headerAssembleBurger);
        assertEquals("Соберите бургер", driver.findElement(headerAssembleBurger).getText());
    }

    //Нажатие на логотип
    public void clickOnLogo() {
        waitElementLocated(logo);
        driver.findElement(logo).click();
    }
}
