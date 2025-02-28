import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnterPersonalCabinetTest {

    private WebDriver driver;
    Response response;
    PostApi postApi = new PostApi();
    DeleteApi deleteApi = new DeleteApi();
    CheckStatusCode checkStatusCode = new CheckStatusCode();
    CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    ExtractionToken extractionToken = new ExtractionToken();

    User user = UserData.expectedCreateUser();
    String accessToken;
    String actualText;

    @Step("авторизация пользователя")
    public Response authorizationUser() {
        return response = postApi.authorizationUser(UserData.expectedCreateUser());
    }

    @Step("извлечение токена")
    public String getAccessToken(Response response) {
        return accessToken = extractionToken.extractionToken(response);
    }

    @Step("Удаелние пользователя")
    public void deleteUser(String accessToken) {
        response = deleteApi.deleteUser(accessToken);
        checkStatusCode.checkStatusCode202(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkMessageDeleteUser(response);
    }

    @Step("Вход по кнопке на главной странице")
    public void loginFromBasePage() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickButtonPersonalCabinet();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonEnter();
        basePageObject.CheckActiveButtonPlaceAnOrder();
    }

    @Before
    public void prepare() {
        response = postApi.postCreateUser(user);
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkBodyUserEmail(response, user);
        checkBodyResponse.checkBoduUserName(response, user);
        driver = BrowserChoose.createDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Вход по кнопке на главной странице")
    public void enterInPersonCabinetFromBasePage() {
        loginFromBasePage();
    }

    @Test
    @DisplayName("Вход в ЛК через кнопку Личный кабинет")
    public void enterThroughButtonPersonalCabinet() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonEnter();
        basePageObject.CheckActiveButtonPlaceAnOrder();
    }

    @Test
    @DisplayName("Вход в ЛК по кнопке на странице регистрации")
    public void enterFromButtonInRegistrationPage() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        registration.clickLinkRegistration();
        registration.checkHeaderRegistration();
        registration.ckickButtonEnterFromRegistrationPage();
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonEnter();
        basePageObject.CheckActiveButtonPlaceAnOrder();
    }

    @Test
    @DisplayName("Вход через кнопку на странице восстановления пароля")
    public void enterFromPagePasswordEecovery() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        registration.clickAndCheckLinkRecoverThePassword();
        PasswordRecovery passwordRecovery = new PasswordRecovery(driver);
        passwordRecovery.checkHeader();
        passwordRecovery.clickAndCheckLinkEnter();
        registration.checkHeaderTextEnter();
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonEnter();
        basePageObject.clickAndCheckActiveButtonPlaceAnOrder();
    }

    @Test
    @DisplayName("Проверка перехода из ЛК в конструктор по кнопке Конструктор авторизованнм пользователем")
    public void checkingЕheTransitionFromLkOnConstructorWithAuthorization() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonEnter();
        basePageObject.clickAndCheckButtonConstructor();
        basePageObject.checkHeaderAssembleBurger();
    }

    @Test
    @DisplayName("Проверка перехода из ЛК в конструктор по логотипу авторизованнм пользователем")
    public void checkingTheЕransitionFromLkByClickOnLogoWithtAuthorization() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        basePageObject.clickOnLogo();
        basePageObject.checkHeaderAssembleBurger();
    }

    @Test
    @DisplayName("Проверка кнопки Выход")
    public void checkExitButton() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickButtonPersonalCabinet();
        Registration registration = new Registration(driver);
        registration.checkHeaderTextEnter();
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonEnter();
        basePageObject.CheckActiveButtonPlaceAnOrder();
        basePageObject.ckickLoginButton();
        registration.checkHeaderProfile();
        registration.clickButtonExitAndCheckText();
        registration.checkHeaderTextEnter();
    }

    @Test
    @DisplayName("Проверка работы переходов в меню ингридиентов авторизованного пользователя")
    public void checkItemsMenu() {
        loginFromBasePage();
        BasePageObject basePageObject = new BasePageObject(driver);
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
        deleteUser(getAccessToken(authorizationUser()));
    }
}
