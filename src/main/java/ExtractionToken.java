import io.restassured.response.Response;

public class ExtractionToken {

    private String accessToken;

    public String extractionToken(Response response) {
        accessToken = response.jsonPath().getString("accessToken");
        return accessToken.substring(7);
    }
}