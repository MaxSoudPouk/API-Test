package com.etl.kyc.etlkycsimregisterapi.displayimage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.etl.kyc.etlkycsimregisterapi.global.GlobalParameter;

import io.jsonwebtoken.io.IOException;


@RestController
public class displayimageController {
	
	
	BufferedImage bufferedImage;
	@RequestMapping(value = "/v1/displayimage", method = { RequestMethod.GET, RequestMethod.POST })
     public BufferedImage getImage(
            @RequestParam(defaultValue = "") String filename,
            @RequestParam(defaultValue = "") String keyid

//        	BufferedImage bufferedImage;
//        	@RequestMapping(value = "/v1/displayimage", method = { RequestMethod.GET, RequestMethod.POST })
//             public BufferedImage getImage(
//                    @RequestParam(defaultValue = "") String filename,
//                    @RequestParam(defaultValue = "") String keyid
                
        
     ) throws NoSuchAlgorithmException, java.io.IOException {
//		 displaymodel dispayimg = new displaymodel();
//		
		 if (filename.equals("")|| keyid.equals("")){

			 
			 return null;
			 
          	//  dispayimg.setResultCode(GlobalParameter.error_no_content);
          	 //  dispayimg.setResultMsg(GlobalParameter.error_no_content_msg); 
             //  return dispayimg;
       }
//           
//		 
//		// boolean view_img = false;
//		viewimage vimg = new viewimage();
//		
//		BufferedImage getimg =vimg.getImage(filename);
//		
//		
//		dispayimg.setImg(getimg);
//		
//  
//		//	 return dispayimg;
//		
		  
		
		if(keyid.equals("303ad702c386b05417a69426f1228ad3")) {
			
		 String FILE_PATH_ROOT="/home";
		// System.out.println("FILE_PATH_ROOT-======:: " + FILE_PATH_ROOT);
  	    // System.out.println("filename-======:: " + filename);
  	    // System.out.println("FILE_PATH_ROOT+filename-======::" + FILE_PATH_ROOT+filename);
		 bufferedImage = ImageIO.read(new File(FILE_PATH_ROOT+filename));
		//bufferedImage = ImageIO.read(new File("/home/kycimage/202247/person2029934356000153.png"))
		// /kycimage/202246/person2023366688213929.png
		 return bufferedImage;
	 }
		
		 return null;
	
		}
 
}
