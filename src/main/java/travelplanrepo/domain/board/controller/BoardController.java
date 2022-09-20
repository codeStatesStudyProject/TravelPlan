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
import travelplanrepo.domain.File.domain.File;
import travelplanrepo.domain.File.service.FileService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    @Value("${file.boardImg}")
    private String boardImgPath;

    @Value("${file.itineraryImg}")
    private String itineraryImgPath;

    private final BoardService boardService;
    private final FileService fileService;

    @PostMapping("/board")
    public String postBoard(@LoginAccountId Long accountId, @ModelAttribute PostBoardDto postBoardDto) throws IOException {
        Board board = postBoardDto.toBoard();

        storeBoardImg(postBoardDto, board);

        boardService.signBoard(accountId, board);

        return "success board created";
    }

    private void storeBoardImg(PostBoardDto postBoardDto, Board board) throws IOException {
        File thumbnail = fileService.storeFile(postBoardDto.getThumbnail(), boardImgPath);
        board.setThumbnail(thumbnail);

        List<PostItineraryDto> itineraryList = postBoardDto.getItineraryList();
        List<Itinerary> boardItineraryList = board.getItineraryList();
        for (int i = 0; i < itineraryList.size(); i++) {
            PostItineraryDto postItineraryDto = itineraryList.get(i);
            File img = fileService.storeFile(postItineraryDto.getImg(), itineraryImgPath);

            Itinerary itinerary = boardItineraryList.get(i);
            itinerary.setImg(img);
        }
    }
}
