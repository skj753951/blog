package com.kfc.Util;

import com.kfc.contant.MessageConstant;
import com.kfc.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final static SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final Duration ACCESS_TOKEN_DURATION = Duration.ofMinutes(15);
    private static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(7);

    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(userDetails, ACCESS_TOKEN_DURATION);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(userDetails, REFRESH_TOKEN_DURATION);
    }

    private String buildToken(UserDetails userDetails, Duration duration) {
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(duration)))
                .signWith(SECRET_KEY)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new BusinessException(MessageConstant.JWT_ERROR.getCode(),MessageConstant.JWT_ERROR.getMessage());
        }
    }

}
