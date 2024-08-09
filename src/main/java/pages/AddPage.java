package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPage extends BasePage {

  @FindBy(xpath = "//input[@placeholder='Name']")
  WebElement nameField;
  @FindBy(xpath = "//input[@placeholder='Last Name']")
  WebElement lastNameField;
  @FindBy(xpath = "//input[@placeholder='Phone']")
  WebElement phoneField;
  @FindBy(xpath = "//input[@placeholder='email']")
  WebElement emailField;
  @FindBy(xpath = "//input[@placeholder='Address']")
  WebElement addressField;
  @FindBy(xpath = "//input[@placeholder='description']")
  WebElement descriptionField;
  @FindBy(xpath = "//button/b[contains(text(), 'Save')]")
  WebElement saveButton;

  public AddPage(WebDriver driver) {
    setDriver(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
  }

  public AddPage fieldNameField(String name) {
    nameField.sendKeys(name);
    return this;
  }

  public AddPage fieldLastNameField(String lastName) {
    lastNameField.sendKeys(lastName);
    return this;
  }

  public AddPage fieldPhoneField(String phone) {
    phoneField.sendKeys(phone);
    return this;
  }

  public AddPage fieldEmailField(String email) {
    emailField.sendKeys(email);
    return this;
  }

  public AddPage fieldAddressField(String address) {
    addressField.sendKeys(address);
    return this;
  }

  public AddPage fieldDescriptionField(String description) {
    descriptionField.sendKeys(description);
    return this;
  }

  private Alert getAlertIfPresent1() {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
      return wait.until(ExpectedConditions.alertIsPresent());
    } catch (TimeoutException exception) {
      System.out.println("There is no alert...");
      return null;
    }
  }

  public BasePage clickBySaveButton() {
    saveButton.click();
    Alert alert = getAlertIfPresent1();
    if (alert != null) {
      alert.accept();
      return this;
    } else {
      return new ContactsPage(driver);
    }
  }


}

