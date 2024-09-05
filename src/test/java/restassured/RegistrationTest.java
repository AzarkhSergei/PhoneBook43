package restassured;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
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


}
