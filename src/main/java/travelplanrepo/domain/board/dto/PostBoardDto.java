package travelplanrepo.domain.board.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import travelplanrepo.domain.Tag.dto.TagReq;
import travelplanrepo.domain.Tag.entity.Tag;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.domain.board.entity.BoardTag;
import travelplanrepo.domain.itinerary.dto.PostItineraryDto;
import travelplanrepo.domain.itinerary.entity.Itinerary;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostBoardDto {

    @NotBlank
    private String title;

    private String preface;

    private MultipartFile thumbnail;

    private List<@Valid PostItineraryDto> itineraryList = new ArrayList<>();

    private List<@Valid TagReq> tagList = new ArrayList<>();

    public Board toBoard() {
        Board board = Board.builder()
                .title(title)
                .preface(preface)
                .itineraryList(new ArrayList<>())
                .boardTagList(new ArrayList<>())
                .build();

        addItinerary(board);
        addBoardTag(board);

        return board;
    }

    public void addBoardTag(Board board) {
        for (TagReq tagReq : tagList) {
            Tag tag = tagReq.toTag();
            new BoardTag(board, tag);
        }
    }

    public void addItinerary(Board board) {
        for (PostItineraryDto postItineraryDto : itineraryList) {
            Itinerary itinerary = postItineraryDto.toItinerary();
            itinerary.chaneBoard(board);
        }
    }
}
