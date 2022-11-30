package com.etl.kyc.etlkycsimregisterapi.security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class sha256encrypt {

public String getSha256encrypt(String sha256) throws NoSuchAlgorithmException  {
	
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte hashBytes[] = messageDigest.digest(sha256.getBytes(StandardCharsets.UTF_8));
		BigInteger noHash = new BigInteger(1, hashBytes);
		String hashStr = noHash.toString(16);
		return hashStr;
		
	}
}
