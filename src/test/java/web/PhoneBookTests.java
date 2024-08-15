package web;

import config.BaseTest;
import config.TestData;
import enums.TopMenuItem;
import helpers.AlertHandler;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;


public class PhoneBookTests extends BaseTest implements TestHelper {

  @Test
  public void successfulLogin(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    loginPage
        .fieldEmailField(PropertiesReaderXML.getProperties(CORRECT_EMAIL, XML_DATA_FILE))
        .fieldPasswordField(PropertiesReaderXML.getProperties(CORRECT_PASSWORD, XML_DATA_FILE))
        .clickByLoginButton();
    ContactsPage contactsPage = new ContactsPage(getDriver());
    boolean result = contactsPage.isSignButtonPersist();
    Assert.assertTrue(result);
  }

  @Test
  public void registrationWithoutPassword(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(TestData.randomEmail)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, TestData.expectedAlert);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutSpecialSymbol(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(TestData.randomEmail)
        .fieldPasswordField(TestData.passwordWithoutSpecialSymbol)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, TestData.expectedAlert);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutUppercaseLetter(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(TestData.randomEmail)
        .fieldPasswordField(TestData.passwordWithoutUppercaseLetter)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, TestData.expectedAlert);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutLowercaseLetter(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(TestData.randomEmail)
        .fieldPasswordField(TestData.passwordWithoutLowercaseLetter)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, TestData.expectedAlert);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithoutDigit(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(TestData.randomEmail)
        .fieldPasswordField(TestData.passwordWithoutDigit)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, TestData.expectedAlert);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithLengthLess8Symbols(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(TestData.randomEmail)
        .fieldPasswordField(TestData.passwordWithLengthLess8Symbols)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, TestData.expectedAlert);
    Assert.assertTrue(isAlertHandled);
  }

  @Test
  public void registrationWithPasswordWithLengthMore15Symbols(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    Alert alert = loginPage
        .fieldEmailField(TestData.randomEmail)
        .fieldPasswordField(TestData.passwordWithLengthMore15Symbols)
        .clickByRegistrationButton();
    boolean isAlertHandled = AlertHandler.handleAlert(alert, TestData.expectedAlert);
    Assert.assertTrue(isAlertHandled);
  }


}
