package com.etl.kyc.etlkycsimregisterapi.testcode;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.etl.kyc.etlkycsimregisterapi.db.AuthenETLConfigUser;
import com.etl.kyc.etlkycsimregisterapi.security.sha256encrypt;

public class TestAuthen {

	public TestAuthen() {
		// TODO Auto-generated constructor stub
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		try {
//
//			sha256encrypt kSha256encrypt = new sha256encrypt();
//			String passwordEncr;
//			String pssword = "123";
//			String userName = "admin";
//
//			passwordEncr = kSha256encrypt.getSha256encrypt(pssword);
//
//			ExecutorService service = Executors.newSingleThreadExecutor();
//
//			AuthenETLConfigUser bprocessingCallAble = new AuthenETLConfigUser(userName.trim(), passwordEncr.trim());
//			Future<String> future = service.submit(bprocessingCallAble);
//			String resultAuthen = null;
//			
//			try {
//				resultAuthen = future.get();
//				// System.out.println("Result 3 : " + resultAuthen);
//			} catch (Exception e) {
//				System.out.println("Error 3: " + e.getMessage());
//			}
//				System.out.println("resultAuthen: " + resultAuthen);
//			
//
//		} catch (NoSuchAlgorithmException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//	}

}
