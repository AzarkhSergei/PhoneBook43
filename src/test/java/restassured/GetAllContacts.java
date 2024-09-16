package restassured;

import db.DataBaseConnection;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import models.ContactsDto;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class GetAllContacts implements TestHelper {

  @Test
  public void getAllContacts() throws SQLException {
    ContactsDto contactsDto = given()
        .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .when()
        .get(BASE_URL+CONTACTS_PATH)
        .then()
        .assertThat()
        .statusCode(200)
        .extract().as(ContactsDto.class);
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    for(Contact contact: contactsDto.getContacts()){
      dataBaseConnection.contactRecorder(contact);
      System.out.println(contact.getEmail());
      System.out.println(contact.getId());
      System.out.println("+++++++++++++++++++++++++++++");
    }
  }

  //Task

}
