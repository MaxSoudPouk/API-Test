package com.etl.kyc.etlkycsimregisterapi.smsotp;

import java.text.DecimalFormat;
import java.util.Random;

public class generateOTP {
	public static  String genOTP = generateOTP();
	
	 private static String generateOTP() {
	        return new DecimalFormat("000000")
	                .format(new Random().nextInt(999999));
	    }
}
