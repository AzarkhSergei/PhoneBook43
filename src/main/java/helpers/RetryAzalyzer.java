package helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAzalyzer implements IRetryAnalyzer {
  int retryCount = 0;
  int maxValue = 3;

  @Override
  public boolean retry(ITestResult iTestResult) {
    if(retryCount<maxValue){
      retryCount++;
      return true;
    }
    return false;
  }
}
