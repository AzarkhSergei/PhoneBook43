package restassured;

import helpers.PropertiesReaderXML;
import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SecurityTests implements TestHelper {

  @Test
  public void loginSecurity() {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username("<script>alert('test')</script>")
        .password(PropertiesReaderXML.getProperties(CORRECT_PASSWORD, XML_DATA_FILE))
        .build();
    ErrorModel response = given()
        .body(requestModel)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+LOGIN_PATH)
        .then()
        .log().all()
        .statusCode(401)
        .extract()
        .as(ErrorModel.class);
    System.out.println(response.getMessage());
  }

  @Test
  public void bruteForceTest(){
    int maxAttemptsBefore = 5;
    int totalAttempts = 7;
    for(int i=0; i<maxAttemptsBefore; i++){
      AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
          .username(RANDOM_EMAIL)
          .password(RANDOM_PASSWORD)
          .build();
      given()
          .body(requestModel)
          .contentType(ContentType.JSON)
          .when()
          .post(BASE_URL+LOGIN_PATH)
          .then()
          .assertThat().statusCode(401);
    }
    for(int i=maxAttemptsBefore; i<totalAttempts; i++){
      AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
          .username("<script>")
          .password("<>")
          .build();
      given()
          .body(requestModel)
          .contentType(ContentType.JSON)
          .when()
          .post(BASE_URL+LOGIN_PATH)
          .then()
          .assertThat().statusCode(429);
    }
  }

}
