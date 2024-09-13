package restassured;

import config.TestData;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthenticationRequestModel;
import models.ErrorModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegistrationTest implements TestHelper {

  @Test
  public void registrationPositive(){
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(RANDOM_EMAIL)
        .password(RANDOM_PASSWORD)
        .build();
    String token = given()
        .body(requestModel)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+REGISTRATION_PATH)
        .then().assertThat().statusCode(200)
        .extract().path("token");
    System.out.println(token);
  }



  @Test(dataProvider = "RegistrationData", dataProviderClass = TestData.class)
  public void registrationNegativeTests(String email, String password, int expectedStatusCode){
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(email)
        .password(password)
        .build();
    ErrorModel errorModel = given()
        .body(requestModel)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+REGISTRATION_PATH)
        .then().assertThat().statusCode(expectedStatusCode)
        .extract().as(ErrorModel.class);
    System.out.println(errorModel.getMessage());
    System.out.println(errorModel.getStatus());
  }

  @Test//(dataProvider = "RegistrationData")
  public void registrationNegativeTests2(){
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(RANDOM_EMAIL)
        .password(PASSWORD_WITHOUT_DIGITS)
        .build();
    Response response = given()
        .body(requestModel)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+REGISTRATION_PATH)
        .then().assertThat().statusCode(400)
        .extract().response();
    System.out.println(response.getBody().asString());
    String message = response.jsonPath().getString("message.password");
    Assert.assertTrue(message.contains("At least 8 characters"));
    System.out.println("Time: "+response.getTime());
    System.out.println("Headers: "+response.getHeaders());
  }

}
