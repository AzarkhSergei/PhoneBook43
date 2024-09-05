package okhttp;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements TestHelper {

  @Test
  public void registrationPositive() throws IOException {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(RANDOM_EMAIL)
        .password(RANDOM_PASSWORD)
        .build();
    System.out.println("Request model :" + requestModel.getUsername() +" "+ requestModel.getPassword());

    RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel), JSON);
    Request request = new Request.Builder().url(BASE_URL+ REGISTRATION_PATH).post(requestBody).build();

    Response response = CLIENT.newCall(request).execute();
    String result = response.body().string();
    System.out.println(result);
    if(response.isSuccessful()){
      AuthenticationResponseModel responseModel = GSON.fromJson(result, AuthenticationResponseModel.class);
      System.out.println("token -->" +responseModel.getToken());
      Assert.assertTrue(response.isSuccessful());
    }else{
      ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
      System.out.println("ErrorModel" +errorModel.toString());
    }
  }

  @Test
  public void registrationNegative() throws IOException {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(RANDOM_EMAIL)
        .password(PASSWORD_WITHOUT_DIGITS)
        .build();
    System.out.println("Request model :" + requestModel.getUsername() +" "+ requestModel.getPassword());

    RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel), JSON);
    Request request = new Request.Builder()
        .url(BASE_URL+ REGISTRATION_PATH)
        .post(requestBody)
        .build();

    Response response = CLIENT.newCall(request).execute();
    if(!response.isSuccessful()){
      ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
      System.out.println("ErrorModel --> " +errorModel.toString());
      Assert.assertEquals(response.code(), 400);
    }
  }

  @Test
  public void registrationWithExistingMail() throws IOException {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel.builder()
        .username(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .password(RANDOM_PASSWORD)
        .build();
    System.out.println("Request model :" + requestModel.getUsername() +" "+ requestModel.getPassword());

    RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel), JSON);
    Request request = new Request.Builder().url(BASE_URL+ REGISTRATION_PATH).post(requestBody).build();

    Response response = CLIENT.newCall(request).execute();
    if(!response.isSuccessful()){
      ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
      System.out.println("ErrorModel --> " +errorModel.toString());
      Assert.assertEquals(response.code(), 409);
    }
  }

}
