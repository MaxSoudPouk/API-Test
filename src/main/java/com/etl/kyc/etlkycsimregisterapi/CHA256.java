package com.etl.kyc.etlkycsimregisterapi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CHA256 {
	
	public String CHA256(String sha256) throws NoSuchAlgorithmException {
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte hashBytes[] = messageDigest.digest(sha256.getBytes(StandardCharsets.UTF_8));
		BigInteger noHash = new BigInteger(1, hashBytes);
		String hashStr = noHash.toString(16);
	
		
		return hashStr;
		
	}

}
