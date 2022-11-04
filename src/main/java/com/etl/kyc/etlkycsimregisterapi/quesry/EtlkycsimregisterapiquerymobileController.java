package com.etl.kyc.etlkycsimregisterapi.quesry;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etl.kyc.etlkycsimregisterapi.SIM_status;
import com.etl.kyc.etlkycsimregisterapi.db.AuthenETLConfigUser;
import com.etl.kyc.etlkycsimregisterapi.db.Config;
import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
import com.etl.kyc.etlkycsimregisterapi.model.LoginModel;
import com.etl.kyc.etlkycsimregisterapi.security.GenerateSignkey_sha256;
import com.etl.kyc.etlkycsimregisterapi.security.JWT_Security_Encode_Decode_Java;
import com.etl.kyc.etlkycsimregisterapi.security.sha256encrypt;

@RestController
public class EtlkycsimregisterapiquerymobileController extends Thread {
	public EtlkycsimregisterapiquerymobileController() {
		
		System.out.println("QuerymobileStart");
		
	}

	private static HttpServletRequest request;

	private void setRequest(HttpServletRequest request) {
		EtlkycsimregisterapiquerymobileController.request = request;
		
		
	}

	@PostMapping("/v1/querymobilestatus")
	public QueryMobileStatusModel querystatus(
			@RequestParam(defaultValue = "") String sign,
			@RequestParam(defaultValue = "") String userName,
			@RequestParam(defaultValue = "") String mobileNumber,
		    @RequestParam(defaultValue = "") String extraParams, 
		    @RequestParam(defaultValue = "") String channel, 
		    @RequestParam(defaultValue = "") String transactionNo,
			@RequestParam(defaultValue = "") String remark,
		   // @RequestParam(defaultValue = "") String callback,
		    @RequestParam(defaultValue = "") String uuid,
		   @RequestParam(defaultValue = "") String latitude,
			@RequestParam(defaultValue = "") String longtitude,
		    @RequestParam(defaultValue = "") String mobileInfo,
			@RequestParam(defaultValue = "") String token,
			//@RequestParam(defaultValue = "") String datetime,
			@RequestParam(defaultValue = "") String userid)

	
	{

	//	System.out.println("##### /v1/querymobilestatus");
	//	System.out.println("##### /v1/sign=" + sign);
		
	
		

		QueryMobileStatusModel querymodel = new QueryMobileStatusModel();
		
		
		
	
		if (channel.equals("") || transactionNo.equals("")|| token.equals("")|| userid.equals("")
			|| sign.equals("") || longtitude.equals("") || latitude.equals("") || mobileInfo.equals("")) {

			System.out.println("token=" + token);
			
			querymodel.setResultCode(GlobalParameter.error_not_acceptable);
			querymodel.setResultMsg(GlobalParameter.error_not_acceptable_msg);
			//querymodel.setMobileStatus(" ");
			querymodel.setResultCode(" ");
			///querymodel.setMobileStatusDes("Some Parameter is null");
			querymodel.setTransactionID(" ");
			
			
			return querymodel;

		}

		if (sign.length() != 64) {

			querymodel.setResultCode(GlobalParameter.error_not_acceptable);
			querymodel.setResultMsg(GlobalParameter.error_not_acceptable_msg);
		   // querymodel.setMobileStatus(" ");
			querymodel.setResultCode(" ");
			querymodel.setTransactionID(" ");
		    querymodel.setExtraPara(" ");
		  
	
			
			return querymodel;

		}

		GenerateSignkey_sha256 signkey_sha256 = new GenerateSignkey_sha256();
		String des_url = "/etllao.com/v1/querymobilestatus";
	//String serverSign = signkey_sha256.generateSignkey_sha256(userName, channel, transactionNo, mobileNumber, remark, extraParams, uuid, des_url);
	
	String serverSign = signkey_sha256.generateSignkey_sha256(userName, channel, transactionNo, mobileNumber, remark, extraParams, uuid, des_url);
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
				
			
				
				boolean jwtencoderesult=false;

				// createJWTSec(String id, long ttlMillis, String userName, String userID)

		JWT_Security_Encode_Decode_Java encode_Decode_Java = new JWT_Security_Encode_Decode_Java();
		jwtencoderesult = encode_Decode_Java.deCodeJWT_validate(token, userid, userName);
		
		
		System.out.println("##### /v1/jwtencoderesult=" + jwtencoderesult);
		
		if(jwtencoderesult){
			
			Querymobilestatus querysim = new Querymobilestatus();
				//	querysim.Querymobilestatus(mobileNumber);
					//==========================
					java.sql.PreparedStatement pstmt;
					ResultSet rs;
					DatabaseConnectionPool dbConnectionPool = null;
					
					Connection connection1 = null;
					String strRetunr = null;
					Statement statementtAuth = null;
					ResultSet resultSettAuth = null;
					Connection conntAuth = null;
								String sql="SELECT t.status_id,k.status_name from tb_subscriber_info t  INNER JOIN tb_status_info k \r\n"
						+ "on t.status_id=k.status_id WHERE t.msisdn='"+ mobileNumber +"' LIMIT 1";

//					final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					Calendar c = Calendar.getInstance();

							//	System.out.println("sql-======:: " + sql);
							
							
								//querymodel simstatus= new querymodel();
					
					try {
						

						dbConnectionPool = new DatabaseConnectionPool(Config.driverServr, Config.dburlServr, Config.dbUserNameServr,
								Config.dbPasswordServr);
						connection1 = dbConnectionPool.getConnection();
						pstmt = connection1.prepareStatement(sql);
						rs = (ResultSet) pstmt.executeQuery();

						while (rs != null && rs.next()) {
							String status_id = rs.getString("status_id");
							String status_name = rs.getString("status_name");
							
							//System.out.println("status_id-======:: " + status_id);
							
							//System.out.println("status_name-======:: " + status_name);
							
							querymodel.setResultCode(GlobalParameter.error_ok_success);
							querymodel.setResultMsg(GlobalParameter.error_ok_success_msg);
							querymodel.setMobileStatus(status_id);
							querymodel.setMobileStatusDes(status_name);
							querymodel.setTransactionID(transactionNo);
							querymodel.setExtraPara("n");
							
							return querymodel;
							
						}

					} catch (Exception e) {
						querymodel.setMobileStatus(" ");
						querymodel.setMobileStatusDes("Error Mobile Status");
						return querymodel;
					
					} finally {

						try {

							dbConnectionPool.freeConnection(connection1);
							// release resources
							// dbConnectionPool.destroy();

							if (conntAuth != null) {
								conntAuth.close();
							}

							if (statementtAuth != null) {
								statementtAuth.close();
							}

							if (resultSettAuth != null) {
								resultSettAuth.close();
							}
						} catch (Exception e3) {

						}
					}
					//===============================
						
//						querymodel getsimstatus = new querymodel();
//						
//						String mstatus=getsimstatus.getMobileStatus();
//						String dstatus=getsimstatus.getMobileStatusDes();
//						
//						System.out.println("getMobileStatus=====" + mstatus);
//					    System.out.println("getMobileStatusDes=====" + dstatus);
						
//				querymodel.setResultCode(GlobalParameter.error_ok_success);
//				querymodel.setResultMsg(GlobalParameter.error_ok_success_msg);
//				//querymodel.setMobileStatus(mstatus);
//				//querymodel.setMobileStatusDes(dstatus);
//				querymodel.setTransactionID(transactionNo);
//				querymodel.setExtraPara("n");
				
				
				
				//System.out.println("Mobilestatusdes=" + querymodel);
				//System.out.println("mobilestatus=" +querymodel.getMobileStatus());
				
				return querymodel;
		
				}else {
					
					querymodel.setResultCode(GlobalParameter.error_invalidtoken);
					querymodel.setResultMsg(GlobalParameter.error_invalidtoken_msg);
					querymodel.setMobileStatus(" ");
					querymodel.setMobileStatusDes(" ");
					querymodel.setExtraPara("n");
					//System.out.println("Mobilestatusdes=" + querymodel.getMobileStatusDes());
					//System.out.println("mobilestatus=" +querymodel.getMobileStatus());
					return querymodel;
				}

			

			} else {

				querymodel.setResultCode(GlobalParameter.error_non_authoritative_sign);
				querymodel.setResultMsg(GlobalParameter.error_non_authoritative_msg_sign);
				querymodel.setMobileStatus("");
				querymodel.setMobileStatusDes("");
				querymodel.setExtraPara("n");
				
			
				return querymodel;

			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			querymodel.setResultCode(GlobalParameter.error_unavailable);
			querymodel.setResultMsg(GlobalParameter.error_unavailable_msg);
			querymodel.setMobileStatus("");
			querymodel.setMobileStatusDes("");
			querymodel.setExtraPara("n");
//			querymodel.setToken("n");
//			querymodel.setUserID("n");
//			querymodel.setFirstName("n");
//			querymodel.setUserName(userName);
			return querymodel;
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
