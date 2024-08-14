package web;

import config.BaseTest;
import config.TestData;
import enums.TopMenuItem;
import helpers.AlertHandler;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTests extends BaseTest {

  @Test
  public void successfulLogin(){
    MainPage mainPage = new MainPage(getDriver());
    LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
    loginPage
        .fieldEmailField(TestData.correctEmail)
        .fieldPasswordField(TestData.correctPassword)
        .clickByLoginButton();
    //TASK
    boolean result = ContactsPage.isElementPersist(getDriver().
        findElement(By.xpath(TestData.buttonSingOut)));
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
