import config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class FootballTests extends TestConfig {

    @Test
    public void getAllCompetitionsOneSeason() {
        given().
                spec(football_requestSpec).
                queryParam("areas", 2016).
                when().get("competitions/");
    }

    //Assert the body of HTTP response
    @Test
    public void getTeamCount_OneComp() {
        given().
                spec(football_requestSpec).
                when().
                get("competitions/2000/teams").then().body("count", equalTo(32));
    }
//array выьрать определлкный элемент из ответа
    @Test
    public void getFirstTeamName() {
        given().
                spec(football_requestSpec).
                when().
                get("competitions/2000/teams").
                then().body( "teams.tla[0]", equalTo("URU"));

    }

    @Test
    public void getAllTeamData() {
        String responseBody = given().
                spec(football_requestSpec).when().get("competitions/2000/teams").asString();
        System.out.println(responseBody);
    }
    //Extract the body of HTTP Response
    @Test
    public void getAllTeamData_DoCheckFirst() {
        Response response = given().
                spec(football_requestSpec).when().get("competitions/2000/teams").
                then().contentType(ContentType.JSON).
                extract().response();

        String jsonResponseString = response.asString();
    }

        //Extract the headers of a HTTP Response
        @Test
        public void extractHeaders() {
            Response response = given().
                    spec(football_requestSpec).when().get("competitions/2000/teams").
                    then().contentType(ContentType.JSON).
                    extract().response();

            Headers headers = response.getHeaders();

            String contentType = response.getHeader("Content-Type");
            System.out.println(contentType);


        }



}
