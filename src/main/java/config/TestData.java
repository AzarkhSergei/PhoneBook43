package config;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import org.testng.annotations.DataProvider;

public class TestData implements TestHelper {


  @DataProvider(name = "loginData")
  public Object[][]loginData(){
    return new Object[][]{
        {RANDOM_EMAIL, RANDOM_PASSWORD, 401},
        {RANDOM_EMAIL, RANDOM_PASSWORD, 401}
    };
  }

  @DataProvider(name = "RegistrationData")
  public Object[][] RegistrationData() {
    return new Object[][]{
        {RANDOM_EMAIL, PASSWORD_WITHOUT_DIGITS, 400},
        {RANDOM_EMAIL, PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS, 400},
        {RANDOM_EMAIL, PASSWORD_WITHOUT_LOWERCASE_LETTER, 400},
        {RANDOM_EMAIL, PASSWORD_WITHOUT_SPECIAL_SYMBOL, 400},
        {RANDOM_EMAIL, PASSWORD_WITHOUT_UPPERCASE_LETTER, 400},
        //{RANDOM_EMAIL, PASSWORD_WITH_LENGTH_MORE_15_SYMBOLS, 400},
        {RANDOM_EMAIL_WITHOUT_USER_NAME, RANDOM_PASSWORD, 400},
        {RANDOM_EMAIL_WITHOUT_DOMAIN, RANDOM_PASSWORD, 400},
        {RANDOM_EMAIL_WITH_INVALID_CHAR, RANDOM_PASSWORD, 400},
        {RANDOM_EMAIL_WITHOUT_AT, RANDOM_PASSWORD, 400},
        //{RANDOM_EMAIL_WITHOUT_DOT, RANDOM_PASSWORD, 400},
        {"", RANDOM_PASSWORD, 400},
        {RANDOM_EMAIL, "", 400},
        {RANDOM_EMAIL, null, 400},
        {null, RANDOM_PASSWORD, 400},
        {PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE), RANDOM_PASSWORD, 409},
    };
  }

}
