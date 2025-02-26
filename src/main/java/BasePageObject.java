import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePageObject {

    private final WebDriver driver;

    //Кнопка Личный кабинет
    private final By personalCabinet = By.xpath(".//a[@class='AppHeader_header__link__3D_hX']//p[text()='Личный Кабинет']");


    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void ckickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(personalCabinet));
        driver.findElement(personalCabinet).click();
    }
}
