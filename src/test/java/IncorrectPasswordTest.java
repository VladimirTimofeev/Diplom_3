import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class IncorrectPasswordTest {

    private WebDriver driver;
    private String actualErros;
    private String actualText;

    User user = UserData.expectedUserWithoutIncorrectPassword();

    @Before
    public void prepare() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Проверка текста ошибки некорректного пароля")
    public void checkErrorPasswordText() {
        BasePageObject basePageObject = new BasePageObject(driver);
        actualText = basePageObject.checkTextButtonEnterInAccaunt();
        assertEquals("Войти в аккаунт", actualText);
        actualText = basePageObject.ckickLoginButton();
        assertEquals("Личный Кабинет", actualText);
        Registration registration = new Registration(driver);
        actualText = registration.checkHeaderText();
        assertEquals("Вход", actualText);
        registration.clickLinkRegistration();
        registration.enterFieldName(user);
        registration.enterRegistrationFieldEmail(user);
        registration.enterPassword(user);
        actualText = registration.clickButtonRegistration();
        assertEquals("Зарегистрироваться", actualText);
        actualErros = registration.chechTextErrorPassword();
        assertEquals("Некорректный пароль", actualErros);
    }

    @After
    public void deleteUserAndQuitDriver() {
        driver.quit();
    }
}