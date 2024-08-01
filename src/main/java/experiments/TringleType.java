package experiments;

public enum TringleType {

  EQUILATERAL("EQUILATERAL"),
  ISOSCELES("ISOSCELES"),
  SCALENE("SCALENE"),
  INVALID("INVALID");

  private final String type;

  TringleType(String type){
    this.type = type;
  }

  @Override
  public String toString() {
    return "TringleType" +
        "type= " + type;
  }
}

