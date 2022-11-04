package com.etl.kyc.etlkycsimregisterapi.simregister;


import com.etl.kyc.etlkycsimregisterapi.db.Config;
import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
import com.etl.kyc.etlkycsimregisterapi.security.GenerateSignkey_sha256;
import com.etl.kyc.etlkycsimregisterapi.security.JWT_Security_Encode_Decode_Java;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

@RestController
public class EtlkycSimregisterController extends Thread {
    static Calendar cal = new GregorianCalendar(Locale.ROOT);
    static int current_year = cal.get(Calendar.YEAR);
    static int current_week = cal.get(Calendar.WEEK_OF_YEAR);
    static String current_time = current_year + "" + current_week;
    public static String linux_DIRECTORY = "/home/kycimage/" + current_time + "/";
    // public static String window_DIRECTORY = System.getProperty("C:\\kycimage\\image"+current_week+"\\");
    public static String window_DIRECTORY = "C:\\kycimage\\" + current_time + "\\";
    //public static String windowpath=directory=C:\Users\lacasoub\myUser\Local\Temp
    private static HttpServletRequest request;
    PreparedStatement pstmt;
    ResultSet rs;
    DatabaseConnectionPool dbConnectionPool;
    Connection connection1;
    public EtlkycSimregisterController() {

        System.out.println("Sim register Start");

    }

    private void setRequest(HttpServletRequest request) {
        EtlkycSimregisterController.request = request;

    }

    @PostMapping("/v1/simregister")
    public SimregisterModel Simregister(
            @RequestParam(defaultValue = "") String sign,
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "") String extraParams,
            @RequestParam(defaultValue = "") String channel,
            @RequestParam(defaultValue = "") String transactionNo,
            @RequestParam(defaultValue = "") String uuid,
            @RequestParam(defaultValue = "") String latitude,
            @RequestParam(defaultValue = "") String longtitude,
            @RequestParam(defaultValue = "") String token,
            @RequestParam(defaultValue = "") String msisdn,
            @RequestParam(defaultValue = "") String userid,
            @RequestParam(defaultValue = "") String fname,
            @RequestParam(defaultValue = "") String lname,
            @RequestParam(defaultValue = "") int gender_id,
            @RequestParam(defaultValue = "") String birthday,
            @RequestParam(defaultValue = "") int document_id,
            @RequestParam(defaultValue = "") String occupation,
            @RequestParam(defaultValue = "") int simtype_id,
            @RequestParam(defaultValue = "") int province_id,
            // @RequestParam(defaultValue = "") int  district_id,
            // @RequestParam(defaultValue = "") int village_id,
            @RequestParam(defaultValue = "") String address_reg,
            @RequestParam(defaultValue = "") String devicename,
            @RequestParam(defaultValue = "") String osversion,
            @RequestParam(defaultValue = "") String remark,
            @RequestParam(defaultValue = "") MultipartFile img_person,
            @RequestParam(defaultValue = "") MultipartFile img_doc,
            @RequestParam(defaultValue = "") MultipartFile img_sim


    ) throws NoSuchAlgorithmException, java.io.IOException {


        System.out.println(sign.length());


        SimregisterModel simregismodel = new SimregisterModel();


        if (channel.equals("") || transactionNo.equals("") || token.equals("") || userid.equals("")
                || sign.equals("") || longtitude.equals("") || latitude.equals("")
                || fname.equals("") || lname.equals("") || msisdn.equals("") ||
                // gender_id<'0'|| birthday.equals("")|| 
                //document_id<'0'||
                occupation.equals("") ||
                // simtype_id<'0' ||province_id<'0' ||
                address_reg.equals("") ||
                devicename.equals("") || osversion.equals("")
                || img_person.isEmpty() || img_doc.isEmpty() || img_sim.isEmpty()
        ) {

            // System.out.println("token=" + token);
            simregismodel.setResultCode(GlobalParameter.error_no_content);
            simregismodel.setResultMsg(GlobalParameter.error_no_content_msg);
            simregismodel.setTransactionID(transactionNo);
            simregismodel.setExtraPara("Some Parameter Null");

            return simregismodel;

        }

        // System.out.println("sign.length()=" + sign.length());
//                
        if (sign.length() != 64) {

            simregismodel.setResultCode(GlobalParameter.error_sign);
            simregismodel.setResultMsg(GlobalParameter.error_sign_msg);
            simregismodel.setExtraPara("Sign not correct");
            return simregismodel;

        }

        GenerateSignkey_sha256 signkey_sha256 = new GenerateSignkey_sha256();
        String des_url = "/etllao.com/v1/simregister";

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


        SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter111.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));


        if (serverSign.equals(sign.trim())) {

            boolean jwtencoderesult = false;

            JWT_Security_Encode_Decode_Java encode_Decode_Java = new JWT_Security_Encode_Decode_Java();
            jwtencoderesult = encode_Decode_Java.deCodeJWT_validate(token, userid, userName);


            System.out.println(userid);
            System.out.println("##### /v1/jwtencoderesult=" + jwtencoderesult);
            //============================================================================================
            if (jwtencoderesult) {
                boolean save_img_person = false;
                boolean save_img_doc = false;
                boolean save_sim = false;

                String URL_person_linux = "";
                String URL_doc_linux = "";
                String URL_sim_linux = "";

                try {

                    Uploadimage imgup = new Uploadimage();
                    boolean isLinux = System.getProperty("os.name").startsWith("Linux");

                    System.out.println("isLinux=" + isLinux);

                    System.out.println("window_DIRECTORY=" + window_DIRECTORY);
                    System.out.println("current_time=======" + current_time);


                    String PATH;
                    if (isLinux) {
                        PATH = linux_DIRECTORY;
                    } else {
                        PATH = window_DIRECTORY;
                    }

                    //============IP address===============
                    InetAddress ip;
                    String hostname;
                    ip = InetAddress.getLocalHost();
                    hostname = ip.getHostAddress();

                    System.out.println("ip=" + ip);

                    System.out.println("hostname=" + hostname);

                    //==============================


                    String img_person_name = "P" + msisdn + ".png";
                    String img_doc_name = "D" + msisdn + ".png";
                    String img_sim_name = "S" + msisdn + ".png";

                    save_img_person = imgup.image_person(PATH, img_person_name, img_person);
                    save_img_doc = imgup.image_doc(PATH, img_doc_name, img_doc);
                    save_sim = imgup.image_sim(PATH, img_sim_name, img_sim);
                    //=====================URL save to db===========================

                    //String URL_person_window = ServletUriComponentsBuilder.fromCurrentContextPath().path(PATH+"\\"+ img_person_name).build().toUriString();
                    URL_person_linux = "/kycimage/" + current_time + "/" + img_person_name;
                    URL_doc_linux = "/kycimage/" + current_time + "/" + img_doc_name;
                    URL_sim_linux = "/kycimage/" + current_time + "/" + img_sim_name;
                    // URL_doc_linux = ServletUriComponentsBuilder.fromCurrentContextPath().pathSegment(PATH+"/"+ img_doc_name).build().toUriString();
                    // URL_sim_linux = ServletUriComponentsBuilder.fromCurrentContextPath().pathSegment(PATH+"/"+ img_sim_name).build().toUriString();

                    System.out.println("URL_person_linux==========================" + URL_person_linux);


                } catch (Exception ex) {

                    simregismodel.setResultCode(GlobalParameter.error_upload_image);
                    simregismodel.setResultMsg(GlobalParameter.error_upload_image_msg);
                    simregismodel.setExtraPara("n");

                    return simregismodel;

                }


                if (save_img_person && save_img_doc && save_sim) {

                    System.out.println("Upload image Success");


                    String strRetunr = null;
                    Statement statementtAuth = null;
                    ResultSet resultSettAuth = null;
                    Connection conntAuth = null;


                    String sql = " insert into tb_subscriber_info (msisdn, fname, lname, gender_id, birthday, document_id, occupation, simtype_id, province_id,"
                            + "address_reg, latitude, longtitude, devicename, osversion, img_person_path, img_doc_path, img_sim_path, user_id)"
                            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    try {
                        dbConnectionPool = new DatabaseConnectionPool(Config.driverServr, Config.dburlServr, Config.dbUserNameServr,
                                Config.dbPasswordServr);
                        connection1 = dbConnectionPool.getConnection();
                        PreparedStatement statement = connection1.prepareStatement(sql);
                        statement.setString(1, msisdn);
                        statement.setString(2, fname);
                        statement.setString(3, lname);
                        statement.setInt(4, gender_id);
                        statement.setString(5, birthday);
                        statement.setInt(6, document_id);
                        statement.setString(7, occupation);
                        statement.setInt(8, simtype_id);
                        statement.setInt(9, province_id);
                        // statement.setInt(10, district_id);
                        // statement.setInt(11, village_id);
                        statement.setString(10, address_reg);
                        statement.setString(11, latitude);
                        statement.setString(12, longtitude);
                        statement.setString(13, devicename);
                        statement.setString(14, osversion);
                        statement.setString(15, URL_person_linux);
                        statement.setString(16, URL_doc_linux);
                        statement.setString(17, URL_sim_linux);
                        statement.setString(18, userid);
                        int rowsUpdated = statement.executeUpdate();

                        System.out.println("sql=====" + sql);
                        if (rowsUpdated > 0) {
                            System.out.println("An existing user was updated successfully!");

                            simregismodel.setResultCode(GlobalParameter.error_ok_success);
                            simregismodel.setResultMsg(GlobalParameter.error_ok_success_msg);
                            simregismodel.setTransactionID(transactionNo);
                            simregismodel.setExtraPara("n");

                            return simregismodel;
                        }

                    } catch (SQLException ex) {

                        simregismodel.setResultCode(GlobalParameter.error_insert_sim_register);
                        simregismodel.setResultMsg(GlobalParameter.error_insert_sim_register_smg);
                        simregismodel.setExtraPara("n");

                        return simregismodel;
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
                    simregismodel.setResultCode(GlobalParameter.error_upload_image);
                    simregismodel.setResultMsg(GlobalParameter.error_upload_image_msg);
                    simregismodel.setExtraPara("Error Upload image");

                    return simregismodel;

                }


                //=======================================================================================================
            } else {

                simregismodel.setResultCode(GlobalParameter.error_invalidtoken);
                simregismodel.setResultMsg(GlobalParameter.error_invalidtoken_msg);
                simregismodel.setExtraPara("n");

                return simregismodel;

            }

        } else {

            simregismodel.setResultCode(GlobalParameter.error_non_authoritative_sign);
            simregismodel.setResultMsg(GlobalParameter.error_non_authoritative_msg_sign);
            simregismodel.setExtraPara("n");

            return simregismodel;

        }
        return simregismodel;
    }


}
