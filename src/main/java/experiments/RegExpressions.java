package experiments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpressions {
  public static void main(String[] args) {
    String text = "You can reach me out at +97253 1111111 or +972532222222 Have a good day!";
    String[] regexp = {"\\+972\\d{2} \\d{7}", "\\+972\\d{2}\\d{7}"};
    for (String reg : regexp){
      Pattern pattern = Pattern.compile(reg);
      Matcher matcher = pattern.matcher(text);
      while(matcher.find()){
        System.out.println("The number is:" + matcher.group());
      }
    }



    /*String str = "kdjwlkfjwk YTS iy56 abcd jjkfew7889 8udewf89w";
    Pattern pattern = Pattern.compile("abcd");
    Matcher matcher = pattern.matcher(str);
    while(matcher.find()){
      System.out.println(matcher.group());
    }*/

  }
}
