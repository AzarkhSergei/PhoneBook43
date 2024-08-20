package helpers;

import java.util.Random;

public class PasswordGenerator {

  public static void main(String[] args) {
    System.out.println(generatePassword(PasswordType.PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS));
  }

  public enum PasswordType{
    VALID,
    PASSWORD_WITHOUT_SPECIAL_SYMBOL,
    PASSWORD_WITHOUT_DIGITS,
    PASSWORD_WITHOUT_UPPERCASE_LETTER,
    PASSWORD_WITHOUT_LOWERCASE_LETTER,
    PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS,
    PASSWORD_WITH_LENGTH_MORE_15_SYMBOLS,
  }

  private static final Random random = new Random();

  public static String generatePassword(PasswordType passwordType){
    StringBuilder password = new StringBuilder();
    switch (passwordType){
      case VALID:
        password.append(generateValidPassword());
        break;
      case PASSWORD_WITHOUT_SPECIAL_SYMBOL:
        password.append(generatePasswordWithoutSpecialSymbol());
        break;
      case PASSWORD_WITHOUT_DIGITS:
        password.append(generatePasswordWithoutDigits());
        break;
      case PASSWORD_WITHOUT_UPPERCASE_LETTER:
        password.append(generatePasswordWithoutUpperCase());
        break;
      case PASSWORD_WITHOUT_LOWERCASE_LETTER:
        password.append(generatePasswordLowerCase());
        break;
      case PASSWORD_WITH_LENGTH_LESS_8_SYMBOLS:
        password.append(generatePasswordWithLengthLess8Symbols());
        break;
      case PASSWORD_WITH_LENGTH_MORE_15_SYMBOLS:
        password.append(generatePasswordWithLengthMore15Symbols());
        break;
    }
    return password.toString();
  }

  private static String generateValidPassword(){
    StringBuilder password = new StringBuilder();

    String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerChars = "abcdefghijklmnopqrstuvwxyz";
    String digitalChars = "0123456789";
    String specialSymbolChars = "@$#^&*!";

    for(int i=0; i<5; i++){
      char upperChar = upperChars.charAt(random.nextInt(upperChars.length()));
      password.append(upperChar);
      char lowerChar = lowerChars.charAt(random.nextInt(lowerChars.length()));
      password.append(lowerChar);
    }

    for(int i=0; i<4; i++){
      char digitalChar = digitalChars.charAt(random.nextInt(digitalChars.length()));
      password.append(digitalChar);
    }

    for(int i=0; i<1; i++){
      char specialSymbolChar = specialSymbolChars.charAt(random.nextInt(specialSymbolChars.length()));
      password.append(specialSymbolChar);
    }

    return password.toString();
  }

  private static String generatePasswordWithoutSpecialSymbol(){
    StringBuilder password = new StringBuilder();

    String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerChars = "abcdefghijklmnopqrstuvwxyz";
    String digitalChars = "0123456789";

    for(int i=0; i<5; i++){
      char upperChar = upperChars.charAt(random.nextInt(upperChars.length()));
      password.append(upperChar);
      char lowerChar = lowerChars.charAt(random.nextInt(lowerChars.length()));
      password.append(lowerChar);
    }

    for(int i=0; i<4; i++){
      char digitalChar = digitalChars.charAt(random.nextInt(digitalChars.length()));
      password.append(digitalChar);
    }

    return password.toString();
  }

  private static String generatePasswordWithoutDigits(){
    StringBuilder password = new StringBuilder();

    String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerChars = "abcdefghijklmnopqrstuvwxyz";
    String specialSymbolChars = "@$#^&*!";

    for(int i=0; i<9; i++){
      char upperChar = upperChars.charAt(random.nextInt(upperChars.length()));
      password.append(upperChar);
      char lowerChar = lowerChars.charAt(random.nextInt(lowerChars.length()));
      password.append(lowerChar);
    }

    for(int i=0; i<1; i++){
      char specialSymbolChar = specialSymbolChars.charAt(random.nextInt(specialSymbolChars.length()));
      password.append(specialSymbolChar);
    }

    return password.toString();
  }

  private static String generatePasswordWithoutUpperCase(){
    StringBuilder password = new StringBuilder();

    String lowerChars = "abcdefghijklmnopqrstuvwxyz";
    String digitalChars = "0123456789";
    String specialSymbolChars = "@$#^&*!";

    for(int i=0; i<5; i++){
      char lowerChar = lowerChars.charAt(random.nextInt(lowerChars.length()));
      password.append(lowerChar);
    }

    for(int i=0; i<4; i++){
      char digitalChar = digitalChars.charAt(random.nextInt(digitalChars.length()));
      password.append(digitalChar);
    }

    for(int i=0; i<1; i++){
      char specialSymbolChar = specialSymbolChars.charAt(random.nextInt(specialSymbolChars.length()));
      password.append(specialSymbolChar);
    }

    return password.toString();
  }

  private static String generatePasswordLowerCase(){
    StringBuilder password = new StringBuilder();

    String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String digitalChars = "0123456789";
    String specialSymbolChars = "@$#^&*!";

    for(int i=0; i<5; i++){
      char upperChar = upperChars.charAt(random.nextInt(upperChars.length()));
      password.append(upperChar);
    }

    for(int i=0; i<4; i++){
      char digitalChar = digitalChars.charAt(random.nextInt(digitalChars.length()));
      password.append(digitalChar);
    }

    for(int i=0; i<1; i++){
      char specialSymbolChar = specialSymbolChars.charAt(random.nextInt(specialSymbolChars.length()));
      password.append(specialSymbolChar);
    }

    return password.toString();
  }

  private static String generatePasswordWithLengthLess8Symbols(){
    StringBuilder password = new StringBuilder();

    String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerChars = "abcdefghijklmnopqrstuvwxyz";
    String digitalChars = "0123456789";
    String specialSymbolChars = "@$#^&*!";

    for(int i=0; i<2; i++){
      char upperChar = upperChars.charAt(random.nextInt(upperChars.length()));
      password.append(upperChar);
      char lowerChar = lowerChars.charAt(random.nextInt(lowerChars.length()));
      password.append(lowerChar);
    }

    for(int i=0; i<2; i++){
      char digitalChar = digitalChars.charAt(random.nextInt(digitalChars.length()));
      password.append(digitalChar);
    }

    for(int i=0; i<1; i++){
      char specialSymbolChar = specialSymbolChars.charAt(random.nextInt(specialSymbolChars.length()));
      password.append(specialSymbolChar);
    }

    return password.toString();
  }

  private static String generatePasswordWithLengthMore15Symbols(){
    StringBuilder password = new StringBuilder();

    String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerChars = "abcdefghijklmnopqrstuvwxyz";
    String digitalChars = "0123456789";
    String specialSymbolChars = "@$#^&*!";

    for(int i=0; i<7; i++){
      char upperChar = upperChars.charAt(random.nextInt(upperChars.length()));
      password.append(upperChar);
      char lowerChar = lowerChars.charAt(random.nextInt(lowerChars.length()));
      password.append(lowerChar);
    }

    for(int i=0; i<8; i++){
      char digitalChar = digitalChars.charAt(random.nextInt(digitalChars.length()));
      password.append(digitalChar);
    }

    for(int i=0; i<1; i++){
      char specialSymbolChar = specialSymbolChars.charAt(random.nextInt(specialSymbolChars.length()));
      password.append(specialSymbolChar);
    }

    return password.toString();
  }


}
