import config.TestConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends TestConfig {

    //Gpath Json Part1 - Setup and basic find
    @Test
    public void extractMapOfElementsWithFind(){

        Response response = get("/competitions/2000/teams");
//Поиск элемента teams , затем в тиамс элемента tla c BRA на языке груви
        Map<String, ?> allTeamDataForSingleTeam = response.path
                ("teams.find{it.tla == 'BRA'}");
        System.out.println(allTeamDataForSingleTeam);
    }

    //Gpath Json Part2 - Using findAll to extract multiple data
    @Test
    public  void extractSingleValueWithFind() {

        Response response = get("teams/18/");
        String nationality = response.
                path("squad.find{it.name == 'Tobias Sippel'}.nationality");
        System.out.println(nationality);
    }

    @Test
    public  void extractListOfValuesWithFindAll() {
        Response response = get("teams/18/");
        List<String> playersNationality = response.
                path("squad.findAll{it.name != 'Tobias Sippel'}.nationality");
        for (String allNationality : playersNationality) {
            System.out.println(allNationality);
        }
    }

    //Gpath Json Part3 - Using min max collect and sum
    @Test
    public void extractSingleValueWithHighestNumber() {
        Response response = get("teams/18/");
        String playerName = response.path("squad.min{it.shirtNumber}name"); //if min change max
        System.out.println(playerName);
    }

    @Test
    public void extractsMultipleValueAndSumThem() {
        Response response = get("teams/18/");
        Integer sumOfShirtNumber = response.path("squad.collect{it.id}.sum()");
        System.out.println(sumOfShirtNumber);
    }
}
