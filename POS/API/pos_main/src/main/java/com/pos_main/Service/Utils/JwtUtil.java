package com.pos_main.Service.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos_main.Domain.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {

    @Value("${pos.web.jwtSecret}")
    private  String jwtSecret;


    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private ObjectMapper objectMapper;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token)  {

        try {
            Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());

        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());

        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());

        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());

        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());

        }
        return false;
    }



    public String GenerateToken(CustomUserDetails userDetails){
        System.out.println(userDetails.getUsername());
        Map<String,Object> claims=new HashMap<>();
        claims.put("roles",userDetails.getAuthorities());
        return createToken(claims,userDetails.getUsername());
    }



    private String createToken(Map<String,Object> claims,String username) {
        return Jwts.builder()
                .setIssuer("pos")
                .setIssuedAt(new Date())
                .setSubject(username)
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60*60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}





