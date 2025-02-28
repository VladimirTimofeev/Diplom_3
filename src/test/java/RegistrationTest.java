import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class RegistrationTest {

    private WebDriver driver;

    private PostApi postApi = new PostApi();
    private DeleteApi deleteApi = new DeleteApi();
    private ExtractionToken extractionToken = new ExtractionToken();
    private CheckStatusCode checkStatusCode = new CheckStatusCode();
    private CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    private String accessToken;

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
    @Description("Configuring the driver and base URL for tests")
    @DisplayName("Setting up the test environment")
    public void setUp() {
        driver = BrowserChoose.createDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    public void registration() {
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
    }

    @After
    public void deleteUserAndQuitDriver() {
        driver.quit();
        deleteUser(getAccessToken(authorizationUser()));
    }
}
