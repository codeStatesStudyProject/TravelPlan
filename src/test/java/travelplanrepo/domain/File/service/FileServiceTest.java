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

    @Test
    @DisplayName("storeFile 테스트 성공")
    void storeFile_Success_Test() throws IOException {
        //given

        //when

        //then
    }
}