package com.etl.kyc.etlkycsimregisterapi.smsotp;

import com.etl.kyc.etlkycsimregisterapi.changpassword.EtlkycChangPasswordController;
import com.etl.kyc.etlkycsimregisterapi.db.Config;
import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
import com.etl.kyc.etlkycsimregisterapi.security.GenerateSignkey_sha256;
import com.etl.kyc.etlkycsimregisterapi.security.JWT_Security_Encode_Decode_Java;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

@RestController
public class OtpResponController extends Thread {


    public static HttpServletRequest request;
    DatabaseConnectionPool dbConnectionPool;


    public OtpResponController() {
    }

    // ----------------------------------------------------------------------


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

    @PostMapping("/v1/checkOTP")
    public otpresponModel otp(
            @RequestParam(defaultValue = "") String sign,
            @RequestParam(defaultValue = "") String confirmOTP,
            @RequestParam(defaultValue = "") String msisdn,
            @RequestParam(defaultValue = "") String channel,
            @RequestParam(defaultValue = "") String transactionNo,
            @RequestParam(defaultValue = "") String remark,
            @RequestParam(defaultValue = "") String extraParams,
            @RequestParam(defaultValue = "") String latitude,
            @RequestParam(defaultValue = "") String longtitude,
            @RequestParam(defaultValue = "") String callback,
            @RequestParam(defaultValue = "") String mobileInfo,
            @RequestParam(defaultValue = "") String uuid,
            @RequestParam(defaultValue = "") String userid,
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "") String token

    ) throws SQLException {


        otpresponModel model = new otpresponModel();

        if (
                sign.equals("")
                        || confirmOTP.equals("")
                        || msisdn.equals("")
                        || channel.equals("")
                        || transactionNo.equals("")
                        || latitude.equals("")
                        || longtitude.equals("")
                        || mobileInfo.equals("")
                        || uuid.equals("")


        ) {

            model.setResultCode(GlobalParameter.error_not_acceptable);
            model.setResultMsg(GlobalParameter.error_not_acceptable_msg);

            return model;

        }

        GenerateSignkey_sha256 signkey_sha256 = new GenerateSignkey_sha256();
        String des_url = "/etllao.com/v1/Otpsms";
        String serverSign = signkey_sha256.generateSignkey_sha256(
                userName,
                channel,
                transactionNo,
                msisdn,
                remark,
                extraParams,
                uuid,
                des_url);

        System.out.println("OTPserverSign=" + serverSign);


        if (sign.length() != 64) {

            model.setResultCode(GlobalParameter.error_not_acceptable);
            model.setResultMsg(GlobalParameter.error_not_acceptable_msg);

            return model;

        }


        //=================================
        if (serverSign.equals(sign.trim())) {


            boolean jwtencoderesult = false;

            JWT_Security_Encode_Decode_Java encode_Decode_Java = new JWT_Security_Encode_Decode_Java();
            jwtencoderesult = encode_Decode_Java.deCodeJWT_validate1(token, userid, msisdn);




            if (jwtencoderesult) {

                Calendar currentDate = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                formatter.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
                String dateNow = formatter.format(currentDate.getTime());
                String datepro = dateNow.toString();


                PreparedStatement pstmt;
                ResultSet rs;
                DatabaseConnectionPool dbConnectionPool = null;
                Connection connection = null;
                Statement statementtAuth = null;
                ResultSet resultSettAuth = null;
                Connection conntAuth = null;
                String sqlpassword = "SELECT otp, expiredate from tb_otp_info where msisdn = '"
                        + msisdn + "' and otp = '" + confirmOTP + "'  LIMIT 1";


                try {

                    dbConnectionPool = new DatabaseConnectionPool(
                            Config.driverServr,
                            Config.dburlServr,
                            Config.dbUserNameServr,
                            Config.dbPasswordServr);
                    connection = dbConnectionPool.getConnection();
                    pstmt = connection.prepareStatement(sqlpassword);
                    rs = (ResultSet) pstmt.executeQuery();
                    int rsfound = 0;

                    String otpResponDB = null;
                    String expiredateDB = null;

                    if (rs != null && rs.next()) {


                        otpResponDB = rs.getString("otp");
                        expiredateDB = rs.getString("expiredate");


                        long dateN = Long.parseLong(datepro);
                        long expiredate = Long.parseLong(expiredateDB);

                        if (confirmOTP.equals(otpResponDB)) {
                            if (dateN <= expiredate) {

                                model.setResultCode(GlobalParameter.error_ok_success);
                                model.setResultMsg(GlobalParameter.error_ok_success_msg);
                                model.setTransactionNo(transactionNo);
                                model.setExtraPara(extraParams);

                                return model;
                            } else {
                                model.setResultCode(GlobalParameter.error_expired_OTP);
                                model.setResultMsg(GlobalParameter.error_expired_OTP_msg);

                                return model;
                            }

                        } else {
                            model.setResultCode(GlobalParameter.fail_OTP_Notconfirm);
                            model.setResultMsg(GlobalParameter.fail_OTP_Notconfirm_msg);

                            return model;
                        }
                    }
                    model.setResultCode(GlobalParameter.fail_OTP_Notconfirm);
                    model.setResultMsg(GlobalParameter.fail_OTP_Notconfirm_msg);

                    return model;

                } catch (SQLException e) {
                    model.setResultCode(GlobalParameter.error_not_acceptable);
                    model.setResultMsg(GlobalParameter.error_not_acceptable_msg);

                    return model;
                }


            } else {

                model.setResultCode(GlobalParameter.error_invalidtoken);
                model.setResultMsg(GlobalParameter.error_invalidtoken_msg);
                model.setExtraPara("n");

                return model;

            }
        } else {

            model.setResultCode(GlobalParameter.error_non_authoritative_sign);
            model.setResultMsg(GlobalParameter.error_non_authoritative_msg_sign);
            return model;

        }
    }

}
