import config.EndPoint;
import config.TestConfig;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.get;

public class GPathXMLTests extends TestConfig {
//GPath xml part 1 - setup and basic find
    @Test
    public void getFirstGameList() {

        Response response = get(EndPoint.VIDEOGAMES);

        String name = response.path("videoGames.videoGame.name[0]");
        System.out.println(name);

    }


}
