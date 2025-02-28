import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import object.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

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
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        registrationObject.enterFieldEmail(user);
        registrationObject.enterPassword(user);
        registrationObject.clickButtonEnter();
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
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        registrationObject.enterFieldEmail(user);
        registrationObject.enterPassword(user);
        registrationObject.clickButtonEnter();
        basePageObject.CheckActiveButtonPlaceAnOrder();
    }

    @Test
    @DisplayName("Вход в ЛК по кнопке на странице регистрации")
    public void enterFromButtonInRegistrationPage() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        registrationObject.clickLinkRegistration();
        registrationObject.checkHeaderRegistration();
        registrationObject.ckickButtonEnterFromRegistrationPage();
        registrationObject.enterFieldEmail(user);
        registrationObject.enterPassword(user);
        registrationObject.clickButtonEnter();
        basePageObject.CheckActiveButtonPlaceAnOrder();
    }

    @Test
    @DisplayName("Вход через кнопку на странице восстановления пароля")
    public void enterFromPagePasswordEecovery() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        registrationObject.clickAndCheckLinkRecoverThePassword();
        PasswordRecoveryObject passwordRecoveryObject = new PasswordRecoveryObject(driver);
        passwordRecoveryObject.checkHeader();
        passwordRecoveryObject.clickAndCheckLinkEnter();
        registrationObject.checkHeaderTextEnter();
        registrationObject.enterFieldEmail(user);
        registrationObject.enterPassword(user);
        registrationObject.clickButtonEnter();
        basePageObject.clickAndCheckActiveButtonPlaceAnOrder();
    }

    @Test
    @DisplayName("Проверка перехода из ЛК в конструктор по кнопке Конструктор авторизованнм пользователем")
    public void checkingЕheTransitionFromLkOnConstructorWithAuthorization() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        registrationObject.enterFieldEmail(user);
        registrationObject.enterPassword(user);
        registrationObject.clickButtonEnter();
        basePageObject.clickAndCheckButtonConstructor();
        basePageObject.checkHeaderAssembleBurger();
    }

    @Test
    @DisplayName("Проверка перехода из ЛК в конструктор по логотипу авторизованнм пользователем")
    public void checkingTheЕransitionFromLkByClickOnLogoWithtAuthorization() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickLoginButton();
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        registrationObject.enterFieldEmail(user);
        registrationObject.enterPassword(user);
        basePageObject.clickOnLogo();
        basePageObject.checkHeaderAssembleBurger();
    }

    @Test
    @DisplayName("Проверка кнопки Выход")
    public void checkExitButton() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.checkTextButtonEnterInAccaunt();
        basePageObject.ckickButtonPersonalCabinet();
        RegistrationObject registrationObject = new RegistrationObject(driver);
        registrationObject.checkHeaderTextEnter();
        registrationObject.enterFieldEmail(user);
        registrationObject.enterPassword(user);
        registrationObject.clickButtonEnter();
        basePageObject.CheckActiveButtonPlaceAnOrder();
        basePageObject.ckickLoginButton();
        registrationObject.checkHeaderProfile();
        registrationObject.clickButtonExitAndCheckText();
        registrationObject.checkHeaderTextEnter();
    }

    @Test
    @DisplayName("Проверка работы переходов в меню ингридиентов авторизованного пользователя")
    public void checkItemsMenu() {
        loginFromBasePage();
        BasePageObject basePageObject = new BasePageObject(driver);
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
        deleteUser(getAccessToken(authorizationUser()));
    }
}
