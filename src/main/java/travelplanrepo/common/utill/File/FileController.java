package travelplanrepo.common.utill.File;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    private final FileProcessor fileProcessor;

    @GetMapping("/profileImg/{imgName}")
    public Resource getProfileImg(@PathVariable String imgName) throws MalformedURLException {
        String fullPath = fileProcessor.getFullPath(profileImgPath, imgName);
        return new UrlResource("file:" + fullPath);
    }

    @GetMapping("/boardImg/{imgName}")
    public Resource getBoardImg(@PathVariable String imgName) throws MalformedURLException {
        String fullPath = fileProcessor.getFullPath(boardImgPath, imgName);
        return new UrlResource("file:" + fullPath);
    }

    @GetMapping("/scheduleImg/{imgName}")
    public Resource getScheduleImg(@PathVariable String imgName) throws MalformedURLException {
        String fullPath = fileProcessor.getFullPath(scheduleImgPath, imgName);
        return new UrlResource("file:" + fullPath);
    }
}
