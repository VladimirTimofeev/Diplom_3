import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePageObject {

    private final WebDriver driver;

    //Кнопка Личный кабинет
    private final By personalCabinet = By.xpath(".//a[@class='AppHeader_header__link__3D_hX']//p[text()='Личный Кабинет']");
    //Кнопка входа в личный кабинет
    private final By buttonPersonalCabinet = By.xpath(".//button[text()='Войти в аккаунт']");
    //Кнопка Оформить заказ
    private final By buttonPlaceAnOrder = By.xpath(".//button[text()='Оформить заказ']");


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

    public void ckickLoginButton() {
        waitElementClicable(personalCabinet);
        driver.findElement(personalCabinet).click();
    }

    public void ckickButtonPersonalCabinet() {
        waitElementLocated(buttonPersonalCabinet);
        driver.findElement(buttonPersonalCabinet).click();
    }

    public String checkActiveButtonPlaceAnOrder() {
        waitElementLocated(buttonPlaceAnOrder);
        return driver.findElement(buttonPlaceAnOrder).getText();
    }
}
