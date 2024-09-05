package okhttp;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import models.ContactsDto;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContacts implements TestHelper {


  @Test
  public void getAllContacts() throws IOException {
    Request request = new Request.Builder()
        .url(BASE_URL+CONTACTS_PATH)
        .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .build();

    Response response = CLIENT.newCall(request).execute();
    String responseBody = response.body().string();
   ContactsDto contactsDto = GSON.fromJson(responseBody, ContactsDto.class);
   System.out.println(contactsDto.toString());
   for(Contact contact: contactsDto.getContacts()){
     System.out.println(contact.toString());
    }

  }

}
