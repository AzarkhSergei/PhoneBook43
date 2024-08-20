package interfaces;

import helpers.EmailGenerator;
import helpers.PasswordGenerator;

public interface TestHelper {

  public static final String XML_DATA_FILE = "src/main/resources/data.xml";
  public static final String CORRECT_EMAIL = "correctEmail";
  public static final String CORRECT_PASSWORD = "correctPassword";
  public static final String PASSWORD_WITHOUT_SPECIAL_SYMBOL = PasswordGenerator.generatePassword(PasswordGenerator.PasswordType.PASSWORD_WITHOUT_SPECIAL_SYMBOL);
  public static final String PASSWORD_WITHOUT_DIGITS = PasswordGenerator.generatePassword(PasswordGenerator.PasswordType.PASSWORD_WITHOUT_DIGITS);
  public static final String PASSWORD_WITHOUT_UPPERCASE_LETTER = PasswordGenerator.generatePassword(PasswordGenerator.PasswordType.PASSWORD_WITHOUT_UPPERCASE_LETTER);
  public static final String PASSWORD_WITHOUT_LOWERCASE_LETTER = PasswordGenerator.generatePassword(PasswordGenerator.PasswordType.PASSWORD_WITHOUT_LOWERCASE_LETTER);
  public static final String PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS = PasswordGenerator.generatePassword(PasswordGenerator.PasswordType.PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS);
  public static final String PASSWORD_WITH_LENGTH_MORE_15_SYMBOLS = PasswordGenerator.generatePassword(PasswordGenerator.PasswordType.PASSWORD_WITH_LENGTH_MORE_15_SYMBOLS);
  public static final String RANDOM_EMAIL = EmailGenerator.generateEmail(10,5,3, EmailGenerator.EmailType.VALID);
  public static final String RANDOM_PASSWORD = PasswordGenerator.generatePassword(PasswordGenerator.PasswordType.VALID);
  public static final String RANDOM_EMAIL_WITHOUT_AT = EmailGenerator.generateEmail(10,5,3, EmailGenerator.EmailType.WITHOUT_AT);
  public static final String RANDOM_EMAIL_WITHOUT_USER_NAME = EmailGenerator.generateEmail(10,5,3, EmailGenerator.EmailType.WITHOUT_USERNAME);
  public static final String RANDOM_EMAIL_WITHOUT_DOMAIN = EmailGenerator.generateEmail(10,5,3, EmailGenerator.EmailType.WITHOUT_DOMAIN);
  public static final String RANDOM_EMAIL_WITHOUT_DOT = EmailGenerator.generateEmail(10,5,3, EmailGenerator.EmailType.WITHOUT_DOT);
  public static final String RANDOM_EMAIL_WITH_INVALID_CHAR = EmailGenerator.generateEmail(10,5,3, EmailGenerator.EmailType.WITH_INVALID_CHAR);


  public static final String EXPECTED_ALERT_REGISTRATION = "Wrong email or password format";
  public static final String EXPECTED_ALERT_LOGIN = "Wrong email or password";

}
