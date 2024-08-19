package web;

import config.BaseTest;
import enums.TopMenuItem;
import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;


public class PhoneBookTests extends BaseTest implements TestHelper {

  @Test
  public void successfulLogin() {
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    loginPage
        .fieldEmailField(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .fieldPasswordField(PropertiesReaderXML.getProperties(CORRECT_PASSWORD,XML_DATA_FILE))
        .clickByLoginButton();
    ContactsPage cp = new ContactsPage(getDriver());
    boolean result = cp.isSignButtonPersist();
    Assert.assertTrue(result);
  }

  @Test
  public void loginWithoutPassword(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(RANDOM_EMAIL)
        .clickByLoginButtonAlert();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_LOGIN);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void loginWithoutEmail(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldPasswordField(RANDOM_PASSWORD)
        .clickByLoginButtonAlert();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_LOGIN);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithoutPassword(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(RANDOM_EMAIL)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutSpecialSymbol(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(RANDOM_EMAIL)
        .fieldPasswordField(PropertiesReaderXML.getProperties(PASSWORD_WITHOUT_SPECIAL_SYMBOL, XML_DATA_FILE))
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutUppercaseLetter(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(RANDOM_EMAIL)
        .fieldPasswordField(PropertiesReaderXML.getProperties(PASSWORD_WITHOUT_UPPERCASE_LETTER, XML_DATA_FILE))
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutLowercaseLetter(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(RANDOM_EMAIL)
        .fieldPasswordField(PropertiesReaderXML.getProperties(PASSWORD_WITHOUT_LOWERCASE_LETTER, XML_DATA_FILE))
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutDigit(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(RANDOM_EMAIL)
        .fieldPasswordField(PropertiesReaderXML.getProperties(PASSWORD_WITHOUT_DIGITS, XML_DATA_FILE))
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithLengthLess8Symbols(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(RANDOM_EMAIL)
        .fieldPasswordField(PropertiesReaderXML.getProperties(PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS, XML_DATA_FILE))
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

/*  @Test
  public void registrationWithPasswordWithLengthMore15Symbols(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(RANDOM_EMAIL)
        .fieldPasswordField(PropertiesReaderXML.getProperties(PASSWORD_WITH_LENGTH_MORE_15_SYMBOLS, XML_DATA_FILE))
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }*/

  @Test
  public void loginOfAnExistingUserAddContact(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    loginPage.fieldEmailField(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .fieldPasswordField(PropertiesReaderXML.getProperties(CORRECT_PASSWORD,XML_DATA_FILE))
        .clickByLoginButton();
    AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);
    Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
        NameAndLastNameGenerator.generateLastName(),
        PhoneNumberGenerator.generatePhoneNumber(),
        EmailGenerator.generateEmail(10,5,3),
        AddressGenerator.generateAddress(),
        "Test description");
    System.out.println(contact.toString());
    addPage.fieldContactFormAndSave(contact);
    ContactsPage contactsPage = new ContactsPage(getDriver());
    Assert.assertTrue(contactsPage.getDataFromContactList(contact));

  }


}
