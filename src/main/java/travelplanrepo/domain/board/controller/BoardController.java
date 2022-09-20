package travelplanrepo.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import travelplanrepo.domain.board.dto.PostBoardDto;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.domain.board.service.BoardService;
import travelplanrepo.domain.itinerary.dto.PostItineraryDto;
import travelplanrepo.domain.itinerary.entity.Itinerary;
import travelplanrepo.global.security.argumentresolver.LoginAccountId;
import travelplanrepo.domain.File.File;
import travelplanrepo.domain.File.FileProcessor;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    @Value("${file.boardImg}")
    private String boardImgPath;

    @Value("${file.scheduleImg}")
    private String scheduleImgPath;

    private final BoardService boardService;
    private final FileProcessor fileProcessor;

    @PostMapping("/board")
    public String postBoard(@LoginAccountId Long accountId, @ModelAttribute PostBoardDto postBoardDto) throws IOException {
        Board board = postBoardDto.toBoard();
        File thumbnail = fileProcessor.storeFile(postBoardDto.getThumbnail(), boardImgPath);
        board.setThumbnail(thumbnail);

        List<PostItineraryDto> itineraryList = postBoardDto.getItineraryList();
        List<Itinerary> boardItineraryList = board.getItineraryList();
        for (int i = 0; i < itineraryList.size(); i++) {
            PostItineraryDto postItineraryDto = itineraryList.get(i);
            File img = fileProcessor.storeFile(postItineraryDto.getImg(), scheduleImgPath);

            Itinerary itinerary = boardItineraryList.get(i);
            itinerary.setImg(img);
        }

        boardService.signBoard(accountId, board);
        return "success board created";
    }
}
