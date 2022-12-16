package com.etl.kyc.etlkycsimregisterapi.smsotp;


import com.etl.kyc.etlkycsimregisterapi.db.Config;
import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
import com.etl.kyc.etlkycsimregisterapi.security.GenerateSignkey_sha256;
import com.etl.kyc.etlkycsimregisterapi.security.JWT_Security_Encode_Decode_Java;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

@RestController
public class OtpRequestController extends Thread {

    @PostMapping("/v1/OTPRequest")
    public otpModel otp(
            @RequestParam(defaultValue = "") String sign,
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "") String msisdn,
            @RequestParam(defaultValue = "") String channel,
            @RequestParam(defaultValue = "") String transactionNo
    ) throws SQLException {

        otpModel model = new otpModel();

        if (sign.equals("")
                || msisdn.equals("")
                || transactionNo.equals("")
                || channel.equals("")
                || userName.equals("")
        ) {

            model.setResultCode(GlobalParameter.error_no_content);
            model.setResultMsg(GlobalParameter.error_no_content_msg);
            model.setSmsresultcode("");
            return model;
        }

        GenerateSignkey_sha256 signkey_sha256 = new GenerateSignkey_sha256();
        String des_url = "/etllao.com/v1/Otpsms";
        String remark = "";
        String extraParams = "";
        String uuid = "";
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


        String fixnumber = null;
        String fixnumber2 = null;
        fixnumber = msisdn.substring(0, 3);
        fixnumber2 = msisdn.substring(0, 6);
        ;
        System.out.println("Numberinput=====" + fixnumber);
        System.out.println("Numberinput2222=====" + fixnumber2);

        if ((fixnumber.equals("205"))
                || fixnumber.equals("207")
                || fixnumber.equals("208")
                || fixnumber.equals("209")
                || fixnumber.equals("204")
                || fixnumber.equals("206")
                || fixnumber2.equals("856204")
                || fixnumber2.equals("856205")
                || fixnumber2.equals("856206")
                || fixnumber2.equals("856207")
                || fixnumber2.equals("856208")
                || fixnumber2.equals("856209")
                || fixnumber2.equals("856304")
                || fixnumber2.equals("856305")
                || fixnumber2.equals("856306")
                || fixnumber2.equals("856307")
                || fixnumber2.equals("856308")
                || fixnumber2.equals("856309")) {
//
//            model.setResultCode(GlobalParameter.fail_fixnumber);
//            model.setResultMsg(GlobalParameter.fail_fixnumber_msg);
            model.setTransactionNo(transactionNo);
            model.setSmsresultcode("");
            return model;
        }

        //=================================
        try {

            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
            String dateNow = formatter.format(currentDate.getTime());
            String datepro = dateNow.toString();


            // Adding 2 mins using Date constructor.
            Calendar date = Calendar.getInstance();
            long timeInSecs = date.getTimeInMillis();
            Date afterAdding10Mins = new Date(timeInSecs + (2 * 60 * 1000));
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
            String expiredate = formatter1.format(afterAdding10Mins);


            PreparedStatement pstmt;
            ResultSet rs;
            DatabaseConnectionPool dbConnectionPool = null;
            Connection connection = null;
            Statement statementtAuth = null;
            ResultSet resultSettAuth = null;
            Connection conntAuth = null;
            String sql = "INSERT INTO tb_otp_info (msisdn, otp, datetime, expiredate)"
                    + " VALUES (?, ?, ?, ?)";
            if (serverSign.equals(sign.trim())) {


                //
                try {
                    String OTP = generateOTP();
                    System.out.println("OTP =======================" + OTP);


                    dbConnectionPool = new DatabaseConnectionPool(
                            Config.driverServr,
                            Config.dburlServr,
                            Config.dbUserNameServr,
                            Config.dbPasswordServr);
                    connection = dbConnectionPool.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, msisdn);
                    statement.setString(2, OTP);
                    statement.setString(3, datepro);
                    statement.setString(4, expiredate);
                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {


                        // Gen JWT
                        String userid = "etlkyc221012072416b0bc56c9";
                        String jwtTokenStrng = "";

                        // createJWTSec(String id, long ttlMillis, String userName, String userID)

                        JWT_Security_Encode_Decode_Java encode_Decode_Java = new JWT_Security_Encode_Decode_Java();
                        long ttlMillis = 604800000; // 604800000 = 1 week, 3 h = 10800000 ms
                        jwtTokenStrng = encode_Decode_Java.createJWTSec1(transactionNo, ttlMillis, userid, msisdn);

                        System.out.println("jwtTokenStrng ======== " + jwtTokenStrng);

                        //          <============================   Send SMS    ===============================>
                        boolean optss = false;
                        sms svss = new sms();
                        optss = svss.sendsmss(msisdn, OTP);

                        if (optss) {

                            sms sendotp = new sms();
                            String sendOTP = String.valueOf(sendotp.sendsmss(msisdn, OTP));

                            System.out.println("msisdn ============" + msisdn);

                            //          <============================   Send SMS    ===============================>


                            model.setResultCode(GlobalParameter.error_ok_success);
                            model.setResultMsg(GlobalParameter.error_ok_success_msg);
                            model.setUserID(userid);
                            model.setTransactionNo(transactionNo);
                            model.setSmsresultcode("2");
                            model.setToken(jwtTokenStrng);

                            return model;
                        }

                        model.setResultCode(GlobalParameter.fail_SOAP_Config);
                        model.setResultMsg(GlobalParameter.fail_SOAP_Config_msg);
                        model.setSmsresultcode("");

                        return model;
                    }

                } catch (SQLException ex) {

                    model.setResultCode(GlobalParameter.fail_requestotp_status);
                    model.setResultMsg(GlobalParameter.fail_requestotp_status_msg);
                    model.setSmsresultcode("");

                    return model;
                } finally {

                    try {

                        dbConnectionPool.freeConnection(connection);
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

                        model.setResultCode(GlobalParameter.fail_requestotp_status);
                        model.setResultMsg(GlobalParameter.fail_requestotp_status_msg);
                        model.setSmsresultcode("");
                    }
                }
                model.setResultCode(GlobalParameter.fail_requestotp_status);
                model.setResultMsg(GlobalParameter.fail_requestotp_status_msg);
                model.setSmsresultcode("");
                return model;
            } else {

                model.setResultCode(GlobalParameter.error_non_authoritative_sign);
                model.setResultMsg(GlobalParameter.error_non_authoritative_msg_sign);
                return model;

            }
        } catch (Exception e) {
            return model;
        }
    }

    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}