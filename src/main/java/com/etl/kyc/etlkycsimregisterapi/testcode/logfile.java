package com.etl.kyc.etlkycsimregisterapi.testcode;

import com.etl.kyc.etlkycsimregisterapi.smsotp.otpModel;
import com.sun.tools.javac.Main;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@RestController
public class logfile extends Thread {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    @PostMapping("/v1/123")
    public otpModel otp(
            @RequestParam(defaultValue = "") String date,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String comment,
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String transactionNo
    )  {
        otpModel model = new otpModel();

        try {
            // Prompt the user for a string input

            String str = date + "       " + name + "     " + comment + "     " + email + "       " + transactionNo;

            System.out.println(str);

            // Write the string to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\santi\\Desktop\\logs\\myfile.txt", true));
            writer.write(str);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }
}
