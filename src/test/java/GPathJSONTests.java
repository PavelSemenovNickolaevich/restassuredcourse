import config.TestConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends TestConfig {

    //Gpath Json Part1 - Setup and basic find
    @Test
    public void extractMapOfElementsWithFind(){

        Response response = get("/competitions/2000/teams");
//Поиск элемента teams , затем в тиамс элемента tla c BRA
        Map<String, ?> allTeamDataForSingleTeam = response.path
                ("teams.find{it.tla == 'BRA'}");
        System.out.println(allTeamDataForSingleTeam);
    }
}
