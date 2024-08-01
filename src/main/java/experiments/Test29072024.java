package experiments;

import helpers.RetryAzalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.SecureRandom;

public class Test29072024 {

  @Test(retryAnalyzer = RetryAzalyzer.class)
  public void generateRandomStringTestPositive(){
    Assert.assertEquals(generateRandomString(10).length(), 12);
  }

  public static void main(String[] args) {

    //byte -128 до 127
    //short -32768 до 32767
    //int -2147483648 до 2147483647
    //float 1.2e-38 до 3.4e+38
    //double 2.3e-308 до 1.7e+308

    //boolean true false

    //char symbols

    //System.out.println("My \u001B[32mfirst test!\u001B[0m");
    //dayPicker(1);

    /*for (int i = 1; i <= 10; i++) {
      System.out.println(i);
    }*/

    //int[] numbers = {1,2,3,4,5,6,7,8};

    //System.out.println("INTEGER "+add(12, 12));
    //System.out.println("DOUBLE "+add(12.5, 12.2));

    System.out.println(generateRandomString(33));

  }

  /**
   * generateRandomString generates random string
   * @param length - any integer in correct diapason
   * @return random string
   */
  public static String generateRandomString(int length){
    if (length <= 0 || length > 1000){
      throw new IllegalArgumentException("Length must be between 1 and 1000 inclusive!");
    }

    String characters = "ABCDEFGHIJKLMNOPRSTUVWXYZ1234567890";
    StringBuilder randomString = new StringBuilder(length);
    SecureRandom random = new SecureRandom();

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(characters.length());
      randomString.append(characters.charAt(randomIndex));
    }

    return randomString.toString();
  }

  public static int add(int a, int b){
    return a+b;
  }
  public static double add(double a, double b){
    return a+b;
  }


  //My comment

  /**
   * The method ...
   * @param a my first param
   * @param b my second param
   */
  public static void MyMethod(int a, int b){
    int res = a+b;
  }

  public static void dayPicker(int day){
    switch (day){
      case 1:
        System.out.println("Monday");
        break;
      case 2:
        System.out.println("Tuesday");
        break;
      case 3:
        System.out.println("Wednesday");
        break;
      default:
        System.out.println("Invalid day...");
        break;
    }
  }

}
