package com.stock.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secret;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }


    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

//    public Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private boolean isTokenExpired(String token) {
//        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
//    }
//
//    public boolean validate(String token) {
//        try {
//            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            LOG.error("Invalid JWT signature - {}", ex.getMessage());
//        } catch (MalformedJwtException ex) {
//            LOG.error("Invalid JWT token - {}", ex.getMessage());
//        } catch (ExpiredJwtException ex) {
//            LOG.error("Expired JWT token - {}", ex.getMessage());
//        } catch (UnsupportedJwtException ex) {
//            LOG.error("Unsupported JWT token - {}", ex.getMessage());
//        } catch (IllegalArgumentException ex) {
//            LOG.error("JWT claims string is empty - {}", ex.getMessage());
//        }
//        return false;
//    }
}
