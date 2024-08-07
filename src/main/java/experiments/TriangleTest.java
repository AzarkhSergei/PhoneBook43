package experiments;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleTest {

  @Test
  public void triangleEquilateralTestPositive(){
    Allure.description("Equilateral Triangle test");
    Assert.assertEquals(Triangle.getTriangleType(5,5,5), TriangleType.EQUILATERAL);
  }

  @Test
  public void triangleIsoscelesTestPositive(){
    Allure.description("Isosceles Triangle test");
    Assert.assertEquals(Triangle.getTriangleType(5,5,1), TriangleType.ISOSCELES);
  }

  @Test
  public void triangleScaleneTestPositive(){
    Allure.description("Scalene Triangle test");
    Assert.assertEquals(Triangle.getTriangleType(3,6,4), TriangleType.SCALENE);
  }

  @Test
  public void triangleTestNegative(){
    Allure.description("Invalid Triangle test");
    Assert.assertEquals(Triangle.getTriangleType(1,1,5), TriangleType.INVALID);
  }

  @Test
  public void triangleTestNegative2(){
    Allure.description("Invalid Triangle test");
    Assert.assertEquals(Triangle.getTriangleType(0,0,0), TriangleType.INVALID);
  }

  @Test
  public void triangleTestNegative3(){
    Allure.description("Invalid Triangle test");
    Assert.assertEquals(Triangle.getTriangleType(-5,5,5), TriangleType.INVALID);
  }
}
