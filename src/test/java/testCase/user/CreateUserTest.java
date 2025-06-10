package testCase.user;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.login.LoginRequest;
import model.login.LoginResponse;
import model.user.GetUserResponse;
import model.user.UserAddressRequest;
import model.user.UserRequest;
import model.user.UserResponse;
import org.junit.jupiter.api.Test;
import testCase.MasterTest;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.ConstantUtils.*;

public class CreateUserTest extends MasterTest {

    //2. Verify schema

    @Test
    void verifyCreateUserSuccessful(){
        LoginRequest loginRequest= new LoginRequest("staff", "1234567890");
        Response response = RestAssured.given().log().all()
                .header(CONTENT_TYPE_HEADER, REQUEST_CONTENT_TYPE_HEADER_VALUE)
                .body(loginRequest)
                .post(LOGIN_API);
        response.then().log().all().statusCode(200);
        LoginResponse loginResponse = response.body().as(LoginResponse.class);
        //Create UserAddress
        UserAddressRequest userAddressRequest= new UserAddressRequest();
            userAddressRequest.setStreetNumber("123");
            userAddressRequest.setStreet("Main St");
            userAddressRequest.setWard("Ward 1");
            userAddressRequest.setDistrict("District 1");
            userAddressRequest.setCity("Thu Duc");
            userAddressRequest.setState("Ho Chi Minh");
            userAddressRequest.setZip("70000");
            userAddressRequest.setCountry("VN");
        //Create User
        UserRequest userRequest = new UserRequest();
            userRequest.setFirstName("Jos");
            userRequest.setLastName("Doe");
            userRequest.setMiddleName("Smith");
            userRequest.setBirthday("01-23-2000");
            userRequest.setEmail(String.format("auto_api_%s@abc.com", System.currentTimeMillis()));
            userRequest.setPhone("0123456789");
            userRequest.setAddresses(List.of(userAddressRequest));

        Response createUserResponse = RestAssured.given().log().all()
                .header(CONTENT_TYPE_HEADER,REQUEST_CONTENT_TYPE_HEADER_VALUE)
                .header(AUTHORIZATION_HEADER, String.format("Bearer %s", loginResponse.getToken()))
                .body(userRequest)
                .post(CREATE_USER_API);
        //1. Verify status code
        createUserResponse.then().log().all().statusCode(200);
        //3. Verify header
        createUserResponse.then().header(X_POWERED_BY_HEADER,equalTo(X_POWERED_BY_HEADER_VALUE))
                .header(CONTENT_TYPE_HEADER,equalTo(RESPONSE_CONTENT_TYPE_HEADER_VALUE));
        //4. Verify body
        UserResponse userResponse= createUserResponse.body().as(UserResponse.class);
        assertThat(userResponse.getId(),not(emptyOrNullString()));
        assertThat(userResponse.getMessage(), equalTo("Customer created"));

        //5. Double check that user existing in the system or not by getUserApi

        Response getUserResponse = RestAssured.given().log().all()
                .header(CONTENT_TYPE_HEADER,REQUEST_CONTENT_TYPE_HEADER_VALUE)
                .header(AUTHORIZATION_HEADER, String.format("Bearer %s", loginResponse.getToken()))
                .get(GET_USER_API,userResponse.getId());
        //6. Verify status
        getUserResponse.then().log().all().statusCode(200);
        //7. Verify get user response again request
        GetUserResponse actualGetUserResponse = getUserResponse.body().as(GetUserResponse.class);
        assertThat(actualGetUserResponse.getId(), equalTo(userResponse.getId()));
        assertThat(actualGetUserResponse, jsonEquals(userRequest)
                .whenIgnoringPaths("id","createdAt","updatedAt","addresses[*].id","addresses[*].customerId","addresses[*].createdAt","addresses[*].updatedAt" ));
    }
}

