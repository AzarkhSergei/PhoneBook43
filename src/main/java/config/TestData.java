package config;

import helpers.EmailGenerator;

public class TestData {

  // login and password
  public static final String correctEmail = "tester19871987@gmail.com";
  public static final String correctPassword = "Tester19871987!";
  public static final String randomEmail = EmailGenerator.generateEmail(10,5,3);
  public static final String passwordWithoutSpecialSymbol = "Tester19871987";
  public static final String passwordWithoutUppercaseLetter = "tester19871987!";
  public static final String passwordWithoutLowercaseLetter = "TESTER19871987!";
  public static final String passwordWithoutDigit = "TesterTesterrr!";
  public static final String passwordWithLengthLess8Symbols = "Testr8!";
  public static final String passwordWithLengthMore15Symbols = "Testerr19871987!";

  // web elements
  public static final String buttonSingOut = "//button[contains(text(), 'Sign ')]";


  // other variables
  public static final String expectedAlert = "Wrong email or password format";


}
