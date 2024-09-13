package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateContact extends ContactGeneratorPrecondition implements TestHelper {

  @Test
  public void updateContactPositive(){
    Contact contact = getContact();
    RestAssured.baseURI = BASE_URL+UPDATE_CONTACT_PATH;
    String message = given()
        .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .body(contact)
        .contentType(ContentType.JSON)
        .when().post()
        .then().extract().path("message");
    String id = IdExtractor.getId(message);
    contact.setId(id);
    contact.setEmail("updatedemail@gmail.com");
    given()
        .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .body(contact)
        .contentType(ContentType.JSON)
        .when().put()
        .then().log().all()
        .assertThat().statusCode(200);
  }


}
