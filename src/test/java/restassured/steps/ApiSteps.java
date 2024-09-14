package restassured.steps;

import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthenticationRequestModel;

import static io.restassured.RestAssured.given;

public class ApiSteps implements TestHelper {

  @Step("Prepare authentication with user name {0}")
  public AuthenticationRequestModel preparedRequest(String username, String password){
    return AuthenticationRequestModel.builder().username(username).password(password).build();
  }

  @Step("Send login request to endpoint: {0}")
  public Response sendLoginRequest(AuthenticationRequestModel requestModel, String endpoint){
    return given()
        .body(requestModel)
        .contentType(ContentType.JSON)
        .when()
        .put(endpoint)
        .then()
        .log().all()
        .statusCode(200)
        .extract().response();
  }

  @Step("Save authentication token.")
  public void saveToken(String token){
    PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML();
    propertiesWriterXML.setProperty("token", token,false, XML_DATA_FILE);
  }

}
