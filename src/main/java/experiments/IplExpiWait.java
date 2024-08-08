package experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class IplExpiWait {

  public static void main(String[] args) {
    long startTime, endTime, duration;
    startTime = System.currentTimeMillis();
    waitForAnElementExplicit();
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    System.out.println("Expl --> " + duration + " ms");

    startTime = System.currentTimeMillis();
    waitForAnElementImplicit();
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    System.out.println("Impl --> " + duration + " ms");
  }


  public static void waitForAnElementImplicit(){
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
    WebElement button = driver.findElement(By.xpath("//div[@id='start']/button"));
    button.click();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    WebElement h4 = driver.findElement(By.xpath("//div[@id='finish']/h4"));
    System.out.println(h4.getText());
    driver.quit();
  }


  public static void waitForAnElementExplicit(){
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
    WebElement button = driver.findElement(By.xpath("//div[@id='start']/button"));
    button.click();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    WebElement h4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
    //WebElement h4 = driver.findElement(By.xpath("//div[@id='finish']/h4"));
    System.out.println(h4.getText());
    driver.quit();
  }

}
