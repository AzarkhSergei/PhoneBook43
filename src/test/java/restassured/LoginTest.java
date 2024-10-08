package restassured;

import config.TestData;
import helpers.PropertiesReaderXML;
import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import org.testng.annotations.Test;
import restassured.steps.ApiSteps;

import static io.restassured.RestAssured.given;

public class LoginTest implements TestHelper {


  @Test
  public void loginPositive() {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .password(PropertiesReaderXML.getProperties(CORRECT_PASSWORD, XML_DATA_FILE))
        .build();
    AuthenticationResponseModel response = given()
        .body(requestModel)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+LOGIN_PATH)
        .then()
        .log().all()
        .statusCode(200)
        .extract()
        .as(AuthenticationResponseModel.class);
    PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML();
    propertiesWriterXML.setProperty("token", response.getToken(),false, XML_DATA_FILE);
  }

  @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
  public void loginNegative(String username, String password, int statusCode) {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(username)
        .password(password)
        .build();
    ErrorModel response = given()
        .body(requestModel)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL + LOGIN_PATH)
        .then()
        .log().all()
        .statusCode(statusCode)
        .extract()
        .as(ErrorModel.class);
    System.out.println(response.getMessage());
  }

  private ApiSteps apiSteps = new ApiSteps();

  @Test(description = "Login positive steps")
  public void loginPositiveSteps(){
    String username = PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE);
    String password = PropertiesReaderXML.getProperties(CORRECT_PASSWORD, XML_DATA_FILE);
    AuthenticationRequestModel requestModel = apiSteps.preparedRequest(username, password);
    Response response = apiSteps.sendLoginRequest(requestModel, BASE_URL+LOGIN_PATH);
    AuthenticationResponseModel responseModel = response.as(AuthenticationResponseModel.class);
    apiSteps.saveToken(responseModel.getToken());

  }

}
