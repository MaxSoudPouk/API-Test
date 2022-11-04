package com.etl.kyc.etlkycsimregisterapi.uploadfile.controller;

import com.etl.kyc.etlkycsimregisterapi.uploadfile.exception.FileStorageException;
import com.etl.kyc.etlkycsimregisterapi.uploadfile.payload.UploadFileResponse;
import com.etl.kyc.etlkycsimregisterapi.uploadfile.property.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;


    private static HttpServletRequest request;

    public FileController() {
//		@Bean
//		public CommandLineRunner CommandLineRunnerBean() {
//			return (args) -> {
        //System.out.println("Querymobile afterregister Start");
//				threadRead threadRead = new threadRead();
//				threadRead.contextInitialized(null);
//			};
//		}
    }

    // ----------------------------------------------------------------------
    private static boolean isNullOrEmpty(String str) {
        // return true if empty
        return str == null || str.trim().isEmpty();
    }

    private static String getClientIp() {
        String remoteAddr = "";
        try {
            if (request != null) {
                try {
                    remoteAddr = request.getHeader("X-FORWARDED-FOR");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (remoteAddr == null || "".equals(remoteAddr)) {
                    remoteAddr = request.getRemoteAddr();
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return remoteAddr;
    }

    private void setRequest(HttpServletRequest request) {
        FileController.request = request;
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(
            @RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        // validate file
        if (file == null) {
            // throw error
            throw new FileStorageException("88888888888888888888888");
        }

        // validate size
//        if (file.getSize() > 1048576 * 2) {
//            // throw error
//            throw FileException.fileMaxSize();
//        }

        String contentType = file.getContentType();
        if (contentType == null) {
            // throw error
            throw new FileStorageException("error null ");
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if (!supportedTypes.contains(contentType)) {
            // throw error (unsupport)
            return new UploadFileResponse(fileName, null,
                    "UnSupport", file.getSize());
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        System.out.println(fileDownloadUri);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {


        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
