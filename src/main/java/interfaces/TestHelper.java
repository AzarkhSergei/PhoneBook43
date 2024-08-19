package interfaces;

import helpers.EmailGenerator;
import helpers.PasswordGenerator;

public interface TestHelper {

  public static final String XML_DATA_FILE = "src/main/resources/data.xml";
  public static final String CORRECT_EMAIL = "correctEmail";
  public static final String CORRECT_PASSWORD = "correctPassword";
  public static final String PASSWORD_WITHOUT_SPECIAL_SYMBOL = "passwordWithoutSpecialSymbol";
  public static final String PASSWORD_WITHOUT_DIGITS = "passwordWithoutDigit";
  public static final String PASSWORD_WITHOUT_UPPERCASE_LETTER = "passwordWithoutUppercaseLetter";
  public static final String PASSWORD_WITHOUT_LOWERCASE_LETTER = "passwordWithoutLowercaseLetter";
  public static final String PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS = "passwordWithLengthLess8Symbols";
  public static final String PASSWORD_WITH_LENGTH_MORE_15_SYMBOLS = "passwordWithLengthMore15Symbols";
  public static final String RANDOM_EMAIL = EmailGenerator.generateEmail(10,5,3);
  public static final String RANDOM_PASSWORD = PasswordGenerator.generateRandomPassword();


  public static final String EXPECTED_ALERT_REGISTRATION = "Wrong email or password format";
  public static final String EXPECTED_ALERT_LOGIN = "Wrong email or password";

}
