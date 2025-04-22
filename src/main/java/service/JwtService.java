package service;

import java.awt.RenderingHints.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


public class JwtService {
	private String SECRET_KEY="dkrgkglştskyşlhksh";

	public String findUsername(String token) {
		
		return exportToken(token,Claims::getSubject);
	}

	private <T> T exportToken(java.lang.String token, Function<Claims,T> claimsFunction) {
	
		final Claims claims = Jwts.parserBuilder().setSigningKeyResolver((SigningKeyResolver) getKey()).build().parseClaimsJws(token).getBody();
		return claimsFunction.apply(claims);
	}

    private Key getKey() {
    	byte[] key = Decoders.BASE64.decode(SECRET_KEY);
    	return (Key) Keys.hmacShaKeyFor(key);
    }

	public boolean tokenControl(String jwt, UserDetails userDetails) {//bendeki usrname ile gelen username aynı mı ve süresi geçmiiş mi
		
		final String username = findUsername(jwt);
		return (username.equals(userDetails.getUsername())&& !exportToken(jwt, Claims::getExpiration).before(new Date(0)));
	}

	public Object generateToken(UserDetails user) {
		return Jwts.builder()
				.setClaims(new HashMap<>())
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith((java.security.Key) getKey(),SignatureAlgorithm.HS256)
				.compact();
	}

}
