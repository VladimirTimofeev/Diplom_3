import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class CheckBodyResponse {

    //Проверка тега ответа TRUE
    public void checkBodyTegSuccessTrue(Response response) {
        response.then().assertThat()
                .body("success", equalTo(true));
    }

    //Проверка сообщения об удачном удалении пользователя
    public void checkMessageDeleteUser(Response response) {
        response.then().assertThat()
                .body("message", equalTo("User successfully removed"));
    }
}
