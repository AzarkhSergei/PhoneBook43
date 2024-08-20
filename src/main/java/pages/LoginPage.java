package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import helpers.AlertHandler;

public class LoginPage extends BasePage{

  @FindBy(xpath = "//input[@name='email']")
  WebElement emailField;
  @FindBy(xpath = "//input[@name='password']")
  WebElement passwordField;
  @FindBy(xpath = "//button[@name='login']")
  WebElement loginButton;
  @FindBy(xpath = "//button[@name='registration']")
  WebElement registrationButton;

  private AlertHandler alertHandler;


  public LoginPage(WebDriver driver){
    setDriver(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    this.alertHandler = new AlertHandler(driver);
  }

  public LoginPage fillEmailField(String email){
    emailField.sendKeys(email);
    return this;
  }

  public LoginPage fillPasswordField(String password){
    passwordField.sendKeys(password);
    return this;
  }

  public BasePage clickByLoginButton(){
    loginButton.click();
    Alert alert = alertHandler.getAlertIfPresent();
    if (alert != null){
      alert.accept();
      return  new LoginPage(driver);
    }else {return new ContactsPage(driver);}
  }

  public Alert clickByLoginButtonAlert(){
    loginButton.click();
    return alertHandler.getAlertIfPresent();
  }

  public Alert clickByRegistrationButton(){
    registrationButton.click();
    return alertHandler.getAlertIfPresent();
  }

}
