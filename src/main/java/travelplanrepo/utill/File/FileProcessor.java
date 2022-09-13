package travelplanrepo.utill.File;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class FileProcessor {

    public File storeFile(MultipartFile multipartFile, String path) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = getStoreFileName(originalFilename);
        String fullPath = getFullPath(path, storeFileName);

        multipartFile.transferTo(new java.io.File(fullPath));
        return new File(originalFilename, storeFileName, fullPath);
    }

    private String getStoreFileName(String originalFilename) {
        UUID uuid = UUID.randomUUID();
        String ext = getExt(originalFilename);
        return uuid + "." + ext;
    }

    private static String getExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    public String getFullPath(String path, String storeFileName) {
        return path + storeFileName;
    }
}
