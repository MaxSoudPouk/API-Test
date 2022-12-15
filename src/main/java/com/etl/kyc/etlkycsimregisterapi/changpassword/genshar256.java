package com.etl.kyc.etlkycsimregisterapi.changpassword;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class genshar256 {

	public static String getSHA256Hash256(String data) {
		String result = null;

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes("UTF-8"));

			return bytesToHex(hash); // make it printable
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private static String bytesToHex(byte[] hash) {
		return DatatypeConverter.printHexBinary(hash);
	}

}
