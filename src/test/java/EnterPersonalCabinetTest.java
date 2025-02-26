import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

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

    @Before
    public void prepare() {
        response = postApi.postCreateUser(user);
        checkStatusCode.checkStatusCode200(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkBodyUserEmail(response, user);
        checkBodyResponse.checkBoduUserName(response, user);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Вход по кнопке на главной странице")
    public void enterInPersonCabinetFromBasePage() {
        BasePageObject basePageObject = new BasePageObject(driver);
        actualText = basePageObject.checkTextButtonEnterInAccaunt();
        assertEquals("Войти в аккаунт", actualText);
        basePageObject.ckickButtonPersonalCabinet();
        Registration registration = new Registration(driver);
        actualText = registration.checkHeaderText();
        assertEquals("Вход", actualText);
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        actualText = registration.clickButtonEnter();
        assertEquals("Войти", actualText);
        actualText = basePageObject.checkActiveButtonPlaceAnOrder();
        assertEquals("Оформить заказ", actualText);
    }

    @Test
    @DisplayName("Вход в ЛК через кнопку Личный кабинет")
    public void enterThroughButtonPersonalCabinet() {
        BasePageObject basePageObject = new BasePageObject(driver);
        actualText = basePageObject.checkTextButtonEnterInAccaunt();
        assertEquals("Войти в аккаунт", actualText);
        actualText = basePageObject.ckickLoginButton();
        assertEquals("Личный Кабинет", actualText);
        Registration registration = new Registration(driver);
        actualText = registration.checkHeaderText();
        assertEquals("Вход", actualText);
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        actualText = registration.clickButtonEnter();
        assertEquals("Войти", actualText);
        actualText = basePageObject.checkActiveButtonPlaceAnOrder();
        assertEquals("Оформить заказ", actualText);
    }

    @Test
    @DisplayName("Вход в ЛК по кнопке на странице регистрации")
    public void enterFromButtonInRegistrationPage() {
        BasePageObject basePageObject = new BasePageObject(driver);
        actualText = basePageObject.checkTextButtonEnterInAccaunt();
        assertEquals("Войти в аккаунт", actualText);
        actualText = basePageObject.ckickLoginButton();
        assertEquals("Личный Кабинет", actualText);
        Registration registration = new Registration(driver);
        actualText = registration.checkHeaderText();
        assertEquals("Вход", actualText);
        registration.clickLinkRegistration();
        actualText = registration.ckickButtonEnterFromRegistrationPage();
        assertEquals("Войти", actualText);
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        actualText = registration.clickButtonEnter();
        assertEquals("Войти", actualText);
        actualText = basePageObject.checkActiveButtonPlaceAnOrder();
        assertEquals("Оформить заказ", actualText);
    }

    @Test
    @DisplayName("Вход через кнопку на странице восстановления пароля")
    public void enterFromPagePasswordEecovery() {
        BasePageObject basePageObject = new BasePageObject(driver);
        actualText = basePageObject.checkTextButtonEnterInAccaunt();
        assertEquals("Войти в аккаунт", actualText);
        actualText = basePageObject.ckickLoginButton();
        assertEquals("Личный Кабинет", actualText);
        Registration registration = new Registration(driver);
        actualText = registration.checkHeaderText();
        assertEquals("Вход", actualText);
        actualText = registration.clickAndCheckLinkRecoverThePassword();
        assertEquals("Восстановить пароль", actualText);
        PasswordRecovery passwordRecovery = new PasswordRecovery(driver);
        actualText = passwordRecovery.checkHeader();
        assertEquals("Восстановление пароля", actualText);
        actualText = passwordRecovery.clickAndCheckLinkEnter();
        assertEquals("Войти", actualText);
        actualText = registration.checkHeaderText();
        assertEquals("Вход", actualText);
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        actualText = registration.clickButtonEnter();
        assertEquals("Войти", actualText);
        actualText = basePageObject.checkActiveButtonPlaceAnOrder();
        assertEquals("Оформить заказ", actualText);
    }

    @After
    public void deleteUserAndQuitDriver() {
        driver.quit();
        deleteUser(getAccessToken(authorizationUser()));
    }
}
