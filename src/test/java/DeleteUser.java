import io.qameta.allure.Step;
import io.restassured.response.Response;
import object.User;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class DeleteUser {

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

    @Step
    public void deleteUser(String accessToken) {
        response = deleteApi.deleteUser(accessToken);
        checkStatusCode.checkStatusCode202(response);
        checkBodyResponse.checkBodyTegSuccessTrue(response);
        checkBodyResponse.checkMessageDeleteUser(response);
    }

    @Test
    public void deleteUser() {
        deleteUser(getAccessToken(authorizationUser()));
    }
}
