package com.etl.kyc.etlkycsimregisterapi.customerstatus;

import com.etl.kyc.etlkycsimregisterapi.db.Config;
import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
import com.etl.kyc.etlkycsimregisterapi.security.GenerateSignkey_sha256;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
@RestController
public class checkmobilestatusforcustomer extends Thread {
	
      public checkmobilestatusforcustomer() {
		
	//	System.out.println("checkstatusforcustomer");
		
	}
	
	
	public static HttpServletRequest request;

	private void setRequest(HttpServletRequest request) {
		checkmobilestatusforcustomer.request = request;
	
	}

	@PostMapping("/v1/checkmobilestatusforcustomer")
	public QueryMobileStatusforcustmerModel querystatus(
			@RequestParam(defaultValue = "") String sign,
			@RequestParam(defaultValue = "") String userName,
			@RequestParam(defaultValue = "") String mobileNumber,
		    @RequestParam(defaultValue = "") String extraParams, 
		    @RequestParam(defaultValue = "") String channel, 
		    @RequestParam(defaultValue = "") String transactionNo,
			@RequestParam(defaultValue = "") String remark,
		    @RequestParam(defaultValue = "") String uuid,
		    @RequestParam(defaultValue = "") String latitude,
			@RequestParam(defaultValue = "") String longtitude,
		    @RequestParam(defaultValue = "") String mobileInfo)
			
	{
		
		QueryMobileStatusforcustmerModel querymodel = new QueryMobileStatusforcustmerModel();
		

		if (channel.equals("") || transactionNo.equals("")
			|| sign.equals("") || longtitude.equals("") || latitude.equals("") || mobileInfo.equals("")|| mobileNumber.equals("")) {

			//System.out.println("token=" + token);
			
			querymodel.setResultCode(GlobalParameter.error_no_content);
			querymodel.setResultMsg(GlobalParameter.error_no_content_msg);
			//querymodel.setMobileStatus(" ");
			querymodel.setResultCode(" ");
			///querymodel.setMobileStatusDes("Some Parameter is null");
			querymodel.setTransactionID(" ");
			
			
			return querymodel;

		}
		
		if (sign.length() != 64) {

			querymodel.setResultCode(GlobalParameter.error_non_authoritative_sign_wrong_lengt);
			querymodel.setResultMsg(GlobalParameter.error_non_authoritative_sign_wrong_lengt_msg);
		   // querymodel.setMobileStatus(" ");
			
			
			return querymodel;

		}
		
		GenerateSignkey_sha256 signkey_sha256 = new GenerateSignkey_sha256();
		String des_url = "/etllao.com/v1/checkmobilestatusforcustomer";
	//String serverSign = signkey_sha256.generateSignkey_sha256(userName, channel, transactionNo, mobileNumber, remark, extraParams, uuid, des_url);
	
	String serverSign = signkey_sha256.generateSignkey_sha256(userName, channel, transactionNo, mobileNumber,remark, extraParams, uuid, des_url);
		
	System.out.println("serverSign=" + serverSign);
		
		
	try {
		if (serverSign.equals(sign.trim())) {
			
				java.sql.PreparedStatement pstmt;
				ResultSet rs;
				DatabaseConnectionPool dbConnectionPool = null;
				Connection connection1 = null;
				String strRetunr = null;
				Statement statementtAuth = null;
				ResultSet resultSettAuth = null;
				Connection conntAuth = null;
							String sql="SELECT t.status_id,k.status_name from tb_subscriber_info t  INNER JOIN tb_status_info k \r\n"
					+ "on t.status_id=k.status_id WHERE t.status_id not in('4') and t.msisdn='"+ mobileNumber +"' order by t.reg_date desc LIMIT 1";

				try {
					
					dbConnectionPool = new DatabaseConnectionPool(Config.driverServr, Config.dburlServr, Config.dbUserNameServr,
							Config.dbPasswordServr);
					connection1 = dbConnectionPool.getConnection();
					pstmt = connection1.prepareStatement(sql);
					rs = (ResultSet) pstmt.executeQuery();
					int rsfound=0;
					
					String status_id=null;
					String status_name=null;
					while (rs != null && rs.next()) {
						rsfound++;
						 status_id = rs.getString("status_id");
						 status_name = rs.getString("status_name");
						
						//System.out.println("status_id-======:: " + status_id);
						
						//System.out.println("status_name-======:: " + status_name);
			
					} 
						
					if(rsfound>0) {
						
						querymodel.setResultCode(GlobalParameter.error_ok_success);
						querymodel.setResultMsg(GlobalParameter.error_ok_success_msg);
						querymodel.setMobileStatus(status_id);
						querymodel.setMobileStatusDes(status_name);
						querymodel.setTransactionID(transactionNo);
						querymodel.setExtraPara("n");
						
						return querymodel;
						
					}else {
						querymodel.setResultCode(GlobalParameter.error_nodata);
						querymodel.setResultMsg(GlobalParameter.error_nodata_msg);
						querymodel.setMobileStatus("0");
						querymodel.setTransactionID(transactionNo);
						return querymodel;
						
					}

				} catch (Exception e) {
					querymodel.setMobileStatus(GlobalParameter.error_querymobile_status);
					querymodel.setMobileStatusDes(GlobalParameter.error_querymobile_status_msg);
					querymodel.setTransactionID(transactionNo);
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
			
		} else {

			querymodel.setResultCode(GlobalParameter.error_non_authoritative_sign);
			querymodel.setResultMsg(GlobalParameter.error_non_authoritative_msg_sign);
			querymodel.setTransactionID(transactionNo);
		
			return querymodel;

		}

	} catch (Exception e1) {
		// TODO Auto-generated catch block
		querymodel.setResultCode(GlobalParameter.error_unavailable);
		querymodel.setResultMsg(GlobalParameter.error_unavailable_msg);
		querymodel.setTransactionID(transactionNo);
		return querymodel;
	}


}

//----------------------------------------------------------------------
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


