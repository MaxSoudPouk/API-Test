package com.etl.kyc.etlkycsimregisterapi.security;

import java.security.Key;
import java.util.Date;

//You have the wrong import for SignatureAlgorithm.

//
//Try this:
//
//import io.jsonwebtoken.SignatureAlgorithm;
//
//Instead of:
//
//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;

//https://stormpath.com/blog/jwt-java-create-verify#to-sum-it-up


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//Sample method to construct a JWT

public class JWT_Security_Encode_Decode_Java extends Thread {

    
//    
//    try {
//		// Create session ID to prevent change password on multy using
//		JWT_Security_Encode_Decode_Java encode_Decode_Java = new JWT_Security_Encode_Decode_Java();
//		long ttlMillis = 300000; // 300000 = 5 min
//		jwtTokenStrng = encode_Decode_Java.createJWTSec(md5_segSerailNo, msisdn, ttlMillis, msisdn,
//				md5_segSerailNo);
//	} catch (Exception e) {
//		// TODO: handle exception
//	}
//    

    // Sample method to construct a JWT
    public String createJWTSec(String id, long ttlMillis, String userName, String userID) {
    	
    	

	// The JWT signature algorithm we will be using to sign the token
	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	long nowMillis = System.currentTimeMillis();
	Date now = new Date(nowMillis);

	// We will sign our JWT with our ApiKey secret
	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(GlobalParameter.sign_key);
	
	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	// Let's set the JWT Claims
//	JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(msisdn).setIssuer(issuer)
//		.setId(sessionID).signWith(signatureAlgorithm, signingKey);
	
	 //Let's set the JWT Claims
	JwtBuilder   builder = Jwts.builder().setId(id)
            .setIssuedAt(now)
            .setSubject(userName) // User Namw
            .setIssuer(userID)  // User ID
            .signWith(signingKey, signatureAlgorithm);

	// if it has been specified, let's add the expiration
	if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	    Date exp = new Date(expMillis);
	    builder.setExpiration(exp);
	}

	// Builds the JWT and serializes it to a compact, URL-safe string
	return builder.compact();
    }

    
    
    // Sample method to validate and read the JWT
    public boolean deCodeJWT_validate(String jwt, String userID, String userName) {
	try {
		 // System.out.println(" jwtgetUserID========:: " + userID);
		 // System.out.println(" jwtgetuserName========:: " + userName);


	    // This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(GlobalParameter.sign_key)).parseClaimsJws(jwt)
		    .getBody();
//	    
		//  System.out.println("jwtuserID========:: " + userID);
	

// System.out.println(" GlobalParameter.sign_key========:: " + GlobalParameter.sign_key);
//		Claims claims = (Claims) Jwts.parserBuilder()
//			         .requireAudience(GlobalParameter.sign_key)
//			         .build()
//			         .parse(jwt);
			 
		
	    String getUserName = claims.getSubject();
	    String getUserID = claims.getIssuer();
	    
	 // System.out.println(" jwtgetUserName11111========:: " + getUserName);
	 // System.out.println(" jwtgetUserID1111111========:: " + getUserID);

//	    String isSessionIDJWT = "";
//
//	    try {
//		isSessionIDJWT = claims.getId();
//	    } catch (Exception e) {
//		return false;
//	    }

	    // Get sessionProfile from hash table;.
//	    String localSessionID = "";
//	    try {
//		String sessionStrLocal = GlobalParameter.hMapProfile.get(msisdn.trim());
//
//		String[] parts = sessionStrLocal.split("\\|");
//
//		localSessionID = parts[3].trim();
//
//	    } catch (Exception e) {
//		return false;
//	    }

	    // System.out.println("getMsisdn: " + claims.getSubject());
	    // System.out.println("msisdn: " + msisdn);

	    if (getUserID.equals(userID) && getUserName.equals(getUserName)) {
		// if(getMsisdn.equals(msisdn) && localSessionID.equals(isSessionIDJWT) ) {
		return true;
	    } else {

		return false;
	    }

	    // System.out.println("ID: " + claims.getId());
	    // System.out.println("Subject: " + claims.getSubject());
	    // System.out.println("Issuer: " + claims.getIssuer());
	    // System.out.println("Expiration: " + claims.getExpiration());

	} catch (Exception e) {
		 // System.out.println(" Exception========:: " + e.getMessage());
		
	    return false;
	}
    }
    
    
//    // Sample method to validate and read the JWT
//    public boolean deCodeJWT_validat_session_id(String jwt, String msisdn, String isSessionID) {
//	try {
//
//	    // This line will throw an exception if it is not a signed JWS (as expected)
////	    Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt)
////		    .getBody();
//
//	    // New one 
//	    Claims claims =   (Claims) Jwts.parserBuilder()
//        .requireAudience(GlobalParameter.sign_key)
//        .build()
//        .parse(jwt);
//	    
//	    
//	    String getMsisdn = claims.getSubject();
//	  //  String sessionInJWT = claims.getSubject();
//
//	    String isSessionIDJWT = "";
//
//	    try {
//		isSessionIDJWT = claims.getId();
//	    } catch (Exception e) {
//		return false;
//	    }
//
//
//	    // System.out.println("getMsisdn: " + claims.getSubject());
//	    // System.out.println("msisdn: " + msisdn);
//
//	    if (getMsisdn.equals(msisdn)  && isSessionIDJWT.equals(isSessionID)) {
//		// if(getMsisdn.equals(msisdn) && localSessionID.equals(isSessionIDJWT) ) {
//
//		return true;
//	    } else {
//
//		return false;
//	    }
//
//	    // System.out.println("ID: " + claims.getId());
//	    // System.out.println("Subject: " + claims.getSubject());
//	    // System.out.println("Issuer: " + claims.getIssuer());
//	    // System.out.println("Expiration: " + claims.getExpiration());
//
//	} catch (Exception e) {
//	    return false;
//	}
//    }
//    
    
    
    
    
    
}
