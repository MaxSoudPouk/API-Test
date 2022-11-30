package com.etl.kyc.etlkycsimregisterapi.OTPsms;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class sms extends Thread {

    String sendsmss(String PhoneNumber, String otp) {

        try {
            String url = "http://103.13.90.39:8898/SmsTicketSmppTicketmanageWS/services/SmsApiWS.SmsApiWSHttpSoap11Endpoint/";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            String countryCode = "Canada";
            String content = "OTP is ###" + otp + "###";
            System.out.println("PhoneNumber===========" + PhoneNumber);
            System.out.println("content===========" + content);
            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sms=\"http://SmsApiWS.fadao.com\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <sms:BulkBroadcastingMessaging>\n" +
                    "         <!--Optional:-->\n" +
                    "         <sms:SpId>101081y723354u4660191</sms:SpId>\n" +
                    "         <!--Optional:-->\n" +
                    "         <sms:SpPassword>appAndroid</sms:SpPassword>\n" +
                    "         <!--Optional:-->\n" +
                    "         <sms:ServicID>1000002222</sms:ServicID>\n" +
                    "         <!--Optional:-->\n" +
                    "         <sms:TransactionID>123456</sms:TransactionID>\n" +
                    "         <!--Optional:-->\n" +
                    "         <sms:SrcAddr>Payment</sms:SrcAddr>\n" +
                    "         <!--Optional:-->\n" +
                    "         <sms:DestAddrss>" + PhoneNumber + "</sms:DestAddrss>\n" +
                    "         <!--Optional:-->\n" +
                    "         <sms:BodyMsg>" + content + "</sms:BodyMsg>\n" +
                    "         <!--Optional:-->\n" +
                    "         <sms:reportRequire>0</sms:reportRequire>\n" +
                    "         <!--Optional:-->\n" +
                    "         <sms:validityPeriod>0</sms:validityPeriod>\n" +
                    "      </sms:BulkBroadcastingMessaging>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("response:" + response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return PhoneNumber;
    }
}
