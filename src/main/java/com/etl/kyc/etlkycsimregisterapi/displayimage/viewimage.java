package com.etl.kyc.etlkycsimregisterapi.displayimage;


import io.jsonwebtoken.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class viewimage {


    public BufferedImage getImage(String filename) throws IOException, java.io.IOException {

        // System.out.println(" UPLOAD_DIRECTORY========: " + UPLOAD_DIRECTORY);
        String FILE_PATH_ROOT = "/home";
        System.out.println("FILE_PATH_ROOT-======:: " + FILE_PATH_ROOT);
        System.out.println("filename-======:: " + filename);
        System.out.println("FILE_PATH_ROOT+filename-======::" + FILE_PATH_ROOT + filename);
        // BufferedImage bufferedImage = ImageIO.read(new File(FILE_PATH_ROOT+filename));
		// /kycimage/202246/person2023366688213929.png
        return ImageIO.read(new File("/home/kycimage/202247/person2029934356000153.png"));

    }

}


