package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdExtractor {

  public static String getId(String text){

    Pattern pattern = Pattern.compile("ID: (\\S+)");
    Matcher matcher = pattern.matcher(text);
    if(matcher.find()) {
      return matcher.group(1);
    }else {
      return null;
    }
  }

}
