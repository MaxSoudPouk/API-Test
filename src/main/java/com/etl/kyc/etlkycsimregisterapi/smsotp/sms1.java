//package com.etl.kyc.etlkycsimregisterapi.smsotp;
//
//import okhttp3.*;
//import org.w3c.dom.Document;
//import org.xml.sax.InputSource;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.StringReader;
//
//public class sms extends Thread {
//
//    boolean sendsmss(String msisdn, String otp) {
//        try {
//
//
//            String url = "http://103.13.90.39:8898/SmsTicketSmppTicketmanageWS/services/SmsApiWS.SmsApiWSHttpSoap11Endpoint/";
//            String content = "OTP ຂອງທ່ານແມ່ນ ###" + otp + "### ໃຊ້ລະຫັດນີ້ເພື່ອເຮັດທຸລະກໍາຂອງທ່ານໃຫ້ສໍາເລັດຂອບໃຈ.";
//            System.out.println("PhoneNumber =========== " + msisdn);
//            System.out.println("content =========== " + content);
//
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//            MediaType mediaType = MediaType.parse("text/xml;charset=UTF-8");
//            RequestBody body = RequestBody.create(mediaType,
//                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sms=\"http://SmsApiWS.fadao.com\">\n"
//                            + "   <soapenv:Header/>\n"
//                            + "   <soapenv:Body>\n"
//                            + "      <sms:BulkBroadcastingMessaging>\n"
//                            + "         <!--Optional:-->\n"
//                            + "         <sms:SpId>101081y723354u4660191</sms:SpId>\n"
//                            + "         <!--Optional:-->\n"
//                            + "         <sms:SpPassword>appAndroid</sms:SpPassword>\n"
//                            + "         <!--Optional:-->\n"
//                            + "         <sms:ServicID>1000002222</sms:ServicID>\n"
//                            + "         <!--Optional:-->\n"
//                            + "         <sms:TransactionID>123456</sms:TransactionID>\n"
//                            + "         <!--Optional:-->\n"
//                            + "         <sms:SrcAddr>ETLKYC</sms:SrcAddr>\n"
//                            + "         <!--Optional:-->\n"
//                            + "         <sms:DestAddrss>" + msisdn + "</sms:DestAddrss>\n"
//                            + "         <!--Optional:-->\n"
//                            + "         <sms:BodyMsg>" + content + "</sms:BodyMsg>\n"
//                            + "         <!--Optional:-->\n"
//                            + "         <sms:reportRequire>0</sms:reportRequire>\n"
//                            + "         <!--Optional:-->\n"
//                            + "         <sms:validityPeriod>0</sms:validityPeriod>\n"
//                            + "      </sms:BulkBroadcastingMessaging>\n"
//                            + "   </soapenv:Body>\n" + "</soapenv:Envelope>");
//            Request request = new Request.Builder()
//                    .url(url)
//                    .method("POST", body)
//                    .addHeader("Content-Type", "text/xml")
//                    .build();
//            Response response = client.newCall(request).execute();
//
//            String getxml = response.body().string();
//
//            System.out.println("response ========= " + getxml);
//
//
//            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            InputSource src = new InputSource();
//            src.setCharacterStream(new StringReader(getxml));
//
//            Document doc = builder.parse(src);
//            String status = doc.getElementsByTagName("ax23:apiResponseCode").item(0).getTextContent();
////
//            System.out.println("status ===================== " + status);
//
//
//            if (status.equals("2")) {
//                return true;
//            } else {
//                return false;
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return false;
//    }
//}
