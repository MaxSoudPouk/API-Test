package com.etl.kyc.etlkycsimregisterapi.OTPsms;

import com.etl.kyc.etlkycsimregisterapi.changpassword.EtlkycChangPasswordController;
import com.etl.kyc.etlkycsimregisterapi.db.Config;
import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

@RestController
public class OtpRequestController extends Thread {

    public static HttpServletRequest request;
    DatabaseConnectionPool dbConnectionPool;


    public OtpRequestController() {
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

    @PostMapping("/v1/OtpRequest")
    public otpModel otp(
            @RequestParam(defaultValue = "") String PhoneNumber
    ) throws SQLException {
        otpModel model = new otpModel();

        //=================================

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

        System.out.println("Datepro =======================" + datepro);
        System.out.println("After adding 10 mins===========" + expiredate);


        PreparedStatement pstmt;
        ResultSet rs;
        DatabaseConnectionPool dbConnectionPool = null;
        Connection connection = null;
        Statement statementtAuth = null;
        ResultSet resultSettAuth = null;
        Connection conntAuth = null;
        String sql = "INSERT INTO tb_otp_info (msisdn, otp, datetime, expiredate)"
                + " VALUES (?, ?, ?, ?)";

        if (!PhoneNumber.isEmpty()) {
            try {
                //          <============================   Send SMS    ===============================>
                String OTP = generateOTP();
                sms sendotp = new sms();
                String sendOTP = String.valueOf(sendotp.sendsmss(PhoneNumber, OTP));

                //          <============================   Send SMS    ===============================>

                dbConnectionPool = new DatabaseConnectionPool(
                        Config.driverServr,
                        Config.dburlServr,
                        Config.dbUserNameServr,
                        Config.dbPasswordServr);
                connection = dbConnectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, PhoneNumber);
                statement.setString(2, OTP);
                statement.setString(3, datepro);
                statement.setString(4, expiredate);
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    model.setResultCode(GlobalParameter.error_ok_success);
                    model.setResultMsg(GlobalParameter.error_ok_success_msg);
                    model.setOtp(OTP);
                    model.setExpiredate(expiredate);
                    model.setDateTime(datepro);

                    return model;
                }

            } catch (SQLException ex) {

                model.setResultCode(GlobalParameter.error_insert_sim_register);
                model.setResultMsg(GlobalParameter.error_insert_sim_register_smg);


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

                }
            }


        }
        model.setResultCode(GlobalParameter.error_not_acceptable);
        model.setResultMsg(GlobalParameter.error_not_acceptable_msg);

        return model;
    }

    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}
