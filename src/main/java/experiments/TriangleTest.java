package experiments;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleTest {

  @Test
  public void triangleTest1(){
    Assert.assertEquals(Triangle.getTriangleType(5,5,5), TriangleType.EQUILATERAL);
  }

  @Test
  public void triangleTest2(){
    Triangle.getTriangleType(0,0,0);
  }

  @Test
  public void triangleTest3(){
    Triangle.getTriangleType(-0,0,0);
  }

  @Test
  public void triangleTest4(){
    Triangle.getTriangleType(1,1,5);
  }
}
