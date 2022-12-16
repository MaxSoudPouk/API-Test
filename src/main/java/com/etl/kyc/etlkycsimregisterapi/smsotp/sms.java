package com.etl.kyc.etlkycsimregisterapi.smsotp;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class sms extends Thread {

    boolean sendsmss(String msisdn, String otp) {

        try {


            String url = "http://103.13.90.39:8898/SmsTicketSmppTicketmanageWS/services/SmsApiWS.SmsApiWSHttpSoap11Endpoint/";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
          String content = "OTP is ###" + otp + "###";
//            String content = "你的 OTP 是 ###" + otp + "###";
//            String content = "OTP ຂອງທ່ານແມ່ນ ###" + otp + "### ໃຊ້ລະຫັດນີ້ເພື່ອເຮັດທຸລະກໍາຂອງທ່ານໃຫ້ສໍາເລັດຂອບໃຈ.";
            System.out.println("PhoneNumber =========== " + msisdn);
            System.out.println("content =========== " + content);

            StringBuffer getreturn = null;
            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                    + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sms=\"http://SmsApiWS.fadao.com\">\n"
                    + "   <soapenv:Header/>\n"
                    + "   <soapenv:Body>\n"
                    + "      <sms:BulkBroadcastingMessaging>\n"
                    + "         <!--Optional:-->\n"
                    + "         <sms:SpId>101081y723354u4660191</sms:SpId>\n"
                    + "         <!--Optional:-->\n"
                    + "         <sms:SpPassword>appAndroid</sms:SpPassword>\n"
                    + "         <!--Optional:-->\n"
                    + "         <sms:ServicID>1000002222</sms:ServicID>\n"
                    + "         <!--Optional:-->\n"
                    + "         <sms:TransactionID>123456</sms:TransactionID>\n"
                    + "         <!--Optional:-->\n"
                    + "         <sms:SrcAddr>ETLKYC</sms:SrcAddr>\n"
                    + "         <!--Optional:-->\n"
                    + "         <sms:DestAddrss>" + msisdn + "</sms:DestAddrss>\n"
                    + "         <!--Optional:-->\n"
                    + "         <sms:BodyMsg>" + content + "</sms:BodyMsg>\n"
                    + "         <!--Optional:-->\n"
                    + "         <sms:reportRequire>0</sms:reportRequire>\n"
                    + "         <!--Optional:-->\n"
                    + "         <sms:validityPeriod>0</sms:validityPeriod>\n"
                    + "      </sms:BulkBroadcastingMessaging>\n"
                    + "   </soapenv:Body>\n" + "</soapenv:Envelope>";


            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String getxml = in.readLine();

            System.out.println("getxml ======  SMSSSSSSS:" + getxml);
            String inputLine;
            StringBuffer response = new StringBuffer();

            //============================

            //otpModel requesmodel = new otpModel();
            //	requesmodel.setSmsresultcode("2");

            //=========================


            //System.out.println("getvalue    SMSSSSSSS:" + getvalue);
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            con.disconnect();
            otp = "";


            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(getxml));

            Document doc = builder.parse(src);
            String status = doc.getElementsByTagName("ax23:apiResponseCode").item(0).getTextContent();

            System.out.println("status ===================== " + status);

            if (status.equals("2")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e3) {
            return false;
        }
    }
}