package pages;

import enums.ContactFieldsType;
import helpers.AddressGenerator;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContactsPage extends BasePage{

  public enum FieldType{
    NAME,
    LASTNAME,
    PHONE,
    EMAIL,
    ADDRESS,
    DESCRIPTION
  }

  @FindBy(xpath = "//button[contains(text(),'Sign')]")
  private WebElement signButton;
  @FindBy(xpath = "//button[contains(text(),'Save')]")
  private WebElement saveButton;
  @FindBy(xpath = "//button[contains(text(),'Edit')]")
  private WebElement editButton;
  @FindBy(xpath = "//input[@placeholder='Name']")
  private WebElement nameField;
  @FindBy(xpath = "//input[@placeholder='Last Name']")
  private WebElement lastNameField;
  @FindBy(xpath = "//input[@placeholder='Phone']")
  private WebElement phoneField;
  @FindBy(xpath = "//input[@placeholder='email']")
  private WebElement emailField;
  @FindBy(xpath = "//input[@placeholder='Address']")
  private WebElement addressField;
  @FindBy(xpath = "//input[@placeholder='desc']")
  private WebElement descriptionField;

  public ContactsPage(WebDriver driver){
    setDriver(driver);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
  }

  public boolean isSignButtonPersist(){
    return isElementPersist(signButton);
  }

  protected List<WebElement> getContactsList(){
    return driver.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
  }

  public int getContactListSize(){
    return getContactsList().size();
  }

  public boolean getDataFromContactList(Contact contact){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement nameContact = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//h2[contains(text(),'"+ contact.getName().toString() +"')]")));
    nameContact.click();
    editButton.click();
    Contact testContact = new Contact(
        nameField.getAttribute("value"),
        lastNameField.getAttribute("value"),
        phoneField.getAttribute("value"),
        emailField.getAttribute("value"),
        addressField.getAttribute("value"),
        descriptionField.getAttribute("value")
    );
    return testContact.equals(contact);
  }

  public boolean editContactField(Contact contact, ContactFieldsType contactFieldsType){
    if(findContact(contact)){
      editButton.click();
      switch (contactFieldsType) {
        case NAME:
          return updateField(nameField, NameAndLastNameGenerator.generateName());
        case LASTNAME:
          return updateField(lastNameField, NameAndLastNameGenerator.generateLastName());
        case PHONE:
          return updateField(phoneField, PhoneNumberGenerator.generatePhoneNumber());
        case EMAIL:
          return updateField(emailField, EmailGenerator.generateEmail(10, 5, 3, EmailGenerator.EmailType.VALID));
        case ADDRESS:
          return updateField(addressField, AddressGenerator.generateAddress());
        case DESCRIPTION:
          return updateField(descriptionField, "New test description...");
      }
    }
    return false;
  }

  private boolean findContact(Contact contact) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement contactFromTheList = wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[h2[contains(text(),'"+contact.getName()+"')] " +
              "and h3[contains(text(), '"+ contact.getPhone() +"')]]")));
      contactFromTheList.click();
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }


  private boolean updateField(WebElement fieldElement, String newValue) {
    fieldElement.clear();
    fieldElement.sendKeys(newValue);
    saveButton.click();
/*    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }*/
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement contactFromTheList = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//div[text()='"+newValue+"'] | " +
            "//h2[text()='"+newValue+"'] | //h3[text()='"+newValue+"']")));
    editButton.click();
    return fieldElement.getAttribute("value").equals(newValue);
  }

}
