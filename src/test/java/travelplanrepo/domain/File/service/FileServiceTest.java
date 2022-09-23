package travelplanrepo.domain.File.service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import travelplanrepo.domain.File.domain.File;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class FileServiceTest {

    FileService fileService = new FileService();
    String profileImgPath = "/Users/hipo/Desktop/TravelPlanRepo/src/test/resources/static/testImg/";

    @Test
    @DisplayName("storeFile 테스트 성공")
    void storeFile_Success_Test() throws IOException {
        //given
        String originalFilename = "testFilename.jpeg";
        MockMultipartFile imgFile = new MockMultipartFile("image", originalFilename,
                "image/jpeg", new FileInputStream(profileImgPath + "default.jpeg"));

        //when
        File storeFile = fileService.storeFile(imgFile, profileImgPath);

        int pos = storeFile.getFullPath().lastIndexOf("/");
        String storeFilePath = storeFile.getFullPath().substring(0, pos + 1);
        java.io.File foundFile = new java.io.File(storeFile.getFullPath());

        //then
        assertThat(originalFilename).isEqualTo(storeFile.getFileName());
        assertThat(profileImgPath).isEqualTo(storeFilePath);
        assertThat(foundFile.exists()).isTrue();

        foundFile.delete();
    }
}