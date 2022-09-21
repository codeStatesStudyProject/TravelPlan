package travelplanrepo.domain.File.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import travelplanrepo.domain.File.domain.File;

import java.io.IOException;
import java.util.UUID;

@Component
public class FileService {

    public File storeFile(MultipartFile multipartFile, String path) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        verifiedFilename(originalFilename);
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

    private void verifiedFilename(String filename) {
        if (filename == null) {
            throw new IllegalStateException("잘못된 형식의 Filename 입니다.");
        }

        boolean matches = filename.matches("^[a-zA-Zㄱ-ㅎ가-힣-_]+\\.(jpg|JPG|png|PNG|jpeg|JPEG)$");
        if (!matches) {
            throw new IllegalStateException("잘못된 형식의 Filename 입니다.");
        }
    }
}
