import config.EndPoint;
import config.TestConfig;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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

    //Validating Response against a XML Schema
    @Test
    public void testVideoGameSchemaXML() {

        given().pathParam("videoGameId", 5).
                when().get(EndPoint.SINGLE_VIEOGAME).then().
                body(matchesXsdInClasspath(" VideoGame.xsd"));

    }

    //Validating Response against a JSON Schema
    @Test
    public void testVideoGameSchemaJSON() {
        given().pathParam("videoGameId", 5).
                when().get(EndPoint.SINGLE_VIEOGAME).then().
                body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }

    //Convert JSON to POJO
    @Test
    public void convertJSONToPojo() {
        Response response = given().pathParam("videoGameId", 5).
                when().get(EndPoint.SINGLE_VIEOGAME);

        VideoGame videoGame = response.getBody().as(VideoGame.class);

        System.out.println(videoGame.toString());

    }


}
