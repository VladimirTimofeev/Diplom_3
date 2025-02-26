import io.restassured.response.Response;

public class CheckStatusCode {

    public void checkStatusCode202(Response response) {
        response.then().assertThat()
                .statusCode(202);
    }
}