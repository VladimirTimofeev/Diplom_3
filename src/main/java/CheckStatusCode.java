import io.restassured.response.Response;

public class CheckStatusCode {

    //Провека кода 200
    public void checkStatusCode200(Response response) {
        response.then().assertThat()
                .statusCode(200);
    }

    //Провека кода 202
    public void checkStatusCode202(Response response) {
        response.then().assertThat()
                .statusCode(202);
    }
}