package okhttp;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAllContactsTests implements TestHelper {

  @Test
  public void deleteAllContactsPositive() throws IOException {
    Request request = new Request.Builder()
        .url(BASE_URL+DELETE_ALL_CONTACTS_PATH)
        .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties(TOKEN, XML_DATA_FILE))
        .delete()
        .build();
    Response response = CLIENT.newCall(request).execute();
    ContactResponseModel contactResponseModel = GSON.fromJson(response.body().string(), ContactResponseModel.class);
    System.out.println(contactResponseModel.getMessage());
    Assert.assertTrue(response.isSuccessful());
  }






}

