//package com.etl.kyc.etlkycsimregisterapi.testcode;
//
//import com.etl.kyc.etlkycsimregisterapi.security.GenerateSignkey_sha256;
//
//public class GenSign {
//
//	public GenSign() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public static void main(String[] args) {
//		
//		
//		GenerateSignkey_sha256 signkey_sha256 = new GenerateSignkey_sha256();
//		String des_url = "/etllao.com/v1/loginAccount";
//		String remark ="test";
//		String extraParams = "";
//		String uuid = "11223344";
//
//		String userName = "admin";
//		String channel ="0";
//		String transactionNo ="1234567890";
//		String mobileNumber = "5161";
//		String serverSign = signkey_sha256.generateSignkey_sha256(userName, channel, transactionNo, mobileNumber,
//				remark, extraParams, uuid, des_url);
//		System.out.println("serverSign=" + serverSign);
//		
//		
//
//	}
//
//}
