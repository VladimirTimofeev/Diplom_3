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

    public void checkBodyUserEmail(Response response, User user) {
        response.then().assertThat()
                .body("user.email", equalTo(user.getEmail()));
    }

    public void checkBoduUserName(Response response, User user) {
        response.then().assertThat()
                .body("user.name", equalTo(user.getName()));
    }
}