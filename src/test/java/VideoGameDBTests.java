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

    @Test
    public void createNewGaneByXML() {
        String gameBodyXML =
                "  <videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                        "    <id>17</id>\n" +
                        "    <name>Resident Evil 7</name>\n" +
                        "    <releaseDate>2005-10-01T00:00:00+04:00</releaseDate>\n" +
                        "    <reviewScore>75</reviewScore>\n" +
                        "  </videoGame>";
        given().body(gameBodyXML).when().post(EndPoint.VIDEOGAMES).then();

    }

    @Test
    public void updateGame() {
        String gameBodyJSON = "{\n" +
                "  \"id\": 17,\n" +
                "  \"name\": \"Update MyGame\",\n" +
                "  \"releaseDate\": \"2020-05-12T12:30:15.494Z\",\n" +
                "  \"reviewScore\": 50,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given().body(gameBodyJSON).when().put("/videogames/17").then();

    }

    @Test
    public void deleteGame() {
        given().when().delete("/videogames/17").then();
    }

    //Path parameters
    @Test
    public void getSingleGame() {

        given().pathParam("videoGameId", 5).
                when().get(EndPoint.SINGLE_VIEOGAME);
    }

    //Object serialization
    @Test
    public void testVideoGameSerialisationByJSON() {
        VideoGame videoGame = new VideoGame("15","shooter", "2014-06-06","Halo 5","Mature", "89");
        given().body(videoGame).
                when().post(EndPoint.VIDEOGAMES).then();

    }


}
