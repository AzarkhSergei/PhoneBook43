package pages;

import helpers.NameAndLastNameGenerator;
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
    WebElement editButton = driver.findElement(By.xpath("//button[contains(text(),'Edit')]"));
    editButton.click();
    WebElement elementName = driver.findElement(By.xpath("//input[@placeholder='Name']"));
    String elementNameValue = elementName.getAttribute("value");
    WebElement elementLastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
    String elementLastNameValue = elementLastName.getAttribute("value");
    WebElement elementPhone = driver.findElement(By.xpath("//input[@placeholder='Phone']"));
    String elementPhoneValue = elementPhone.getAttribute("value");
    WebElement elementEmail = driver.findElement(By.xpath("//input[@placeholder='email']"));
    String elementEmailValue = elementEmail.getAttribute("value");
    WebElement elementAddress = driver.findElement(By.xpath("//input[@placeholder='Address']"));
    String elementAddressValue = elementAddress.getAttribute("value");
    WebElement elementDesc = driver.findElement(By.xpath("//input[@placeholder='desc']"));
    String elementDescValue = elementDesc.getAttribute("value");

    Contact testContact = new Contact();
    testContact.setName(elementNameValue);
    testContact.setLastName(elementLastNameValue);
    testContact.setPhone(elementPhoneValue);
    testContact.setEmail(elementEmailValue);
    testContact.setAddress(elementAddressValue);
    testContact.setDescription(elementDescValue);

    return testContact.equals(contact);
  }

  public boolean editContactField(Contact contact, FieldType fieldType){
    if(getDataFromContactList(contact)){
      switch (fieldType){
        case NAME:
          WebElement elementName = driver.findElement(By.xpath("//input[@placeholder='Name']"));
          String elementNameValue = elementName.getAttribute("value");
          String newNameValue;
          do {
            newNameValue = NameAndLastNameGenerator.generateName();
          } while (newNameValue.equals(elementNameValue));
          elementName.clear();
          elementName.sendKeys(newNameValue);
          WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
          saveButton.click();
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
          WebElement nameContact = wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//h2[contains(text(),'"+ newNameValue +"')]")));
          nameContact.click();
          WebElement editButton = driver.findElement(By.xpath("//button[contains(text(),'Edit')]"));
          editButton.click();
          elementName = driver.findElement(By.xpath("//input[@placeholder='Name']"));
          String updatedNameValue = elementName.getAttribute("value");
          return newNameValue.equals(updatedNameValue);
        case LASTNAME:
          WebElement elementLastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
          String elementLastNameValue = elementLastName.getAttribute("value");
          String newLastNameValue;
          do {
            newLastNameValue = NameAndLastNameGenerator.generateLastName();
          } while (newLastNameValue.equals(elementLastNameValue));
          elementLastName.clear();
          //elementLastName.sendKeys(newLastNameValue);
          saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
          saveButton.click();
          wait = new WebDriverWait(driver, Duration.ofSeconds(5));
          nameContact = wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//h2[contains(text(),'"+ contact.getName().toString() +"')]")));
          nameContact.click();
          editButton = driver.findElement(By.xpath("//button[contains(text(),'Edit')]"));
          editButton.click();
          elementLastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
          String updatedLastNameValue = elementLastName.getAttribute("value");
          return newLastNameValue.equals(updatedLastNameValue);
      }
    }
    return false;
  }
}
