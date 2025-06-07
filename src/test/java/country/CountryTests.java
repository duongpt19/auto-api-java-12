package country;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import model.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CountryTests {

    @BeforeAll
    static void setup(){
        //http://localhost:3000/api/v1/countries
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }

    @Test
    void verifySchemaOfGetCountriesApi(){
        RestAssured.given().log().all()
                .get("/api/v1/countries")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("json-schema/countries-schema.json"));
    }

    @Test
    void verifyGetCountriesApiData() throws JsonProcessingException {
        Response response = RestAssured.given().log().all()
                .get("/api/v1/countries");
        //1. Verify status
        response.then().log().all().statusCode(200);
        //2. Verify Headers
        response.then().header("X-Powered-By",equalTo("Express"))
                .header("Content-Type",equalTo("application/json; charset=utf-8"));
        //3. Verify Body
        ObjectMapper mapper = new ObjectMapper();
        List<Country> expected = mapper.readValue(CountriesData.ALL_COUNTRIES_DATE, new TypeReference<List<Country>>(){
        });
        List<Country> actual = response.body().as(new TypeRef<List<Country>>(){
        });
        assertThat(actual.size(), equalTo(expected.size()));
        assertThat(actual.containsAll(expected), equalTo(true));
        assertThat(expected.containsAll(actual), equalTo(true));
    }

    @Test
    void verifySchemaOfGetCountryApi(){
        RestAssured.given().log().all()
                .get("/api/v1/countries/VN")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("json-schema/country-schema.json"));
    }

    static Stream<Country> countryProvider() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Country> inputData = mapper.readValue(CountriesData.ALL_COUNTRIES_DATE, new TypeReference<>(){
        });
        return inputData.stream();
    }

    @ParameterizedTest
    @MethodSource("countryProvider")
    void verifyGetCountry(Country input) {
        Response response = RestAssured.given().log().all()
                .get("/api/v1/countries/{code}", input.getCode());
        //1. Verify status
        response.then().log().all().statusCode(200);
        //2. Verify Headers
        response.then().header("X-Powered-By",equalTo("Express"))
                .header("Content-Type",equalTo("application/json; charset=utf-8"));
        //3. Verify Body
        Country actual=response.body().as(Country.class);
        assertThat(actual, equalToObject(input));
    }


}