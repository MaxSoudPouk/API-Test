package com.etl.kyc.etlkycsimregisterapi.login;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etl.kyc.etlkycsimregisterapi.db.AuthenETLConfigUser;
import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
import com.etl.kyc.etlkycsimregisterapi.model.LoginModel;
import com.etl.kyc.etlkycsimregisterapi.security.GenerateSignkey_sha256;
import com.etl.kyc.etlkycsimregisterapi.security.JWT_Security_Encode_Decode_Java;
import com.etl.kyc.etlkycsimregisterapi.security.sha256encrypt;

@RestController
public class EtlkycsimregisterapiLoginController extends Thread {

	public EtlkycsimregisterapiLoginController() {
//		@Bean
//		public CommandLineRunner CommandLineRunnerBean() {
//			return (args) -> {
		//System.out.println("Querymobile afterregister Start");
//				threadRead threadRead = new threadRead();
//				threadRead.contextInitialized(null);
//			};
//		}
	}

	private static HttpServletRequest request;

	private void setRequest(HttpServletRequest request) {
		EtlkycsimregisterapiLoginController.request = request;
	}

	@RequestMapping("/")
	
	public TestModel test() {
		TestModel t = new TestModel();
		Calendar currentDate111 = Calendar.getInstance();
		SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter111.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
		String dateNow111 = formatter111.format(currentDate111.getTime());
		String datepro111 = dateNow111.toString();
		t.setResultCode("405000000");
		t.setResultMsg("Response msg on item" + datepro111);
		return t;

	}

	@PostMapping("/v1/loginAccount")
	public LoginModel login(@RequestParam(defaultValue = "") String sign,
			@RequestParam(defaultValue = "") String userName, @RequestParam(defaultValue = "") String pssword,
			@RequestParam(defaultValue = "") String channel, @RequestParam(defaultValue = "") String transactionNo,
			@RequestParam(defaultValue = "") String remark, @RequestParam(defaultValue = "") String callback,
			@RequestParam(defaultValue = "") String extraParams, @RequestParam(defaultValue = "") String mobileNumber,
			@RequestParam(defaultValue = "") String uuid, @RequestParam(defaultValue = "") String latitude,
			@RequestParam(defaultValue = "") String longtitude,@RequestParam(defaultValue = "") String mobileInfo) {

		//System.out.println("##### /v1/loginAccount");
		//System.out.println("##### /v1/sign=" + sign);

		LoginModel model = new LoginModel();

		if (userName.equals("") || pssword.equals("") || channel.equals("") || transactionNo.equals("")
				|| uuid.equals("") || sign.equals("") || longtitude.equals("") || latitude.equals("") || mobileInfo.equals("")) {

			model.setResultCode(GlobalParameter.error_not_acceptable);
			model.setResultMsg(GlobalParameter.error_not_acceptable_msg);
			model.setExtraPara("n");
			model.setToken("n");
			model.setFirstName("n");
			model.setUserID("n");
			model.setUserName(userName);
			return model;

		}

		if (sign.length() != 64) {

			model.setResultCode(GlobalParameter.error_not_acceptable);
			model.setResultMsg(GlobalParameter.error_not_acceptable_msg);
	
			return model;

		}

		GenerateSignkey_sha256 signkey_sha256 = new GenerateSignkey_sha256();
		String des_url = "/etllao.com/v1/loginAccount";
		String serverSign = signkey_sha256.generateSignkey_sha256(userName, channel, transactionNo,
				mobileNumber,	remark, extraParams, uuid, des_url);

		System.out.println("serverSign=" + serverSign);

		Calendar currentDate111 = Calendar.getInstance();
		SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter111.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
		String dateNow111 = formatter111.format(currentDate111.getTime());
		String datepro111 = dateNow111.toString();

		Calendar currentDate = Calendar.getInstance();
		String ipAdressReq_ = getClientIp();
		String resultCode = "";
		String resultMsg = "";
		try {
			if (serverSign.equals(sign.trim())) {

				sha256encrypt kSha256encrypt = new sha256encrypt();
				String passwordEncr;
				passwordEncr = kSha256encrypt.getSha256encrypt(userName+pssword);

				ExecutorService service = Executors.newSingleThreadExecutor();
				AuthenETLConfigUser bprocessingCallAble = new AuthenETLConfigUser(userName.trim(), passwordEncr.trim());
				Future<String> future = service.submit(bprocessingCallAble);
				
				String resultAuthen = null;
				try {
					resultAuthen = future.get();
					// System.out.println("Result 3 : " + resultAuthen);
				} catch (Exception e) {
					System.out.println("Error 3: " + e.getMessage());
				}

				if (!isNullOrEmpty(resultAuthen)) {

					String[] parts = resultAuthen.split("\\|");

					String userID = "";
					String userRealName = "";

					try {
						userID = parts[0].trim();
					} catch (Exception e) {

					}

					try {
						userRealName = parts[1].trim();
					} catch (Exception e) {

					}

					// Gen JWT
					String jwtTokenStrng = "";

					// createJWTSec(String id, long ttlMillis, String userName, String userID)

					JWT_Security_Encode_Decode_Java encode_Decode_Java = new JWT_Security_Encode_Decode_Java();
					long ttlMillis = 604800000; // 604800000 = 1 week, 3 h = 10800000 ms
					jwtTokenStrng = encode_Decode_Java.createJWTSec(transactionNo, ttlMillis, userName, userID);

					model.setResultCode(GlobalParameter.error_ok_success);
					model.setResultMsg(GlobalParameter.error_ok_success_msg);
					model.setExtraPara("n");
					model.setUserName(userName);
					model.setToken(jwtTokenStrng);
					model.setUserID(userID);
					model.setFirstName(userRealName);
					return model;

				} else {
					model.setResultCode(GlobalParameter.fail_login_notsuccess);
					model.setResultMsg(GlobalParameter.fail_login_notsuccess_msg);
					model.setUserName(userName);
					return model;

				}

			} else {

				model.setResultCode(GlobalParameter.error_non_authoritative_sign);
				model.setResultMsg(GlobalParameter.error_non_authoritative_msg_sign);
				return model;

			}

		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			model.setResultCode(GlobalParameter.error_login);
			model.setResultMsg(GlobalParameter.error_login_msg);
			
			return model;
		}

	}

	
	
// ----------------------------------------------------------------------
	private static boolean isNullOrEmpty(String str) {
		// return true if empty
		if (str != null && !str.trim().isEmpty())
			return false;
		return true;
	}

	private static String getClientIp() {
		String remoteAddr = "";
		try {
			if (request != null) {
				try {
					remoteAddr = request.getHeader("X-FORWARDED-FOR");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (remoteAddr == null || "".equals(remoteAddr)) {
					remoteAddr = request.getRemoteAddr();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return remoteAddr;
	}
}
