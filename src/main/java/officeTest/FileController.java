package officeTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/office")
public class FileController {
    @Autowired
    S3Services s3Services;

    @PostMapping("/file/upload")
    public String uploadMultipartFile(@RequestParam("keyname") String keyName, @RequestParam("uploadfile") MultipartFile file) {
        s3Services.uploadFile(keyName, file);
        return "Upload Successfully. -> KeyName = " + keyName;
    }

    //List ALL Files
    @GetMapping("/files")
    public List<String> listAllFiles(){
        return s3Services.listFiles();
    }

    private MediaType contentType(String keyname) {
        String[] arr = keyname.split("\\.");
        String type = arr[arr.length-1];
        switch(type) {
            case "txt": return MediaType.TEXT_PLAIN;
            case "png": return MediaType.IMAGE_PNG;
            case "jpg": return MediaType.IMAGE_JPEG;
            default: return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    // TODO: fix this
    // Download Files
    @GetMapping("/file")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String keyname) {
        ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(keyname);

        return ResponseEntity.ok()
                .contentType(contentType(keyname))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + keyname + "\"")
                .body(downloadInputStream.toByteArray());
    }

    @DeleteMapping("/file/delete/{keyname}")
    public String uploadMultipartFile(@PathVariable String keyname) {
        try {
            s3Services.deleteFile(keyname);
        }catch(Exception e) {
            return "Cannot Delete File -> Keyname = " + keyname;
        }
        return "Delete Successfully -> Keyname = " + keyname;
    }
}
