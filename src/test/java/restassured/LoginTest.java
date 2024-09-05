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

public class LoginTest implements TestHelper {


  @Test
  public void login() {
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

  @Test
  public void loginNegativeWithPasswordWithoutDigits() {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .password(PropertiesReaderXML.getProperties(PASSWORD_WITHOUT_DIGITS, XML_DATA_FILE))
        .build();
    ErrorModel response = given()
        .body(requestModel)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL + LOGIN_PATH)
        .then()
        .log().all()
        .statusCode(401)
        .extract()
        .as(ErrorModel.class);
    System.out.println(response.getMessage());
  }

}
