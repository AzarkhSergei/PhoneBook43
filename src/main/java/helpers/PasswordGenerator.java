package helpers;

import java.util.Random;

public class PasswordGenerator {

  public static void main(String[] args) {
    System.out.println(generateRandomPassword());
  }

  private static final Random random = new Random();

  public static String generateRandomPassword(){
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

}
