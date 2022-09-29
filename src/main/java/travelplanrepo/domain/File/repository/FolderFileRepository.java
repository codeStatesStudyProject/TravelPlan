package travelplanrepo.domain.File.repository;

import org.springframework.web.multipart.MultipartFile;
import travelplanrepo.domain.File.domain.File;

import java.io.IOException;
import java.util.UUID;

public class FolderFileRepository implements FileRepository {

    @Override
    public File saveFile(MultipartFile multipartFile, String path) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = getStoreFileName(originalFilename);
        String fullPath = path + storeFileName;

        multipartFile.transferTo(new java.io.File(fullPath));
        return new File(originalFilename, fullPath);
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
}
