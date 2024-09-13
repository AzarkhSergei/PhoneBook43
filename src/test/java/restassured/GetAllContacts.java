package restassured;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import models.ContactsDto;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllContacts implements TestHelper {

  @Test
  public void getAllContacts(){
    ContactsDto contactsDto = given()
        .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .when()
        .get(BASE_URL+CONTACTS_PATH)
        .then()
        .assertThat()
        .statusCode(200)
        .extract().as(ContactsDto.class);
    for(Contact contact: contactsDto.getContacts()){
      System.out.println(contact.getEmail());
      System.out.println(contact.getId());
      System.out.println("+++++++++++++++++++++++++++++");
    }
  }

  //Task

}
