package travelplanrepo.domain.File.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import travelplanrepo.domain.File.service.FileService;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
public class FileController {

    @Value("${file.profileImg}")
    private String profileImgPath;

    @Value("${file.boardImg}")
    private String boardImgPath;

    @Value("${file.scheduleImg}")
    private String scheduleImgPath;

    private final FileService fileService;

    @GetMapping("/profileImg/{imgName}")
    public ResponseEntity<Resource> getProfileImg(@PathVariable String imgName) throws MalformedURLException {
        String fullPath = fileService.getFullPath(profileImgPath, imgName);
        UrlResource urlResource = new UrlResource("file:" + fullPath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .body(urlResource);
    }

    @GetMapping("/boardImg/{imgName}")
    public ResponseEntity<Resource> getBoardImg(@PathVariable String imgName) throws MalformedURLException {
        String fullPath = fileService.getFullPath(boardImgPath, imgName);
        UrlResource urlResource = new UrlResource("file:" + fullPath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .body(urlResource);
    }

    @GetMapping("/scheduleImg/{imgName}")
    public ResponseEntity<Resource> getScheduleImg(@PathVariable String imgName) throws MalformedURLException {
        String fullPath = fileService.getFullPath(scheduleImgPath, imgName);
        UrlResource urlResource = new UrlResource("file:" + fullPath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .body(urlResource);
    }
}
