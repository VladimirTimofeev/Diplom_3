import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;


public class RegistrationTest {

    private WebDriver driver;

    private PostApi postApi = new PostApi();
    private DeleteApi deleteApi = new DeleteApi();
    private ExtractionToken extractionToken = new ExtractionToken();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    private String accessToken;
    private String actualText;

    User user = UserData.expectedCreateUser();

    Response response;

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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    public void registration() {
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
    }

    @After
    public void deleteUserAndQuitDriver() {
        driver.quit();
        deleteUser(getAccessToken(authorizationUser()));
    }
}
