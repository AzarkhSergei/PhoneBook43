package config;

import helpers.EmailGenerator;

public class TestData {

  // login and password
  public static final String correctEmail = "tester19871987@gmail.com";
  public static final String correctPassword = "Tester19871987!";
  public static final String randomEmail = EmailGenerator.generateEmail(10,5,3);


  // web elements
  public static final String buttonSingOut = "//button[contains(text(), 'Sign ')]";

}
