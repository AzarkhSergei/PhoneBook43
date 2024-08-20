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
        .fillEmailField(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .fillPasswordField(PropertiesReaderXML.getProperties(CORRECT_PASSWORD,XML_DATA_FILE))
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
        .fillEmailField(RANDOM_EMAIL)
        .clickByLoginButtonAlert();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_LOGIN);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void loginWithoutEmail(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillPasswordField(RANDOM_PASSWORD)
        .clickByLoginButtonAlert();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_LOGIN);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void loginWithIncorrectPassword(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(CORRECT_EMAIL)
        .fillPasswordField(RANDOM_PASSWORD)
        .clickByLoginButtonAlert();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_LOGIN);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithoutPassword(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutSpecialSymbol(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL)
        .fillPasswordField(PASSWORD_WITHOUT_SPECIAL_SYMBOL)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutUppercaseLetter(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL)
        .fillPasswordField(PASSWORD_WITHOUT_UPPERCASE_LETTER)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutLowercaseLetter(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL)
        .fillPasswordField(PASSWORD_WITHOUT_LOWERCASE_LETTER)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutDigit(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL)
        .fillPasswordField(PASSWORD_WITHOUT_DIGITS)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithLengthLess8Symbols(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL)
        .fillPasswordField(PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS)
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
        .fieldPasswordField(PASSWORD_WITH_LENGTH_MORE_15_SYMBOLS)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }*/

  @Test
  public void registrationWithEmailWithoutAt(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL_WITHOUT_AT)
        .fillPasswordField(RANDOM_PASSWORD)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithEmailWithoutUserName(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL_WITHOUT_USER_NAME)
        .fillPasswordField(RANDOM_PASSWORD)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithEmailWithoutDomain(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL_WITHOUT_DOMAIN)
        .fillPasswordField(RANDOM_PASSWORD)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

/*  @Test
  public void registrationWithEmailWithoutDot(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL_WITHOUT_DOT)
        .fillPasswordField(RANDOM_PASSWORD)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }*/

  @Test
  public void registrationWithEmailWithInvalidChar(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fillEmailField(RANDOM_EMAIL_WITH_INVALID_CHAR)
        .fillPasswordField(RANDOM_PASSWORD)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, EXPECTED_ALERT_REGISTRATION);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void loginOfAnExistingUserAddContact(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    loginPage.fillEmailField(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .fillPasswordField(PropertiesReaderXML.getProperties(CORRECT_PASSWORD,XML_DATA_FILE))
        .clickByLoginButton();
    AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);
    Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
        NameAndLastNameGenerator.generateLastName(),
        PhoneNumberGenerator.generatePhoneNumber(),
        RANDOM_EMAIL,
        AddressGenerator.generateAddress(),
        "Test description");
    System.out.println(contact.toString());
    addPage.fieldContactFormAndSave(contact);
    ContactsPage contactsPage = new ContactsPage(getDriver());
    Assert.assertTrue(contactsPage.getDataFromContactList(contact));
  }

  @Test
  public void loginOfAnExistingUserAddContactAndEditName(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    loginPage.fillEmailField(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .fillPasswordField(PropertiesReaderXML.getProperties(CORRECT_PASSWORD,XML_DATA_FILE))
        .clickByLoginButton();
    AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);
    Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
        NameAndLastNameGenerator.generateLastName(),
        PhoneNumberGenerator.generatePhoneNumber(),
        RANDOM_EMAIL,
        AddressGenerator.generateAddress(),
        "Test description");
    System.out.println(contact.toString());
    addPage.fieldContactFormAndSave(contact);
    ContactsPage contactsPage = new ContactsPage(getDriver());
    Assert.assertTrue(contactsPage.editContactField(contact, ContactsPage.FieldType.NAME));
  }

  @Test
  public void loginOfAnExistingUserAddContactAndEditLastName(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    loginPage.fillEmailField(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .fillPasswordField(PropertiesReaderXML.getProperties(CORRECT_PASSWORD,XML_DATA_FILE))
        .clickByLoginButton();
    AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);
    Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
        NameAndLastNameGenerator.generateLastName(),
        PhoneNumberGenerator.generatePhoneNumber(),
        RANDOM_EMAIL,
        AddressGenerator.generateAddress(),
        "Test description");
    System.out.println(contact.toString());
    addPage.fieldContactFormAndSave(contact);
    ContactsPage contactsPage = new ContactsPage(getDriver());
    Assert.assertTrue(contactsPage.editContactField(contact, ContactsPage.FieldType.LASTNAME));
  }



}
