package com.example.classproject.Pojo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Calendar;

public class JWTobject {
    public static String getToken(String userName,String Password) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,3600);
        String Token = JWT.create()
                .withClaim("userName", userName)
                .withClaim("Password", Password)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("@%$$#@#$$Qw"));
        return Token;
    }
    public static Boolean vertify(String Token){
        try {
            JWT.require(Algorithm.HMAC256("@%$$#@#$$Qw")).build().verify(Token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
