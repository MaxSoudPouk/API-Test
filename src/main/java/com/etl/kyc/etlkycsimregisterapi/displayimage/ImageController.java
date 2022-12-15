package com.etl.kyc.etlkycsimregisterapi.displayimage;//package com.etl.kyc.etlkycsimregisterapi.displayimage;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.etl.kyc.etlkycsimregisterapi.db.Config;
//import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
//import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;
//import com.etl.kyc.etlkycsimregisterapi.simregister.SimregisterModel;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.security.NoSuchAlgorithmException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//@RestController
//public class ImageController extends Thread {
//	 public ImageController() {
//			
//			System.out.println("Display ddddd Start");
//			
//		}
//	 
//	 
//	// @GetMapping(path = "v1/displayimage", produces = "image/png")
//	 @PostMapping("/v1/displayimage")
//     public display_img_model displayimage(
//            @RequestParam(defaultValue = "") String msisdn,
//             @RequestParam(defaultValue = "") String userid,
//             @RequestParam(defaultValue = "") String img_person
//           //  @RequestParam(defaultValue = "") String img_doc,
//           //  @RequestParam(defaultValue = "") String img_sim
//           
//                   
//     ) throws NoSuchAlgorithmException, java.io.IOException {
//   	  
// 
//   	 // System.out.println(sign.length());
//		// System.out.println("userid=" + userid);
//   	  
//		 display_img_model dispayimg = new display_img_model();
//
////               if (userid.equals("")|| msisdn.equals("") || img_person.isEmpty()||img_doc.isEmpty()||img_sim.isEmpty()
////                 ) {
////
////                  
////            	   dispayimg.setResultCode(GlobalParameter.error_no_content);
////            	   dispayimg.setResultMsg(GlobalParameter.error_no_content_msg);
////          
////                  return dispayimg;
////
////         }
////               
//            
//              //===================check number status================= 
//		    	  
//		    	  java.sql.PreparedStatement pstmt;
//					ResultSet rs;
//					DatabaseConnectionPool dbConnectionPool = null;
//					Connection connection1 = null;
//					String strRetunr = null;
//					Statement statementtAuth = null;
//					ResultSet resultSettAuth = null;
//					Connection conntAuth = null;
//					String dbuser_id=null;
//					String dbmsisdn=null;
//					String dbpersonpath=null;
//					String dbdocpath=null;
//					String dbsimpath=null;
//					
//								String querysql="SELECT t.user_id , t.msisdn,img_person_path,img_doc_path,img_sim_path from tb_subscriber_info t where t.user_id='"+userid+"' and t.msisdn='"+msisdn+"' and t.status_id not in('1','2','4') order by t.reg_date desc LIMIT 1";
//					try {
//						
//						dbConnectionPool = new DatabaseConnectionPool(Config.driverServr, Config.dburlServr, Config.dbUserNameServr,
//								Config.dbPasswordServr);
//						connection1 = dbConnectionPool.getConnection();
//						pstmt = connection1.prepareStatement(querysql);
//						rs = (ResultSet) pstmt.executeQuery();
//						int rsfound=0;
//						
//						
//						while (rs != null && rs.next()) {
//						rsfound++;
//							dbuser_id = rs.getString("user_id");
//							dbmsisdn = rs.getString("msisdn");
//							dbpersonpath = rs.getString("img_person_path");
//							dbdocpath = rs.getString("img_doc_path");
//							dbsimpath = rs.getString("img_sim_path");
//							//System.out.println("status_id-======:: " + status_id);
//							
//							System.out.println("dbsimpath-======:: " + dbsimpath);
//				
//						} 
//                    //if((dbuser_id.equals(userid)) && (dbmsisdn.equals(msisdn)) && (dbpersonpath.equals(img_person)) && (dbdocpath.equals(img_doc)) && (dbsimpath.equals(img_sim)) ) {
//                    	 if((dbuser_id!=userid) ) {
//  
//                    
//                    	System.out.println("dispayimg-======:: " + dispayimg);
//                    	
//                    
//                    	//	displayimageservice simg = new displayimageservice();
//                    		
//                    		
//                    		
//                    		BufferedImage imgperson= simg.getimage_person(img_person);
//                    		//BufferedImage imgdoc= simg.getimage_doc(img_doc);
//                   		//BufferedImage imgsim= simg.getimage_sim(img_sim);
//                    		
//                    		 //=====================
//                    		
//                    	
//                       	  // dispayimg.setImageperson(imgperson);
//                       	 //  dispayimg.setImagedoc(imgdoc);
//                       	 //  dispayimg.setImagesim(imgsim);
//                    		 
//                    		return dispayimg;
//						}
//		    	  
//					} catch (Exception e) {
//						dispayimg.setResultCode(GlobalParameter.error_querymobile_status);
//						dispayimg.setResultMsg(GlobalParameter.error_querymobile_status_msg);
//						
//						return dispayimg;
//					
//					} 
//					 //=======================End check status================
//  
//               
//			return dispayimg;
//
//	 }
//	 
//	 
//	
////    @GetMapping(path = "/image", produces = "image/png")
////    public BufferedImage image() throws Exception {
////        BufferedImage bufferedImage = ImageIO.read(new File("C:\\kycimage\\202246\\doc2028044486111258.png"));
////        return bufferedImage;
////    }
//    
//////	 @GetMapping(path = "/kkkkkkk", produces = "image/png")
//////	 public BufferedImage getimage(String img_person) throws Exception {
//////	       BufferedImage buffperson = ImageIO.read(new File("C:\\kycimage\\202246\\doc2028044486111258.png"));
//////	      BufferedImage buffdoc = ImageIO.read(new File("C:\\kycimage\\202246\\doc2028044486111258.png"));
//////	      return buffperson ;
//////	      
//////	   }
////	 
////	 
//
//	
//    
//}
//
//
