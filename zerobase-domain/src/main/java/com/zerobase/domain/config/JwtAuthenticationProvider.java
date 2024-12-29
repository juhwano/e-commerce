package com.zerobase.domain.config;

import com.zerobase.domain.domain.common.UserType;
import com.zerobase.domain.domain.common.UserVo;
import com.zerobase.domain.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Objects;

public class JwtAuthenticationProvider {

	private String secretKey = "secreyKey";

	private long tokenValidTime = 1000L * 60 * 60 * 24;

	public String createToken(String userPk, Long id, UserType userType) {
		Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPk)).setId(Aes256Util.encrypt(id.toString()));
		claims.put("roles", userType);
		Date now = new Date();
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + tokenValidTime))
			.signWith(SignatureAlgorithm.HS256, secretKey) // secret -> hs256 -> signature에 넣기
			.compact();
	}

	public boolean validateToken(String jwtToken) {
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
			return !claimsJws.getBody().getExpiration().before(new Date()); // 현재 시간보다 적을 때
		} catch (Exception e) {
			return false;
		}
	}
	//토큰을 받아서 유저에게 반환
	public UserVo getUserVo(String token) {
		Claims c = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		return new UserVo(Long.valueOf(Objects.requireNonNull(Aes256Util.decrypt(c.getId()))), Aes256Util.decrypt(c.getSubject()));
	}



}
