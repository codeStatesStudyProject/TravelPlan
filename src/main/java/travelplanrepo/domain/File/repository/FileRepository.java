package travelplanrepo.domain.File.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import travelplanrepo.domain.File.domain.File;

import java.io.IOException;

@Repository
public interface FileRepository {
    File saveFile(MultipartFile multipartFile, String path) throws IOException;
}
