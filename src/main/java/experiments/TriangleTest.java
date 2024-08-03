package experiments;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleTest {

  @Test
  public void triangleEquilateralTestPositive(){
    Assert.assertEquals(Triangle.getTriangleType(5,5,5), TriangleType.EQUILATERAL);
  }

  @Test
  public void triangleIsoscelesTestPositive(){
    Assert.assertEquals(Triangle.getTriangleType(5,5,1), TriangleType.ISOSCELES);
  }

  @Test
  public void triangleScaleneTestPositive(){
    Assert.assertEquals(Triangle.getTriangleType(3,6,4), TriangleType.SCALENE);
  }

  @Test
  public void triangleTestNegative(){
    Assert.assertEquals(Triangle.getTriangleType(1,1,5), TriangleType.INVALID);
  }

  @Test
  public void triangleTestNegative2(){
    Assert.assertEquals(Triangle.getTriangleType(0,0,0), TriangleType.INVALID);
  }

  @Test
  public void triangleTestNegative3(){
    Assert.assertEquals(Triangle.getTriangleType(-5,5,5), TriangleType.INVALID);
  }
}
