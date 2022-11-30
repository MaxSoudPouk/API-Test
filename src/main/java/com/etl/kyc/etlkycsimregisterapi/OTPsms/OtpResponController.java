package com.etl.kyc.etlkycsimregisterapi.OTPsms;

import com.etl.kyc.etlkycsimregisterapi.changpassword.EtlkycChangPasswordController;
import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

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
        return null;
    }
}
