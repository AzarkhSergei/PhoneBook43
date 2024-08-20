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

  public static String generateEmail(int a, int b, int c, EmailType emailType){
    StringBuilder email = new StringBuilder();
    switch(emailType){
      case VALID:
         email.append(generateValidEmail(a,b,c));
         break;
      case WITHOUT_AT:
        email.append(generateEmailWithoutAt(a,b,c));
        break;
      case WITHOUT_USERNAME:
        email.append(generateEmailWithoutUserName(a,b,c));
        break;
      case WITHOUT_DOMAIN:
        email.append(generateEmailWithoutDomain(a,b,c));
        break;
      case WITHOUT_DOT:
        email.append(generateEmailWithoutDot(a,b,c));
        break;
      case WITH_INVALID_CHAR:
        email.append(generateEmailWithInvalidChar(a,b,c));
        break;
    }
    return email.toString();
  }

  private static String generateValidEmail(int a, int b, int c){
    if(a<=0 || b<=0 || c<=0){
      throw new IllegalArgumentException("Length must be positive!");
    }
    StringBuilder email = new StringBuilder();
    for(int i=0; i<a;i++){
      email.append(randomCharForFirstPart());
    }
    email.append('@');
    for(int i=0; i<b;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    email.append('.');
    for(int i=0; i<c;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    return email.toString();
  }

  private static String generateEmailWithoutAt(int a, int b, int c){
    if(a<=0 || b<=0 || c<=0){
      throw new IllegalArgumentException("Length must be positive!");
    }
    StringBuilder email = new StringBuilder();
    for(int i=0; i<a;i++){
      email.append(randomCharForFirstPart());
    }
    for(int i=0; i<b;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    email.append('.');
    for(int i=0; i<c;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    return email.toString();
  }

  private static String generateEmailWithoutUserName(int a, int b, int c){
    if(b<=0 || c<=0){
      throw new IllegalArgumentException("Length must be positive!");
    }
    StringBuilder email = new StringBuilder();
    email.append('@');
    for(int i=0; i<b;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    email.append('.');
    for(int i=0; i<c;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    return email.toString();
  }

  private static String generateEmailWithoutDomain(int a, int b, int c){
    if(a<=0 || c<=0){
      throw new IllegalArgumentException("Length must be positive!");
    }
    StringBuilder email = new StringBuilder();
    for(int i=0; i<a;i++){
      email.append(randomCharForFirstPart());
    }
    email.append('@');
    email.append('.');
    for(int i=0; i<c;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    return email.toString();
  }

  private static String generateEmailWithoutDot(int a, int b, int c){
    if(a<=0 || b<=0 || c<=0){
      throw new IllegalArgumentException("Length must be positive!");
    }
    StringBuilder email = new StringBuilder();
    for(int i=0; i<a;i++){
      email.append(randomCharForFirstPart());
    }
    email.append('@');
    for(int i=0; i<b;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    for(int i=0; i<c;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    return email.toString();
  }

  private static String generateEmailWithInvalidChar(int a, int b, int c){
    if(a<=0 || b<=0 || c<=0){
      throw new IllegalArgumentException("Length must be positive!");
    }
    StringBuilder email = new StringBuilder();
    for(int i=0; i<a;i++){
      email.append(randomCharForFirstPart());
    }
    email.append(randomInvalidChar());
    for(int i=0; i<b;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    email.append('.');
    for(int i=0; i<c;i++){
      email.append(randomCharForSecondAndThirdPart());
    }
    return email.toString();
  }

  private static char randomCharForFirstPart() {
    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    return chars.charAt(RANDOM.nextInt(chars.length()));
  }

  private static char randomCharForSecondAndThirdPart() {
    String chars = "abcdefghijklmnopqrstuvwxyz";
    return chars.charAt(RANDOM.nextInt(chars.length()));
  }

  private static char randomInvalidChar(){
    String invalidChars = "!@#$%^&*()~\"\\'?;:,.";
    return invalidChars.charAt(RANDOM.nextInt(invalidChars.length()));
  }

}
