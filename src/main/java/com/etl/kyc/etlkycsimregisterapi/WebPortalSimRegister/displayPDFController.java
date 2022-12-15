package com.etl.kyc.etlkycsimregisterapi.WebPortalSimRegister;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;


@RestController
public class displayPDFController {

    @GetMapping(
            value = "/v1/DisplayPDF",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getTermsConditions(
            @RequestParam(defaultValue = "") String filename,
            @RequestParam(defaultValue = "") String keyid
    ) throws Exception {

        if (filename.equals("") || keyid.equals("")) {

            return null;
        }

        if (keyid.equals("303ad702c386b05417a69426f1228ad3")) {

            File file = new File("C:" + filename);

            System.out.println(file);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        }
        return null;
    }
}
