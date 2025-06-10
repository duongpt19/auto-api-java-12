package testCase;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static utils.ConstantUtils.HOST;
import static utils.ConstantUtils.PORT;

public class MasterTest {

    @BeforeAll
    static void globalSetup(){
        RestAssured.baseURI = HOST;
        RestAssured.port = PORT;
    }

}
