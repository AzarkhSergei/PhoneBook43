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
    String expectedAlert = "Wrong ";
    boolean isAlertHandled = AlertHandler.handleAlert(alert, expectedAlert);
    Assert.assertTrue(isAlertHandled);
  }


}
