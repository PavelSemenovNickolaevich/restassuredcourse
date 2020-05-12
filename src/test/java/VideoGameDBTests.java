import config.EndPoint;
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameDBTests extends TestConfig {

    @Test
    public void getAllGames() {

        given().
                when().get(EndPoint.VIDEOGAMES).then();
    }

    @Test
    public void createNewGameByJSON() {

        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"MyGame\",\n" +
                "  \"releaseDate\": \"2020-05-12T12:30:15.494Z\",\n" +
                "  \"reviewScore\": 50,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";
        given().body(gameBodyJson).
                when().post(EndPoint.VIDEOGAMES).then();
    }
}
