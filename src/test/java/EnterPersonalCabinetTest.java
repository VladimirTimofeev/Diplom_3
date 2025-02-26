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
        basePageObject.ckickButtonPersonalCabinet();
        Registration registration = new Registration(driver);
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonEnter();
        actualText = basePageObject.checkActiveButtonPlaceAnOrder();
        assertEquals("Оформить заказ", actualText);
    }

    @Test
    @DisplayName("Вход в ЛК через кнопку Личный кабинет")
    public void enterThroughButtonPersonalCabinet() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonEnter();
        actualText = basePageObject.checkActiveButtonPlaceAnOrder();
        assertEquals("Оформить заказ", actualText);
    }

    @Test
    @DisplayName("Вход в ЛК по кнопке на странице регистрации")
    public void enterFromButtonInRegistrationPage() {
        BasePageObject basePageObject = new BasePageObject(driver);
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.clickLinkRegistration();
        registration.ckickButtonEnterFromRegistrationPage();
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonEnter();
        actualText = basePageObject.checkActiveButtonPlaceAnOrder();
        assertEquals("Оформить заказ", actualText);
    }



    @After
    public void deleteUserAndQuitDriver() {
        driver.quit();
        deleteUser(getAccessToken(authorizationUser()));
    }
}
