package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWriterXML {

  public static void main(String[] args) {
    PropertiesWriterXML pp = new PropertiesWriterXML();
    pp.setProperty("correctEmail", "tester19871987@gmail.com", false, "src/main/resources/data.xml");
    pp.setProperty("correctPassword", "Tester19871987!", false, "src/main/resources/data.xml");
    pp.setProperty("passwordWithoutSpecialSymbol", "Tester19871987", false, "src/main/resources/data.xml");
    pp.setProperty("passwordWithoutUppercaseLetter", "tester19871987!", false, "src/main/resources/data.xml");
    pp.setProperty("passwordWithoutLowercaseLetter", "TESTER19871987!", false, "src/main/resources/data.xml");
    pp.setProperty("passwordWithoutDigit", "TesterTesterrr!", false, "src/main/resources/data.xml");
    pp.setProperty("passwordWithLengthLess8Symbols", "Testr8!", false, "src/main/resources/data.xml");
    pp.setProperty("passwordWithLengthMore15Symbols", "Testerr19871987!", false, "src/main/resources/data.xml");

  }

  Properties properties = new Properties();

  public void setProperty(String key, String value, boolean clearFile, String path){
    if(!clearFile){
      try(FileInputStream fileInputStream = new FileInputStream(path)){
        properties.loadFromXML(fileInputStream);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }


    properties.setProperty(key, value);
    try(FileOutputStream fileOutputStream = new FileOutputStream(path)){
      properties.storeToXML(fileOutputStream, null);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

}
