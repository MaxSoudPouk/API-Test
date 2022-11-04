package com.etl.kyc.etlkycsimregisterapi;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.expression.spel.support.StandardTypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class createjwt {
	
	 static boolean jwtboolean;
	 
	static String decodeuserid ="";

	        User user =new User();
			String userid=user.getUser_id();
	
	// static String originalInput = "etlkyc882022";
	 
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		
		  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
		//We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(id);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	            .setIssuedAt(now)
	            .setSubject(subject)
	            .setIssuer(issuer)
	            .signWith(signatureAlgorithm, signingKey);
	  
	    //if it has been specified, let's add the expiration
	    if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis*60*60*1000;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}


	
	
	public static Claims decodeJWT(String jwt, String userid) {
		
		
		System.out.println("jwt==========="+ jwt);
		System.out.println("userid========"+ userid);
		
	
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(userid))
	            .parseClaimsJws(jwt).getBody();
	    
	     decodeuserid= claims.getId();
	 
	    System.out.println("decodeuserid123456========" +decodeuserid + "//userid====="+ userid);
	   
	    
	    if(decodeuserid.equals(userid)) {
			//return true;
			jwtboolean=true;
			System.out.println("jwtbooleantrue========"+ jwtboolean);
		} else {
			
			jwtboolean=false;
			System.out.println("jwtbooleanfalse========"+ jwtboolean);
			
		}
	    //===================================
	    
	    return claims;
	}
	

}
