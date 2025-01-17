package com.etl.kyc.etlkycsimregisterapi.displayimage;

import com.etl.kyc.etlkycsimregisterapi.db.DatabaseConnectionPool;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.NoSuchAlgorithmException;

@RestController
public class displayimageController {

    public static HttpServletRequest request;
    DatabaseConnectionPool dbConnectionPool;

    BufferedImage bufferedImage;

    @RequestMapping(value = "/v1/displayimage", method = {
            RequestMethod.GET,
            RequestMethod.POST},
            produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage getImage(@RequestParam(defaultValue = "") String filename,
                                  @RequestParam(defaultValue = "") String keyid

//        	BufferedImage bufferedImage;
//        	@RequestMapping(value = "/v1/displayimage", method = { RequestMethod.GET, RequestMethod.POST })
//             public BufferedImage getImage(
//                    @RequestParam(defaultValue = "") String filename,
//                    @RequestParam(defaultValue = "") String keyid

    ) throws NoSuchAlgorithmException, java.io.IOException {
//		 displaymodel dispayimg = new displaymodel();
//		
        if (filename.equals("") || keyid.equals("")) {

            return null;

            // dispayimg.setResultCode(GlobalParameter.error_no_content);
            // dispayimg.setResultMsg(GlobalParameter.error_no_content_msg);
            // return dispayimg;
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

        if (keyid.equals("303ad702c386b05417a69426f1228ad3")) {

            String FILE_PATH_ROOT = "C:/";

            // System.out.println("FILE_PATH_ROOT-======:: " + FILE_PATH_ROOT);
            // System.out.println("filename-======:: " + filename);
            // System.out.println("FILE_PATH_ROOT+filename-======::" +
            // FILE_PATH_ROOT+filename);

            bufferedImage = ImageIO.read(new File(FILE_PATH_ROOT + filename).getAbsoluteFile());
            // bufferedImage = ImageIO.read(new
            // File("/home/kycimage/202247/person2029934356000153.png"));
            // bufferedImage = ImageIO.read(new
            // File("C:\\kycimage\\202248\\sim2022904900135804.png").getAbsoluteFile());
            // /kycimage/202246/person2023366688213929.png
            return bufferedImage;
        }

        return null;

    }

}
