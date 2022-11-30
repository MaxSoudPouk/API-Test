package com.etl.kyc.etlkycsimregisterapi.changpassword;

import com.etl.kyc.etlkycsimregisterapi.db.Config;
import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
import com.etl.kyc.etlkycsimregisterapi.security.GenerateSignkey_sha256;
import com.etl.kyc.etlkycsimregisterapi.security.JWT_Security_Encode_Decode_Java;
import com.etl.kyc.etlkycsimregisterapi.security.sha256encrypt;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

@RestController
public class EtlkycChangPasswordController extends Thread {
    public static HttpServletRequest request;
    DatabaseConnectionPool dbConnectionPool;

    public EtlkycChangPasswordController() {

//        System.out.println("ChangePassword");

    }

    // ----------------------------------------------------------------------
    private static boolean isNullOrEmpty(String str) {
        // return true if empty
        return str == null || str.trim().isEmpty();
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

    private void setRequest(HttpServletRequest request) {
        EtlkycChangPasswordController.request = request;

    }

    @PostMapping("/v1/changepassword")
    public ChangePasswordModel changepassword(
            @RequestParam(defaultValue = "") String sign,
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "") String newpassowrd,
            @RequestParam(defaultValue = "") String oldpassword,
            @RequestParam(defaultValue = "") String extraParams,
            @RequestParam(defaultValue = "") String channel,
            @RequestParam(defaultValue = "") String transactionNo,
            @RequestParam(defaultValue = "") String msisdn,
            @RequestParam(defaultValue = "") String uuid,
            @RequestParam(defaultValue = "") String latitude,
            @RequestParam(defaultValue = "") String longtitude,
            @RequestParam(defaultValue = "") String token,
            @RequestParam(defaultValue = "") String remark,
            @RequestParam(defaultValue = "") String userid
           // @RequestParam(defaultValue = "") String datetime
           // @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}", required = true) LocalDate datetime
           // @RequestParam(value = "datetime", required = true ) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  Date datetime
           // @RequestParam(name = "d", defaultValue = "#{T(java.time.LocalDate).now()}", required = true) LocalDate d)
    ) {


        ChangePasswordModel passwordModel = new ChangePasswordModel();
        
        Calendar currentDate111 = Calendar.getInstance();
		SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter111.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
		String dateNow111 = formatter111.format(currentDate111.getTime());
		String datepro111 = dateNow111.toString();
		
		//=================================
		//LocalDateTime 
		// Calendar currentDate111 = Calendar.getInstance();
		//datetime dtinput = new datetime();
//			SimpleDateFormat inputformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			inputformat.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
//			String inputdate = inputformat.format(datetime);
//			String datefff1 = inputdate.toString();
//           
//			DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/uuuu" ) ;
//			f.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
//			LocalDateTime ldt = LocalDateTime.parse( input , f ) ;
		
		// DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss");
		// f.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
		// LocalDateTime dtinput = LocalDateTime.parse( datetime , f ) ;
		
        
      //  System.out.println("datepro111=====" + datepro111);
        
     // System.out.println("datefff1=====" + datefff1);
        
        
//		if(getinput_date!=datepro111) {
//			  passwordModel.setResultCode(GlobalParameter.error_datetime_format);
//	          passwordModel.setResultMsg(GlobalParameter.error_datetime_format_smg);
//	            return passwordModel;
//			
//		}


        if (channel.equals("") || transactionNo.equals("") || token.equals("") || userid.equals("")
                || sign.equals("") || longtitude.equals("") || latitude.equals("")
                || newpassowrd.equals("") || oldpassword.equals("")) {

           // System.out.println("token=" + token);

            passwordModel.setResultCode(GlobalParameter.error_not_acceptable);
            passwordModel.setResultMsg(GlobalParameter.error_not_acceptable_msg);
            passwordModel.setTransactionID(transactionNo);
            return passwordModel;

        }

        if (sign.length() != 64) {

           
            passwordModel.setResultCode(GlobalParameter.error_non_authoritative_sign_wrong_lengt);
            passwordModel.setResultMsg(GlobalParameter.error_non_authoritative_sign_wrong_lengt_msg);
            passwordModel.setTransactionID(transactionNo);
            return passwordModel;

        }

        GenerateSignkey_sha256 signkey_sha256 = new GenerateSignkey_sha256();
        String des_url = "/etllao.com/v1/changePassword";

        String serverSign = signkey_sha256.generateSignkey_sha256(
                userName,
                channel,
                transactionNo,
                msisdn,
                remark,
                extraParams,
                uuid,
                des_url);

       System.out.println("serverSign=" + serverSign);


        try {
            if (serverSign.equals(sign.trim())) {

                sha256encrypt kSha256encrypt = new sha256encrypt();
                String passwordEncr;
                String passwordEncrOldPassword;
                passwordEncr = kSha256encrypt.getSha256encrypt(userName+newpassowrd);
                passwordEncrOldPassword = kSha256encrypt.getSha256encrypt(userName+oldpassword);
                
               // System.out.println("passwordEncrOldPassword=======" +passwordEncrOldPassword);
               // System.out.println("userid=======" +userid);
               // System.out.println("userName=======" +userName);
                boolean jwtencoderesult = false;

                JWT_Security_Encode_Decode_Java encode_Decode_Java = new JWT_Security_Encode_Decode_Java();
                jwtencoderesult = encode_Decode_Java.deCodeJWT_validate(token, userid, userName);


            //    System.out.println(userid);
             //   System.out.println("##### /v1/jwtencoderesult=" + jwtencoderesult);
//============================================================================================
                if (jwtencoderesult) {

                	PreparedStatement pstmt;
					ResultSet rs;
					DatabaseConnectionPool dbConnectionPool = null;
					Connection connection1 = null;
					String strRetunr = null;
					Statement statementtAuth = null;
					ResultSet resultSettAuth = null;
					Connection conntAuth = null;
					String user_password= null;
//                    String sqlpassword = "SELECT user_password from tb_user_info where user_id = '"
//                            + userid + "'  LIMIT 1";
//
//                    try {
//                      
//                        dbConnectionPool = new DatabaseConnectionPool(Config.driverServr, Config.dburlServr, Config.dbUserNameServr,
//								Config.dbPasswordServr);
//						connection1 = dbConnectionPool.getConnection();
//						pstmt = connection1.prepareStatement(sqlpassword);
//						rs = (ResultSet) pstmt.executeQuery();
//
//                        if (rs != null && rs.next()) {
//                            user_password = rs.getString("user_password");
//                        }
                        
					
					 if (oldpassword.equals(newpassowrd)) {
                           passwordModel.setResultCode(GlobalParameter.error_oldpassword);
                           passwordModel.setResultMsg(GlobalParameter.error_oldpassword_msg);
                           passwordModel.setTransactionID(transactionNo);
                            return passwordModel;
                            
					 }



					try {

						  dbConnectionPool = new DatabaseConnectionPool(
                                  Config.driverServr,
                                  Config.dburlServr,
                                  Config.dbUserNameServr,
                                  Config.dbPasswordServr);
							connection1 = dbConnectionPool.getConnection();

                                          // System.out.println("Connected");
                                            String sql = "UPDATE tb_user_info SET user_password =? WHERE user_id =? ";
                                            System.out.println("sqlupdate===="+sql);
                                            PreparedStatement statement = connection1.prepareStatement(sql);
                                            System.out.println("statement===="+statement);
                                            statement.setString(1, passwordEncr);
                                            statement.setString(2, userid);
                                           // statement.setString(3, oldpassword);

                                            int rowsUpdated = statement.executeUpdate();
                                            if (rowsUpdated > 0) {

                                              //  System.out.println("An existing user was updated successfully!");
                                               // System.out.println(newpassowrd);

                                                passwordModel.setResultCode(GlobalParameter.error_ok_success);
                                                passwordModel.setResultMsg(GlobalParameter.error_ok_success_msg);
                                                passwordModel.setTransactionID(transactionNo);
                                                passwordModel.setExtraPara(extraParams);

                                                return passwordModel;
                                            }else {

                                            	 passwordModel.setResultCode(GlobalParameter.fail_change_password);
                                                 passwordModel.setResultMsg(GlobalParameter.fail_change_password_msg);
                                                 passwordModel.setTransactionID(transactionNo);

                                                 return passwordModel;
                                            }


                    } catch (Exception e) {
                    	passwordModel.setResultCode(GlobalParameter.error_query_password);
                    	passwordModel.setResultMsg(GlobalParameter.error_query_password_msg);
                    	 passwordModel.setTransactionID(transactionNo);
                    	return passwordModel;

                    }
//=======================================================================================================
                } else {

                    passwordModel.setResultCode(GlobalParameter.error_invalidtoken);
                    passwordModel.setResultMsg(GlobalParameter.error_invalidtoken_msg);
                    passwordModel.setTransactionID(transactionNo);

                    return passwordModel;

                }

            } else {

                passwordModel.setResultCode(GlobalParameter.error_non_authoritative_sign);
                passwordModel.setResultMsg(GlobalParameter.error_non_authoritative_msg_sign);
                passwordModel.setTransactionID(transactionNo);

                return passwordModel;

            }
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            passwordModel.setResultCode(GlobalParameter.error_unavailable);
            passwordModel.setResultMsg(GlobalParameter.error_unavailable_msg);
            passwordModel.setTransactionID(transactionNo);

            return passwordModel;
        }
       
    }
}
