package br.edu.ifs.rss_g1.notices_g1.service;

import br.edu.ifs.rss_g1.notices_g1.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("RSS_G1")
                    .withSubject(user.getLogin())
                    .withExpiresAt(tokenExpireTime())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("error create token or expire" + exception);
        }
    }
    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("RSS_G1")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw  new RuntimeException("token invalidate"+exception);
        }



    }
    public static String getSubjectFromToken(String token){
        try {
            return JWT.decode(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token: " + exception.getMessage(), exception);
        }
    }

    private Instant tokenExpireTime(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
