//package com.etl.kyc.etlkycsimregisterapi.WebPortalSimRegister;
//
//
//import com.etl.kyc.etlkycsimregisterapi.db.Config;
//import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
//import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
//import com.etl.kyc.etlkycsimregisterapi.simregister.Uploadimage;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.JSONPointerException;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.json.JsonArray;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//@RestController
//public class RegistertestLOOP {
//
//    //private static final String PATTERN_FORMAT = "yyyy-MM-dd HH:mm:ss";
//    private static final String PATTERN_FORMAT = "HHmmss";
//    static Calendar cal = new GregorianCalendar(Locale.ROOT);
//    static int current_year = cal.get(Calendar.YEAR);
//    static int current_week = cal.get(Calendar.WEEK_OF_YEAR);
//    static String current_time = current_year + "" + current_week;
//    public static String linux_DIRECTORY = "/home/kyc_Organize/" + current_time + "/";
//    // public static String window_DIRECTORY = System.getProperty("C:\\kycimage\\image"+current_week+"\\");
//    public static String window_DIRECTORY = "C:\\kyc_Organize\\" + current_time + "\\";
//    private static HttpServletRequest request;
//    private final MappingJackson2HttpMessageConverter jsonConverter;
//    PreparedStatement pstmt;
//    ResultSet rs;
//    DatabaseConnectionPool dbConnectionPool;
//    //public static String windowpath=directory=C:\Users\lacasoub\myUser\Local\Temp
//    Connection connection1;
//
//    public RegistertestLOOP(MappingJackson2HttpMessageConverter jsonConverter) {
//        this.jsonConverter = jsonConverter;
//    }
//
//
//    @PostMapping("/v1/registerOrganization")
//    public WebPortalSimregisterModel Simregister(
////            @RequestBody List<JSONObject> list, HttpServletRequest request,
//            @RequestParam String payload,
//            @RequestParam(defaultValue = "") String sign,
//            @RequestParam() MultipartFile img_person,
//            @RequestParam() MultipartFile img_doc,
//            @RequestParam() MultipartFile img_sim,
//            @RequestParam() MultipartFile img_org_register
//
//    ) throws NoSuchAlgorithmException, IOException, JSONException, JSONPointerException {
//
//
//        List<Integer> listnumber = new ArrayList<>();
//        List<Integer> listnmae = new ArrayList<>();
//
//
////        List<String> list = new ArrayList<>();
//
//        JSONObject list1 = new JSONObject();
//        ArrayList<JsonArray> arr = new ArrayList<>();
//
//
//        WebPortalSimregisterModel simregismodel = new WebPortalSimregisterModel();
//
//
//        PreparedStatement pstmt;
//        ResultSet rs;
//        DatabaseConnectionPool dbConnectionPool = null;
//        Connection connection1 = null;
//        Statement statementtAuth = null;
//        ResultSet resultSettAuth = null;
//        Connection conntAuth = null;
////                    String querysql = "SELECT t.status_id,k.status_name from tb_subscriber_info t  INNER JOIN tb_status_info k \r\n"
////                            + "on t.status_id=k.status_id WHERE t.status_id in('1','2','4') and  t.msisdn='" + msisdn + "' order by t.reg_date desc LIMIT 1";
////
////                    try {
////
////                        dbConnectionPool = new DatabaseConnectionPool(
////                                Config.driverServr,
////                                Config.dburlServr,
////                                Config.dbUserNameServr,
////                                Config.dbPasswordServr);
////                        connection1 = dbConnectionPool.getConnection();
////                        pstmt = connection1.prepareStatement(querysql);
////                        rs = (ResultSet) pstmt.executeQuery();
////                        int rsfound = 0;
////
////                        String status_id = null;
////                        String status_name = null;
////                        while (rs != null && rs.next()) {
////                            rsfound++;
////                            status_id = rs.getString("status_id");
////                            status_name = rs.getString("status_name");
////
////
////                        }
////                        if (rsfound > 0) {
////                            simregismodel.setResultCode(GlobalParameter.fail_query_status);
////                            simregismodel.setResultMsg(GlobalParameter.fail_query_status_msg);
//////                        simregismodel.setTransactionID(transactionNo);
////                            simregismodel.setExtraPara("n");
////
////                            return simregismodel;
////
////                        }
////
////                    } catch (Exception e) {
////                        simregismodel.setResultCode(GlobalParameter.error_querymobile_status);
////                        simregismodel.setResultMsg(GlobalParameter.error_querymobile_status_msg);
//////                    simregismodel.setTransactionID(transactionNo);
////                        return simregismodel;
////
////                    }
////                    //=======================End check status================
//
//        boolean save_img_person = false;
//        boolean save_img_doc = false;
//        boolean save_sim = false;
//        boolean save_org = false;
//
//        String URL_person_linux = "";
//        String URL_doc_linux = "";
//        String URL_sim_linux = "";
//        String URL_org_linux = "";
//
//        try {
//
//            Uploadimage imgup = new Uploadimage();
//            boolean isLinux = System.getProperty("os.name").startsWith("Linux");
//
//
//            String PATH;
//            if (isLinux) {
//                PATH = linux_DIRECTORY;
//            } else {
//                PATH = window_DIRECTORY;
//            }
//
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.systemDefault());
//
//            Instant instant = Instant.now();
//            String timename = formatter.format(instant);
//
//
//            String img_person_name = "person" + "img_person_name" + timename + ".pdf";
//            String img_doc_name = "doc" + "img_doc_name" + timename + ".pdf";
//            String img_sim_name = "sim" + "img_sim_name" + timename + ".pdf";
//            String img_org_name = "org" + "img_org_name" + timename + ".pdf";
//
//            save_img_person = imgup.image_person(PATH, img_person_name, img_person);
//            save_img_doc = imgup.image_doc(PATH, img_doc_name, img_doc);
//            save_sim = imgup.image_sim(PATH, img_sim_name, img_sim);
//            save_org = imgup.img_org_register(PATH, img_org_name, img_org_register);
//            //=====================URL save to db===========================
//
//            //String URL_person_window = ServletUriComponentsBuilder.fromCurrentContextPath().path(PATH+"\\"+ img_person_name).build().toUriString();
//            URL_person_linux = "/kyc_Organize/" + current_time + "/" + img_person_name;
//            URL_doc_linux = "/kyc_Organize/" + current_time + "/" + img_doc_name;
//            URL_sim_linux = "/kyc_Organize/" + current_time + "/" + img_sim_name;
//            URL_org_linux = "/kyc_Organize/" + current_time + "/" + img_org_name;
//
//
//        } catch (Exception ex) {
//
//            simregismodel.setResultCode(GlobalParameter.error_upload_image);
//            simregismodel.setResultMsg(GlobalParameter.error_upload_image_msg);
//            simregismodel.setExtraPara("n");
//
//            return simregismodel;
//        }
//
//        if (save_img_person && save_img_doc && save_sim && save_org) {
//            try {
//                // =========================== JSON decode end =========================== //
//
////          Object o1 = JSONValue.parse(payload);
////          net.minidev.json.JSONObject jsonObj = (net.minidev.json.JSONObject) o1;
////
////          System.out.println("jsonObj ================== " + jsonObj);
//
//                JSONObject jObj = new JSONObject(payload);
////            JSONObject dataResult = jObj.getJSONObject("data");
//                JSONArray jArr = jObj.getJSONArray("orgdata1");
//
//
//                SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                formatter111.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
//
//
//                GenerateSignkey_sha256_web signkey_sha256 = new GenerateSignkey_sha256_web();
//                String userName = "";
//                String transactionNo = "";
//                String remark1 = "";
//                String extraParams = "";
//                String uuid = "";
//                String des_url = "/etllao.com/v1/webportalsimregister";
//
//
////                String serverSign = signkey_sha256.generateSignkey_sha256(
////                        userName,
////                        channel,
////                        transactionNo,
////                        msisdn1,
////                        remark1,
////                        extraParams,
////                        uuid,
////                        des_url);
////
////                System.out.println("serverSign=" + serverSign);
//                if (sign.length() != 64) {
//
//                    simregismodel.setResultCode(GlobalParameter.error_sign);
//                    simregismodel.setResultMsg(GlobalParameter.error_sign_msg);
//
//                    return simregismodel;
//
//                }
//
//                if (!sign.equals("36b128b5dada223c04779bd27687b3c17b375a5d19a2ee735507922af63bcaad")) {
//
//                    simregismodel.setResultCode(GlobalParameter.error_non_authoritative_sign);
//                    simregismodel.setResultMsg(GlobalParameter.error_non_authoritative_msg_sign);
//                    simregismodel.setExtraPara("n");
//
//                    return simregismodel;
//                }
//
//
//                for (int i = 0; i < jArr.length(); i++) {
//                    JSONObject innerObj = jArr.getJSONObject(i);
//                    System.out.println("innerObj =========== " + innerObj);
//
//                    String user_id = (String) innerObj.get("user_id");
//                    int msisdn = (int) innerObj.get("msisdn");
//                    String fname = (String) innerObj.get("fname");
//                    String lname = (String) innerObj.get("lname");
//                    int gender_id = (int) innerObj.get("gender_id");
//                    String birthday = (String) innerObj.get("birthday");
//                    int document_id = (int) innerObj.get("document_id");
//                    String document_no = (String) innerObj.get("document_no");
//                    String docno_org = (String) innerObj.get("docno_org");
//                    String occupation = (String) innerObj.get("occupation");
//                    String email = (String) innerObj.get("email");
//                    int province_id = (int) innerObj.get("province_id");
//                    int district_id = (int) innerObj.get("district_id");
//                    int village_id = (int) innerObj.get("village_id");
//                    String address_reg = (String) innerObj.get("address_reg");
//                    String latitude = (String) innerObj.get("latitude");
//                    String longtitude = (String) innerObj.get("longtitude");
//                    String devicename = (String) innerObj.get("devicename");
//                    String osversion = (String) innerObj.get("osversion");
//                    String remark = (String) innerObj.get("remark");
//
//
//                    System.out.println("user_id : " + user_id);
//                    System.out.println("msisdn : " + msisdn);
//                    System.out.println("fname : " + fname);
//                    System.out.println("lname : " + lname);
//                    System.out.println("gender_id : " + gender_id);
//                    System.out.println("birthday : " + birthday);
//                    System.out.println("document_id : " + document_id);
//                    System.out.println("document_no : " + document_no);
//                    System.out.println("docno_org : " + docno_org);
//                    System.out.println("occupation : " + occupation);
//                    System.out.println("email : " + email);
//                    System.out.println("province_id : " + province_id);
//                    System.out.println("district_id : " + district_id);
//                    System.out.println("village_id : " + village_id);
//                    System.out.println("address_reg : " + address_reg);
//                    System.out.println("occupation : " + occupation);
//
//
//                    // =========================== JSON decode end =========================== //
//
//
//                    if (user_id.equals("")
//                            || fname.equals("")
//                            || lname.equals("")
//                            || birthday.equals("")
//                            || document_no.equals("")
//                            || docno_org.equals("")
//                            || occupation.equals("")
//                            || email.equals("")
//                            || address_reg.equals("")
//                    ) {
//
//                        // System.out.println("token=" + token);
//                        simregismodel.setResultCode(GlobalParameter.error_no_content);
//                        simregismodel.setResultMsg(GlobalParameter.error_no_content_msg);
//                        simregismodel.setExtraPara("Some Parameter Null");
//
//                        return simregismodel;
//
//                    }
//
//
////                        System.out.println(URL_person_linux);
////                        System.out.println(URL_doc_linux);
////                        System.out.println(URL_sim_linux);
////                        System.out.println(URL_org_linux);
//
//
//                    // =========================== check status =========================== //
//
//
//                    String querysql = "SELECT t.status_id,k.status_name from tb_subscriber_info t  INNER JOIN tb_status_info k \r\n"
//                            + "on t.status_id=k.status_id WHERE t.status_id in('1','2','4') and  t.msisdn='" + msisdn + "' order by t.reg_date desc LIMIT 1";
//
//                    try {
//
//                        dbConnectionPool = new DatabaseConnectionPool(
//                                Config.driverServr,
//                                Config.dburlServr,
//                                Config.dbUserNameServr,
//                                Config.dbPasswordServr);
//                        connection1 = dbConnectionPool.getConnection();
//                        pstmt = connection1.prepareStatement(querysql);
//                        rs = pstmt.executeQuery();
//                        int rsfound = 0;
//
//                        String status_id = null;
//                        String status_name = null;
//                        while (rs != null && rs.next()) {
//                            rsfound++;
//                            status_id = rs.getString("status_id");
//                            status_name = rs.getString("status_name");
//
//
//                        }
////                        listnumber.add(Integer.valueOf(msisdn));
////                        listnmae.add(Integer.valueOf(rsfound));
//
////                        JSONObject jsonArray = new JSONObject();
////
////                        // Add key-value pairs to the object
////                        jsonArray.put("element 1", msisdn);
////                        jsonArray.put("element 2", rsfound);
//
////
////                        JSONArray jsonArray = new JSONArray();
////                        jsonArray.put("msisdn : " + msisdn);
////                        jsonArray.put("stt : " + rsfound);
//
//
//
//
//                        list1.put("element 1", msisdn);
//                        list1.put("element 2", rsfound);
//                        System.out.println("jsonObject ======== " + list1);
//
//
//                        if (rsfound > 0) {
//                            continue;
//                        }
//
//                    } catch (Exception e) {
//                        simregismodel.setResultCode(GlobalParameter.error_querymobile_status);
//                        simregismodel.setResultMsg(GlobalParameter.error_querymobile_status_msg);
//
//                        return simregismodel;
//
//                    }
//
//
//                    // =========================== End check status =========================== //
//
//                    // =================================== default =================================== //
//                    int simtype_id = 1;
//                    int channel_id = 3;
//                    int status_id = 2;
//                    int sub_type_id = 2;
//                    //==================================== default =================================== //
//
//                    String sql = " insert into tb_subscriber_info (msisdn, fname, lname, gender_id, birthday, occupation, simtype_id,"
//                            + "province_id, district_id, village_id, latitude, longtitude, docno_org,"
//                            + " channel_id, status_id, sub_type_id, document_id, document_no, email ,"
//                            + " img_person_path, img_doc_path, img_sim_path, img_org_register, address_reg, user_id, devicename, osversion, remark)"
//                            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//                    try {
//                        dbConnectionPool = new DatabaseConnectionPool(
//                                Config.driverServr,
//                                Config.dburlServr,
//                                Config.dbUserNameServr,
//                                Config.dbPasswordServr);
//                        connection1 = dbConnectionPool.getConnection();
//                        PreparedStatement statement = connection1.prepareStatement(sql);
//                        statement.setInt(1, msisdn);
//                        statement.setString(2, fname);
//                        statement.setString(3, lname);
//                        statement.setInt(4, gender_id);
//                        statement.setString(5, birthday);
//                        statement.setString(6, occupation);
//                        statement.setInt(7, simtype_id);
//                        statement.setInt(8, province_id);
//                        statement.setInt(9, district_id);
//                        statement.setInt(10, village_id);
//                        statement.setString(11, latitude);
//                        statement.setString(12, longtitude);
//                        statement.setString(13, docno_org);
//                        statement.setInt(14, channel_id);
//                        statement.setInt(15, status_id);
//                        statement.setInt(16, sub_type_id);
//                        statement.setInt(17, document_id);
//                        statement.setString(18, document_no);
//                        statement.setString(19, email);
//                        statement.setString(20, URL_person_linux);
//                        statement.setString(21, URL_doc_linux);
//                        statement.setString(22, URL_sim_linux);
//                        statement.setString(23, URL_org_linux);
//                        statement.setString(24, address_reg);
//                        statement.setString(25, user_id);
//                        statement.setString(26, devicename);
//                        statement.setString(27, osversion);
//                        statement.setString(28, remark);
//
//
//                        int rowsUpdated = statement.executeUpdate();
//
////                        list.add("IphoneNumber=" + msisdn + "Name=" + fname);
//
//
//                        // System.out.println("sql====="+ sql);
//
//
//                    } catch (SQLException ex) {
//                        simregismodel.setResultCode(GlobalParameter.error_insert_sim_register);
//                        simregismodel.setResultMsg(GlobalParameter.error_insert_sim_register_smg);
//
//                        simregismodel.setExtraPara("n");
//
//                        return simregismodel;
//                    } finally {
//
//                        try {
//
//                            dbConnectionPool.freeConnection(connection1);
//                            // release resources
//                            // dbConnectionPool.destroy();
//
//                            if (conntAuth != null) {
//                                conntAuth.close();
//                            }
//
//                            if (statementtAuth != null) {
//                                statementtAuth.close();
//                            }
//
//                            if (resultSettAuth != null) {
//                                resultSettAuth.close();
//                            }
//                        } catch (Exception e3) {
//
//                        }
//                    }
//
//
//// ======================================================================================================= //
//
//                }
//
//
////                System.out.println("arr ======= " + arr);
//////                System.out.println("ss ======= " + ss);
////                System.out.println("arr type ======= " + arr.getClass());
////
////
////
////                System.out.println("ss ======= " + list1);
////                System.out.println("ss ======= " + list1.getClass());
////                System.out.println("ss ======= " + list1.get(1));
//
////                JSONArray jsonArray = new JSONArray();
////                jsonArray.put("msisdn : " + list1);
////
////                System.out.println("jsonArray ======= " + jsonArray);
//
//
//                System.out.println("****************************************************************");
//
//                System.out.println("An existing user was updated successfully!");
//
//
//                simregismodel.setResultCode(GlobalParameter.error_ok_success);
//                simregismodel.setResultMsg(GlobalParameter.error_ok_success_msg);
//                simregismodel.setExtraPara("");
////                simregismodel.setNumberSuccess(list);
//                //.replace("]", "").replace("[", "")
//
//                return simregismodel;
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        return null;
//    }
//}
//
//
//
