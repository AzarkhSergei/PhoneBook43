package experiments;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class TokenGenerator {
  static String secretKey = "xxjnHukuJ7Ty3235289huvih";

  public static void main(String[] args) {
    String token = generateToken("Sergei", "admin", secretKey);
    System.out.println("Token: "+token);
    verifyToken(token, secretKey);
  }

  public static String generateToken(String username, String role, String secretKey){
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    String token = JWT.create()
        .withIssuer("myCompany")
        .withSubject(username)
        .withClaim("role", role)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis()+3600*1000))
        .sign(algorithm);
    return token;
  }

  public static void verifyToken(String token, String secretKey){
    try{
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer("myCompany")
          .build();
      DecodedJWT decodedJWT = verifier.verify(token);
      String subject = decodedJWT.getSubject();
      String role = decodedJWT.getClaim("role").toString();
      Date issuedAt = decodedJWT.getIssuedAt();
      Date expiresAt = decodedJWT.getExpiresAt();
      System.out.println("Subject: "+subject);
      System.out.println("Role: "+role);
      System.out.println("Issued At: "+issuedAt);
      System.out.println("Expires At: "+expiresAt);
    }catch (Exception e){
      System.out.println("Invalid Token....");
    }
  }

}
