package com.etl.kyc.etlkycsimregisterapi.WebPortalSimRegister;//package com.etl.kyc.etlkycsimregisterapi.WebPortalSimRegister;
//
//import com.etl.kyc.etlkycsimregisterapi.db.Config;
//import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
//import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
//import com.etl.kyc.etlkycsimregisterapi.simregister.Uploadimage;
//import net.minidev.json.JSONObject;
//import net.minidev.json.JSONValue;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.Locale;
//import java.util.TimeZone;
//
//@RestController
//public class WebPortalSimRegisterController {
//
//
//    //private static final String PATTERN_FORMAT = "yyyy-MM-dd HH:mm:ss";
//    private static final String PATTERN_FORMAT = "HHmmss";
//    static Calendar cal = new GregorianCalendar(Locale.ROOT);
//    static int current_year = cal.get(Calendar.YEAR);
//    static int current_week = cal.get(Calendar.WEEK_OF_YEAR);
//    static String current_time = current_year + "" + current_week;
//    public static String linux_DIRECTORY = "/home/kycimage/" + current_time + "/";
//    // public static String window_DIRECTORY = System.getProperty("C:\\kycimage\\image"+current_week+"\\");
//    public static String window_DIRECTORY = "C:\\kycimage\\" + current_time + "\\";
//    private static HttpServletRequest request;
//    PreparedStatement pstmt;
//    ResultSet rs;
//    DatabaseConnectionPool dbConnectionPool;
//    //public static String windowpath=directory=C:\Users\lacasoub\myUser\Local\Temp
//    Connection connection1;
//
//
//    @PostMapping("/v1/WebPortalSimregister")
//    public WebPortalSimregisterModel Simregister(
//            @RequestParam(defaultValue = "") String JSON,
//            @RequestParam(defaultValue = "") String sign,
//            @RequestParam(defaultValue = "") MultipartFile img_person,
//            @RequestParam(defaultValue = "") MultipartFile img_doc,
//            @RequestParam(defaultValue = "") MultipartFile img_sim,
//            @RequestParam(defaultValue = "") MultipartFile img_org_register
//
//
//    ) throws NoSuchAlgorithmException, java.io.IOException {
//
//        WebPortalSimregisterModel simregismodel = new WebPortalSimregisterModel();
//        System.out.println("JSON =========== " + JSON);
//
//
//        // =========================== JSON decode =========================== //
//
//
//        Object o1 = JSONValue.parse(JSON);
//        JSONObject jsonObj = (JSONObject) o1;
//
//
//        String organization = (String) jsonObj.get("organization");
//        String name_surname = (String) jsonObj.get("name_surname");
//        int gender = (int) jsonObj.get("gender");
//        String msisdn = (String) jsonObj.get("msisdn");
//        String birthday = (String) jsonObj.get("birthday");
//        String idcard = (String) jsonObj.get("idcard");
//        String idfamily = (String) jsonObj.get("idfamily");
//        String passport = (String) jsonObj.get("passport");
//        int village = (int) jsonObj.get("village");
//        int district = (int) jsonObj.get("district");
//        int province = (int) jsonObj.get("province");
//        String docno = (String) jsonObj.get("docno");
//        String docno_org = (String) jsonObj.get("docno_org");
//        String longtitude = (String) jsonObj.get("longtitude");
//        String latitude = (String) jsonObj.get("latitude");
//        String occupation = (String) jsonObj.get("occupation");
//
//
//        System.out.println("sign :" + sign);
//        System.out.println("organization :" + organization);
//        System.out.println("name_surname :" + name_surname);
//        System.out.println("gender :" + gender);
//        System.out.println("msisdn :" + msisdn);
//        System.out.println("birthday :" + birthday);
//        System.out.println("idcard :" + idcard);
//        System.out.println("idfamily :" + idfamily);
//        System.out.println("passport :" + passport);
//        System.out.println("village :" + village);
//        System.out.println("district :" + district);
//        System.out.println("province :" + province);
//        System.out.println("docno :" + docno);
//        System.out.println("longtitude :" + longtitude);
//        System.out.println("latitude :" + latitude);
//        System.out.println("occupation :" + occupation);
//
//        // =========================== JSON decode end =========================== //
//
//
//        if (idfamily.equals("") && passport.equals("")) {
//            int doccument_id = 1;
//            System.out.println(doccument_id);
//        }else if (idcard.equals("") && passport.equals("")) {
//            int doccument_id = 2;
//            System.out.println(doccument_id);
//        }else if ( idfamily.equals("") && idcard.equals("")) {
//            int doccument_id = 3;
//            System.out.println(doccument_id);
//        }
//
//
//
//
//
//        if (sign.equals("")
//                || organization.equals("")
//                || name_surname.equals("")
//                || msisdn.equals("")
//                || birthday.equals("")
//                || docno.equals("")
//                || img_person.isEmpty()
//                || img_doc.isEmpty()
//                || img_sim.isEmpty()
//                || img_org_register.isEmpty()
//        ) {
//
//            // System.out.println("token=" + token);
//            simregismodel.setResultCode(GlobalParameter.error_no_content);
//            simregismodel.setResultMsg(GlobalParameter.error_no_content_msg);
////            simregismodel.setTransactionID(transactionNo);
//            simregismodel.setExtraPara("Some Parameter Null");
//
//            return simregismodel;
//
//        }
//
//
//
//        SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        formatter111.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
//
//
//        if (sign.equals("9c5f1181fd9341ea08885dd5574be63a51545342e69b6e561301c2858d1abb03")) {
//
//
////            //===================check number status=================
////
//            PreparedStatement pstmt;
//            ResultSet rs;
//            DatabaseConnectionPool dbConnectionPool = null;
//            Connection connection1 = null;
//            Statement statementtAuth = null;
//            ResultSet resultSettAuth = null;
//            Connection conntAuth = null;
////            String querysql = "SELECT t.status_id,k.status_name from tb_subscriber_info t  INNER JOIN tb_status_info k \r\n"
////                    + "on t.status_id=k.status_id WHERE t.status_id in('1','2','4') and  t.msisdn='" + msisdn + "' order by t.reg_date desc LIMIT 1";
////
////            try {
////
////                dbConnectionPool = new DatabaseConnectionPool(
////                        Config.driverServr,
////                        Config.dburlServr,
////                        Config.dbUserNameServr,
////                        Config.dbPasswordServr);
////                connection1 = dbConnectionPool.getConnection();
////                pstmt = connection1.prepareStatement(querysql);
////                rs = pstmt.executeQuery();
////                int rsfound = 0;
////
////                String status_id = null;
////                String status_name = null;
////                while (rs != null && rs.next()) {
////                    rsfound++;
////                    status_id = rs.getString("status_id");
////                    status_name = rs.getString("status_name");
////
////                    //System.out.println("status_id-======:: " + status_id);
////
////                    //System.out.println("status_name-======:: " + status_name);
////
////                }
////                if (rsfound > 0) {
////                    simregismodel.setResultCode(GlobalParameter.fail_query_status);
////                    simregismodel.setResultMsg(GlobalParameter.fail_query_status_msg);
//////                        simregismodel.setTransactionID(transactionNo);
////                    simregismodel.setExtraPara("n");
////
////                    return simregismodel;
////
////                }
////
////            } catch (Exception e) {
////                simregismodel.setResultCode(GlobalParameter.error_querymobile_status);
////                simregismodel.setResultMsg(GlobalParameter.error_querymobile_status_msg);
//////                    simregismodel.setTransactionID(transactionNo);
////                return simregismodel;
////
////            }
////            //=======================End check status================
//
//            boolean save_img_person = false;
//            boolean save_img_doc = false;
//            boolean save_sim = false;
//            boolean save_org = false;
//
//            String URL_person_linux = "";
//            String URL_doc_linux = "";
//            String URL_sim_linux = "";
//            String URL_org_linux = "";
//
//            try {
//
//                Uploadimage imgup = new Uploadimage();
//                boolean isLinux = System.getProperty("os.name").startsWith("Linux");
//
//
//                String PATH;
//                if (isLinux) {
//                    PATH = linux_DIRECTORY;
//                } else {
//                    PATH = window_DIRECTORY;
//                }
//
//
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.systemDefault());
//
//                Instant instant = Instant.now();
//                String timename = formatter.format(instant);
//
//
//                String img_person_name = "person" + msisdn + timename + ".pdf";
//                String img_doc_name = "doc" + msisdn + timename + ".pdf";
//                String img_sim_name = "sim" + msisdn + timename + ".pdf";
//                String img_org_name = "org" + msisdn + timename + ".pdf";
//
//                save_img_person = imgup.image_person(PATH, img_person_name, img_person);
//                save_img_doc = imgup.image_doc(PATH, img_doc_name, img_doc);
//                save_sim = imgup.image_sim(PATH, img_sim_name, img_sim);
//                save_org = imgup.img_org_register(PATH, img_org_name, img_org_register);
//                //=====================URL save to db===========================
//
//                //String URL_person_window = ServletUriComponentsBuilder.fromCurrentContextPath().path(PATH+"\\"+ img_person_name).build().toUriString();
//                URL_person_linux = "/kycimage/" + current_time + "/" + img_person_name;
//                URL_doc_linux = "/kycimage/" + current_time + "/" + img_doc_name;
//                URL_sim_linux = "/kycimage/" + current_time + "/" + img_sim_name;
//                URL_org_linux = "/kycimage/" + current_time + "/" + img_org_name;
//
//
//            } catch (Exception ex) {
//
//                simregismodel.setResultCode(GlobalParameter.error_upload_image);
//                simregismodel.setResultMsg(GlobalParameter.error_upload_image_msg);
////                    simregismodel.setTransactionID(transactionNo);
//                simregismodel.setExtraPara("n");
//
//                return simregismodel;
//
//            }
//
//            if (save_img_person && save_img_doc && save_sim && save_org) {
//
//                //System.out.println("Upload image Success");
//
////
////						String strRetunr = null;
////						Statement statementtAuth = null;
////						ResultSet resultSettAuth = null;
////						Connection conntAuth = null;
//                //=================================== default =================================== //
//                int simtype_id = 1;
//                int channel = 3;
//                int status_id = 2;
//                int sub_type_id = 2;
////=================================== default =================================== //
//                String sql = " insert into tb_subscriber_info (msisdn, fname, lname, gender_id, birthday, occupation, simtype_id,"
//                        + "province_id, district_id, village_id, latitude, longtitude, img_person_path, img_doc_path, " +
//                        "img_sim_path, img_org_register, docno_org, channel_id, status_id, sub_type_id)"
//                        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//                try {
//                    dbConnectionPool = new DatabaseConnectionPool(
//                            Config.driverServr,
//                            Config.dburlServr,
//                            Config.dbUserNameServr,
//                            Config.dbPasswordServr);
//                    connection1 = dbConnectionPool.getConnection();
//                    PreparedStatement statement = connection1.prepareStatement(sql);
//                    statement.setString(1, msisdn);
//                    statement.setString(2, organization);
//                    statement.setString(3, name_surname);
//                    statement.setInt(4, gender);
//                    statement.setString(5, birthday);
//                    statement.setString(6, occupation);
//                    statement.setInt(7, simtype_id);
//                    statement.setInt(8, province);
//                    statement.setInt(9, district);
//                    statement.setInt(10, village);
//                    statement.setString(11, latitude);
//                    statement.setString(12, longtitude);
//                    statement.setString(13, URL_person_linux);
//                    statement.setString(14, URL_doc_linux);
//                    statement.setString(15, URL_sim_linux);
//                    statement.setString(16, URL_org_linux);
//                    statement.setString(17, docno_org);
//                    statement.setInt(18, channel);
//                    statement.setInt(19, status_id);
//                    statement.setInt(20, sub_type_id);
//
//                    int rowsUpdated = statement.executeUpdate();
//
//                    // System.out.println("sql====="+ sql);
//                    if (rowsUpdated > 0) {
//                        //  System.out.println("An existing user was updated successfully!");
//
//                        simregismodel.setResultCode(GlobalParameter.error_ok_success);
//                        simregismodel.setResultMsg(GlobalParameter.error_ok_success_msg);
//
//                        simregismodel.setExtraPara("n");
//
//                        return simregismodel;
//                    }
//
//                } catch (SQLException ex) {
//
//                    simregismodel.setResultCode(GlobalParameter.error_insert_sim_register);
//                    simregismodel.setResultMsg(GlobalParameter.error_insert_sim_register_smg);
////                        simregismodel.setTransactionID(transactionNo);
//                    simregismodel.setExtraPara("n");
//
//                    return simregismodel;
//                } finally {
//
//                    try {
//
//                        dbConnectionPool.freeConnection(connection1);
//                        // release resources
//                        // dbConnectionPool.destroy();
//
//                        if (conntAuth != null) {
//                            conntAuth.close();
//                        }
//
//                        if (statementtAuth != null) {
//                            statementtAuth.close();
//                        }
//
//                        if (resultSettAuth != null) {
//                            resultSettAuth.close();
//                        }
//                    } catch (Exception e3) {
//
//                    }
//                }
//
//            } else {
//                simregismodel.setResultCode(GlobalParameter.error_upload_image);
//                simregismodel.setResultMsg(GlobalParameter.error_upload_image_msg);
////                    simregismodel.setTransactionID(transactionNo);
//                simregismodel.setExtraPara("Error Upload image");
//
//                return simregismodel;
//
//            }
//
//
//            //=======================================================================================================
//
//        } else {
//
//            simregismodel.setResultCode(GlobalParameter.error_non_authoritative_sign);
//            simregismodel.setResultMsg(GlobalParameter.error_non_authoritative_msg_sign);
////            simregismodel.setTransactionID(transactionNo);
//            simregismodel.setExtraPara("n");
//
//            return simregismodel;
//
//        }
//        return simregismodel;
//    }
//}
//
//
//
//
//
