package experiments;

public enum TriangleType {

  EQUILATERAL("EQUILATERAL"),
  ISOSCELES("ISOSCELES"),
  SCALENE("SCALENE"),
  INVALID("INVALID");

  private final String type;

  TriangleType(String type){
    this.type = type;
  }

  @Override
  public String toString() {
    return "TriangleType" +
        "type= " + type;
  }
}

