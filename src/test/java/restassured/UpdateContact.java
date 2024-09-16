package restassured;

import db.DataBaseConnection;
import db.DataBaseReader;
import helpers.*;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

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

  @Test
  public void updateContactLocalDB() throws SQLException {
    Contact contact = getContact();
    RestAssured.baseURI = BASE_URL+ADD_CONTACT_PATH;
    String message = given()
        .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .body(contact)
        .contentType(ContentType.JSON)
        .when().post()
        .then().extract().path("message");
    String id = IdExtractor.getId(message);
    contact.setId(id);
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    try {
      dataBaseConnection.contactRecorder(contact);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    contact.setEmail("mytestemail@gmail.com");
    given()
        .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .body(contact)
        .contentType(ContentType.JSON)
        .when().put()
        .then().statusCode(200);
    Contact changedContact = DataBaseReader.readContactFromDb(id);
    Assert.assertNotEquals(changedContact.getEmail(), contact.getEmail());
  }


}
