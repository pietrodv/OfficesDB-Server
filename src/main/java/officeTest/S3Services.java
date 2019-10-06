package officeTest;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

// Not AUTO IMPLEMENTED by spring, therefore below methods are defined in S3ServicesIml
public interface S3Services {
    // The file downloaded will be a ByteArrayOutputStream type
    ByteArrayOutputStream downloadFile(String keyName);
    void uploadFile(String keyName, MultipartFile file);
    List<String> listFiles();
    void deleteFile(String keyName);
}
