package incircle.utils;

import java.io.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Julian on 12/12/2015.
 */
public class CommonUtils {
    public static void uploadFile(MultipartFile file, String filePath) throws Exception{
        if (!file.isEmpty()) try {
            byte[] bytes = file.getBytes();
            File newFile = new File(filePath);
            FileOutputStream fos = new FileOutputStream(newFile);
            BufferedOutputStream stream = new BufferedOutputStream(fos);
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            throw new Exception("File cannot be uploaded.");
        }
        else {
            throw new Exception("No file to upload.");
        }
    }
}
