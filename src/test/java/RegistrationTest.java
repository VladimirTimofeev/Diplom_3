import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class RegistrationTest {

    private WebDriver driver;

    PostApi postApi = new PostApi();
    DeleteApi deleteApi = new DeleteApi();
    ExtractionToken extractionToken = new ExtractionToken();
    CheckStatusCode checkStatusCode = new CheckStatusCode();
    CheckBodyResponse checkBodyResponse = new CheckBodyResponse();
    String accessToken;

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
        basePageObject.ckickLoginButton();
        Registration registration = new Registration(driver);
        registration.clickLinkRegistration();
        registration.enterFieldName(user);
        registration.enterFieldEmail(user);
        registration.enterPassword(user);
        registration.clickButtonRegistration();
    }

    @After
    public void deleteUserAndQuitDriver() {
        driver.quit();
        deleteUser(getAccessToken(authorizationUser()));
    }
}
