package etlkycdispayimage;

import java.awt.PageAttributes.MediaType;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.etl.kyc.etlkycsimregisterapi.changpassword.ChangePasswordModel;

import io.jsonwebtoken.io.IOException;

@RestController
public class etlkycdisplayimageController extends Thread {
public etlkycdisplayimageController() {

		//System.out.println("displayimage");

	}

//@GetMapping(value = "/image")
//public @ResponseBody byte[] getImage() throws IOException {
//    InputStream in = getClass()
//      .getResourceAsStream("/com/baeldung/produceimage/image.jpg");
//    return IOUtils.toByteArray(in);
//}

//@GetMapping("/v1/displayimages")
//@ResponseBody
//public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("jpg") boolean jpg) {
//    MediaType contentType = jpg ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
//    InputStream in = jpg ?
//      getClass().getResourceAsStream("/com/baeldung/produceimage/image.jpg") :
//      getClass().getResourceAsStream("/com/baeldung/produceimage/image.png");
//    return ResponseEntity.ok()
//      .contentType(contentType)
//      .body(new InputStreamResource(in));
//}
//
}
