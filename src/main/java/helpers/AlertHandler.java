package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import java.time.Duration;

public class AlertHandler extends BasePage {
  public AlertHandler(WebDriver driver) {
    setDriver(driver);
  }

  public static boolean handleAlert(Alert alert, String expectedText){
    if(alert != null){
      String alertText = alert.getText();
      alert.accept();
      return alertText.contains(expectedText);
    }
    System.out.println(" ... !!!");
    return false;
  }

  public static Alert getAlertIfPresent(){
    try{
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
      return wait.until(ExpectedConditions.alertIsPresent());
    }catch (TimeoutException exception){
      System.out.println("There is no alert...");
      return null;
    }
  }
}
