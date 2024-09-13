package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewContact extends ContactGeneratorPrecondition implements TestHelper {

  @Test
  public void addNewContact(){
    Contact contact = getContact();
    Assert.assertEquals(given()
        .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .body(contact)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+ADD_CONTACT_PATH)
        .then().log().all()
        .assertThat().statusCode(200).extract().response().getStatusCode(), 200);
  }

  @Test
  public void addNewContactMessage(){
    Contact contact = getContact();
    Assert.assertTrue(given()
        .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .body(contact)
        .contentType(ContentType.JSON)
        .when()
        .post(BASE_URL+ADD_CONTACT_PATH)
        .then().log().all()
        .assertThat().statusCode(200)
        .extract().response().body().path("message").toString().contains("Contact was added"));
  }


}
