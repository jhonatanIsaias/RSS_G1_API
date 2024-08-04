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

/**
 * Serviço responsável pela geração e validação de tokens JWT.
 */
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT para um usuário específico.
     *
     * @param user Objeto User para o qual o token será gerado.
     * @return Um token JWT como String.
     * @throws RuntimeException Caso ocorra um erro durante a criação do token.
     */
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("RSS_G1")
                    .withSubject(user.getLogin())
                    .withExpiresAt(tokenExpireTime())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao criar ou expirar token: " + exception);
        }
    }

    /**
     * Valida um token JWT.
     *
     * @param token O token JWT a ser validado.
     * @return O assunto (subject) do token JWT, que geralmente é o login do usuário.
     * @throws RuntimeException Caso ocorra um erro durante a validação do token.
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("RSS_G1")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido: " + exception);
        }
    }

    /**
     * Recupera o login (subject) de um token JWT sem verificar a assinatura.
     *
     * @param token O token JWT.
     * @return O assunto (subject) do token JWT.
     * @throws RuntimeException Caso o token seja inválido.
     */
    public static String getSubjectFromToken(String token) {
        try {
            return JWT.decode(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido: " + exception.getMessage(), exception);
        }
    }

    /**
     * Calcula o tempo de expiração do token.
     *
     * @return Um objeto Instant representando o tempo de expiração do token.
     */
    private Instant tokenExpireTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
