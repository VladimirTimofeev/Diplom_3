import io.qameta.allure.junit4.DisplayName;
import object.BasePageObject;
import object.ConstructorObject;
import object.RegistrationObject;
import object.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class WithoutAuthorizationTest {

    private WebDriver driver;
    private String actualText;

    User user = UserData.expectedUserWithoutIncorrectPassword();

    @Before
    public void prepare() {
        driver = BrowserChoose.createDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Проверка текста ошибки некорректного пароля")
    public void checkErrorPasswordText() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        registrationObject.clickLinkRegistration();
        registrationObject.enterFieldName(user);
        registrationObject.enterRegistrationFieldEmail(user);
        registrationObject.enterPassword(user);
        registrationObject.clickButtonRegistration();
        registrationObject.chechTextErrorPassword();
    }

    @Test
    @DisplayName("Проверка перехода из ЛК в конструктор по кнопке Конструктор неавторизованнм пользователем")
    public void checkingЕheTransitionFromLkOnConstructorWithoutAuthorization() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        basePageObject.clickAndCheckButtonConstructor();
        basePageObject.checkHeaderAssembleBurger();
    }

    @Test
    @DisplayName("Проверка перехода из ЛК в конструктор по логотипу неавторизованнм пользователем")
    public void checkingTheЕransitionFromLkByClickOnLogoWithoutAuthorization() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        basePageObject.clickOnLogo();
        basePageObject.checkHeaderAssembleBurger();
    }

    @Test
    @DisplayName("Проверка работы переходов в меню ингридиентов неавторизованного пользователя")
    public void checkItemsMenu() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        ConstructorObject constructorObject = new ConstructorObject(driver);
        actualText = constructorObject.selectedSauceElement();
        constructorObject.checkSauceScrollMenu();
        constructorObject.selectedActiveElement(actualText);
        actualText = constructorObject.selectedToppingsElement();
        constructorObject.checkToppingScrollMenu();
        basePageObject.checkHeaderAssembleBurger();
        constructorObject.selectedActiveElement(actualText);
        actualText = constructorObject.selectedSauceElement();
        constructorObject.checkSauceScrollMenu();
        basePageObject.checkHeaderAssembleBurger();
        constructorObject.selectedActiveElement(actualText);
        actualText = constructorObject.selectedBreadElement();
        constructorObject.checkBreadScrollMenu();
        basePageObject.checkHeaderAssembleBurger();
        constructorObject.selectedActiveElement(actualText);
    }

    @After
    public void deleteUserAndQuitDriver() {
        driver.quit();
    }
}