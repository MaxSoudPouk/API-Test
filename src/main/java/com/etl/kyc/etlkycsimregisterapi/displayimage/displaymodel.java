package com.etl.kyc.etlkycsimregisterapi.displayimage;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import io.jsonwebtoken.io.IOException;

public class displaymodel {
	private String resultCode;
	private String resultMsg;
	BufferedImage img;
	
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
//	
//	
//	public BufferedImage getImage(String filename) throws IOException, java.io.IOException {
//        
//		 // System.out.println(" UPLOAD_DIRECTORY========: " + UPLOAD_DIRECTORY);
//		 String FILE_PATH_ROOT="/home";
//		 System.out.println("FILE_PATH_ROOT-======:: " + FILE_PATH_ROOT);
// 	     System.out.println("filename-======:: " + filename);
// 	     System.out.println("FILE_PATH_ROOT+filename-======::" + FILE_PATH_ROOT+filename);
// 	    // BufferedImage bufferedImage = ImageIO.read(new File(FILE_PATH_ROOT+filename));
// 	    img = ImageIO.read(new File("/home/kycimage/202247/person2029934356000153.png"));
//		// /kycimage/202246/person2023366688213929.png
//		return img;
//		
//		
//		
//	 }
	
}
