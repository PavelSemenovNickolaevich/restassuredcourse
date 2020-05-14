package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class TestConfig {

    public static  RequestSpecification videoGame_requestSpec;
    public static  RequestSpecification football_requestSpec;
    public static ResponseSpecification responseSpec;
    @BeforeClass
    public static void setup() {

        videoGame_requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(8080).
                setBasePath("/app/").
                addHeader("Content-Type", "application/json").
                addHeader("Accept", "application/json").build();

        football_requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.football-data.org").
                setBasePath("/v2/").
                addHeader("X-Auth-Token", "a4c363d5cdb345ada49e5a262bd97c16").
               // addHeader("X-Responce-Control", "minified").
                build();

        RestAssured.requestSpecification = videoGame_requestSpec;
        RestAssured.requestSpecification = football_requestSpec ;

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectResponseTime(lessThan(4000L)).
                build();

        RestAssured.responseSpecification = responseSpec;


//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 8080;
//        RestAssured.basePath = "/app/";
//
//        RequestSpecification requestSpecification = new RequestSpecBuilder().
//                addHeader("Content-Type", "application/json").
//                addHeader("Accept", "application/json").
//                build();
//
//        RestAssured.requestSpecification = requestSpecification;
//
//
//        ResponseSpecification responseSpecification = new ResponseSpecBuilder().
//                expectStatusCode(200).
//                build();
//
//        RestAssured.responseSpecification = responseSpecification;


        //capture traffic dor Fiddler. And we can see request(FIDDLER should be able)
 //       RestAssured.proxy("localhost", 8888);

    }
}
