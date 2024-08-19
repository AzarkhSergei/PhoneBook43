package helpers;
import java.util.Random;

public class EmailGenerator {

  private static final Random RANDOM = new Random();
//TASK
  public enum EmailType{
    VALID,
    WITHOUT_AT,
    WITHOUT_USERNAME,
    WITHOUT_DOMAIN,
    WITHOUT_DOT,
    WITH_INVALID_CHAR
  }

  private static char randomInvalidChar(){
    String invalidChars = "!@#$%^&*()~\"\\'?;:,.";
    return invalidChars.charAt(RANDOM.nextInt(invalidChars.length()));
  }

  public static String generateEmailWithInvalidChar(int a, int b, int c){
    StringBuilder email = new StringBuilder();
    for(int i=0; i<a;i++){
      email.append(randomCharForFirstPart());
    }
    email.append('@');
    for(int i=0; i<b;i++){
      email.append(randomChar());
    }
    email.append('.');
    for(int i=0; i<c;i++){
      email.append(randomChar());
    }
    return email.toString();
  }

  public static String generateEmail(int a, int b, int c){
    if(a<=0 || b<=0 || c<=0){
      throw new IllegalArgumentException("Length must be positive!");
    }
    StringBuilder email = new StringBuilder();
    for(int i=0; i<a;i++){
      email.append(randomCharForFirstPart());
    }
    email.append('@');
    for(int i=0; i<b;i++){
      email.append(randomChar());
    }
    email.append('.');
    for(int i=0; i<c;i++){
      email.append(randomChar());
    }
    return email.toString();
  }

  private static char randomChar(){
    return (char) ('a'+Math.random()*('z'-'a'));
  }

  private static char randomCharForFirstPart() {
    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    return chars.charAt(RANDOM.nextInt(chars.length()));
  }

}
