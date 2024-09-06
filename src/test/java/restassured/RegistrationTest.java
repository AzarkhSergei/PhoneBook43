package restassured;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.ErrorModel;
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

  @DataProvider(name = "RegistrationData")
  public Object[][] RegistrationData() {
    return new Object[][]{
        {RANDOM_EMAIL, PASSWORD_WITHOUT_DIGITS},
        {RANDOM_EMAIL, PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS},
        {RANDOM_EMAIL, PASSWORD_WITHOUT_LOWERCASE_LETTER},
        {RANDOM_EMAIL, PASSWORD_WITHOUT_SPECIAL_SYMBOL},
        {RANDOM_EMAIL, PASSWORD_WITHOUT_UPPERCASE_LETTER},
        //{RANDOM_EMAIL, PASSWORD_WITH_LENGTH_MORE_15_SYMBOLS},
        {RANDOM_EMAIL_WITHOUT_USER_NAME, RANDOM_PASSWORD},
        {RANDOM_EMAIL_WITHOUT_DOMAIN, RANDOM_PASSWORD},
        {RANDOM_EMAIL_WITH_INVALID_CHAR, RANDOM_PASSWORD},
        {RANDOM_EMAIL_WITHOUT_AT, RANDOM_PASSWORD},
        //{RANDOM_EMAIL_WITHOUT_DOT, RANDOM_PASSWORD},
        {"", RANDOM_PASSWORD},
        {RANDOM_EMAIL, ""},
    };
  }

  @Test(dataProvider = "RegistrationData")
  public void registrationNegativeTests(String email, String password){
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(email)
        .password(password)
        .build();
    ErrorModel errorModel = given()
        .body(requestModel)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+REGISTRATION_PATH)
        .then().assertThat().statusCode(400)
        .extract().as(ErrorModel.class);
    System.out.println(errorModel.getMessage());
    System.out.println(errorModel.getStatus());
  }

  @Test
  public void registrationDuplicateUser(){
    AuthenticationRequestModel authenticationRequestModel = AuthenticationRequestModel
        .builder()
        .username(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .password(RANDOM_PASSWORD)
        .build();
    ErrorModel errorModel = given()
        .body(authenticationRequestModel)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+REGISTRATION_PATH)
        .then().assertThat().statusCode(409)
        .extract().as(ErrorModel.class);
    System.out.println(errorModel.getMessage());
    System.out.println(errorModel.getStatus());
  }


}
