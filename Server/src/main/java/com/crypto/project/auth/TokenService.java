package com.crypto.project.auth;

import static com.crypto.project.auth.SecurityConstants.EXPIRATION_TIME;
import static com.crypto.project.auth.SecurityConstants.HEADER_STRING;
import static com.crypto.project.auth.SecurityConstants.SECRET;
import static com.crypto.project.auth.SecurityConstants.TOKEN_PREFIX;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class TokenService {
    
    //private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    //private static final Cache restApiAuthTokenCache = CacheManager.getInstance().getCache("restApiAuthTokenCache");
    public static final int HALF_AN_HOUR_IN_MILLISECONDS = 30 * 60 * 1000;
 
//    @Scheduled(fixedRate = HALF_AN_HOUR_IN_MILLISECONDS)
//    public void evictExpiredTokens() {
//        logger.info("Evicting expired tokens");
//        restApiAuthTokenCache.evictExpiredElements();
//    }
 
    public String generateNewToken(Authentication auth) {
    	return Jwts.builder()
		.setIssuedAt(new Date(System.currentTimeMillis()))
        .setSubject(((User) auth.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
    }
    
    UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        try
        {
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
    }
    
    public boolean validateToken(String authToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}
 
//    public void store(String token, Authentication authentication) {
//        restApiAuthTokenCache.put(new Element(token, authentication));
//    }
 
//    public boolean contains(String token) {
//        return restApiAuthTokenCache.get(token) != null;
//    }
// 
//    public Authentication retrieve(String token) {
//        return (Authentication) restApiAuthTokenCache.get(token).getObjectValue();
//    }
}