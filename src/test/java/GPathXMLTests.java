import config.EndPoint;
import config.TestConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class GPathXMLTests extends TestConfig {
    //GPath xml part 1 - setup and basic find
    @Test
    public void getFirstGameList() {

        Response response = get(EndPoint.VIDEOGAMES);

        String name = response.path("videoGames.videoGame.name[0]");
        System.out.println(name);

    }

    //GPath xml part 2 - attributes
    @Test
    public void getAttributesName() {
        Response response = get(EndPoint.VIDEOGAMES);
        String category = response.path("videoGames.videoGame[0].@category");

        System.out.println(category);

    }

    //GPath xml part 3 - extract all XML nodes with findAll
    @Test
    public void getListOfXMLNodes() {

        String responseAsString = get(EndPoint.VIDEOGAMES).asString();

        List<Node> allResults = XmlPath.from(responseAsString).
                get("videoGames.videoGame.findAll {element -> return element}");

        System.out.println(allResults.get(2).get("name").toString());

    }

    //GPath xml part 4 - extract all XML nodes with an attribute
    @Test
    public void getListOfXMLNodesByFindAttribute() {

        String responseAsString = get(EndPoint.VIDEOGAMES).asString();

        List<Node> allDrivingGames = XmlPath.from(responseAsString).
                get("videoGames.videoGame.findAll {videoGame -> def category = videoGame.@category; category == 'Driving'}");

        System.out.println(allDrivingGames.get(0).get("name").toString());

    }

    //GPath xml part 5 - extract single node
    @Test
    public void getSingleNodes() {
        String responseAsString = get(EndPoint.VIDEOGAMES).asString();
        Node videoGame =XmlPath.from(responseAsString).
                get("videGames.videoGame.find{videoGame -> def name = vodeoGame.name; name == 'Tetris'}");

        String videoGameName = videoGame.get("name").toString();
        System.out.println(videoGameName);
    }



}
