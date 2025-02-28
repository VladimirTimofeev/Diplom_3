import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WithoutAuthorizationTest {

    private WebDriver driver;
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
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        registration.clickLinkRegistration();
        registration.enterFieldName(user);
        registration.enterRegistrationFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonRegistration();
        registration.chechTextErrorPassword();
    }

    @Test
    @DisplayName("Проверка перехода из ЛК в конструктор по кнопке Конструктор неавторизованнм пользователем")
    public void checkingЕheTransitionFromLkOnConstructorWithoutAuthorization() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        basePageObject.clickAndCheckButtonConstructor();
        basePageObject.checkHeaderAssembleBurger();
    }

    @Test
    @DisplayName("Проверка перехода из ЛК в конструктор по логотипу неавторизованнм пользователем")
    public void checkingTheЕransitionFromLkByClickOnLogoWithoutAuthorization() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        basePageObject.clickOnLogo();
        basePageObject.checkHeaderAssembleBurger();
    }

    @Test
    @DisplayName("Проверка работы переходов в меню ингридиентов неавторизованного пользователя")
    public void checkItemsMenu() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        Constructor constructor = new Constructor(driver);
        actualText = constructor.selectedSauceElement();
        constructor.checkSauceScrollMenu();
        constructor.selectedActiveElement(actualText);
        actualText = constructor.selectedToppingsElement();
        constructor.checkToppingScrollMenu();
        basePageObject.checkHeaderAssembleBurger();
        constructor.selectedActiveElement(actualText);
        actualText = constructor.selectedSauceElement();
        constructor.checkSauceScrollMenu();
        basePageObject.checkHeaderAssembleBurger();
        constructor.selectedActiveElement(actualText);
        actualText = constructor.selectedBreadElement();
        constructor.checkBreadScrollMenu();
        basePageObject.checkHeaderAssembleBurger();
        constructor.selectedActiveElement(actualText);
    }

    @After
    public void deleteUserAndQuitDriver() {
        driver.quit();
    }
}