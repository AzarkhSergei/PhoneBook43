package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;
import org.testng.Assert;

import java.io.IOException;

public class UpdateContactTest implements TestHelper {

  @Test
  public void updateContactPositive() throws IOException {
    Contact contact = new Contact(NameAndLastNameGenerator.generateName()
        ,NameAndLastNameGenerator.generateLastName()
        ,EmailGenerator.generateEmail(10,5,3, EmailGenerator.EmailType.VALID)
        ,PhoneNumberGenerator.generatePhoneNumber()
        ,AddressGenerator.generateAddress()
        ,"Test desc...");

    RequestBody requestBody = RequestBody.create(GSON.toJson(contact),JSON);
    Request request = new Request.Builder()
        .url(BASE_URL+ADD_CONTACT_PATH)
        .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .post(requestBody)
        .build();
    Response response = CLIENT.newCall(request).execute();
    ContactResponseModel contactResponseModel = GSON.fromJson(response.body().string(), ContactResponseModel.class);
    String id = IdExtractor.getId(contactResponseModel.getMessage());
    Contact modifiedContact = new Contact(
        id,
        NameAndLastNameGenerator.generateName(),
        contact.getLastName(),
        contact.getEmail(),
        contact.getPhone(),
        contact.getAddress(),
        contact.getDescription());
    System.out.println("Modified contact: "+modifiedContact);
    RequestBody updatedBody = RequestBody.create(GSON.toJson(modifiedContact),JSON);
    Request updatedRequest = new Request.Builder()
        .url(BASE_URL+UPDATE_CONTACT_PATH)
        .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .put(updatedBody)
        .build();
    Response updatedResponse = CLIENT.newCall(updatedRequest).execute();
    Assert.assertTrue(updatedResponse.isSuccessful());
  }
}
