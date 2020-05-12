import config.EndPoint;
import config.TestConfig;
import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class MyFirsTest extends TestConfig {

    @Test
    public void myFirstTest() {
        given().
                log().
                all().
 //               ifValidationFails().
                when().get("videogames/1").
                then().
                log().
                all();
 //               statusCode(200);

    }

    @Test
    public void getAllGames() {
        when().get(EndPoint.VIDEOGAMES);
    }
}
