package travelplanrepo.domain.board.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.domain.itinerary.dto.PostItineraryDto;
import travelplanrepo.domain.itinerary.entity.Itinerary;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostBoardDto {

    private String title;
    private String preface;
    private MultipartFile thumbnail;
    private List<PostItineraryDto> itineraryList = new ArrayList<>();

    public Board toBoard() {
        Board board = Board.builder()
                .title(title)
                .preface(preface)
                .itineraryList(new ArrayList<>())
                .build();

        addItinerary(board);

        return board;
    }

    public void addItinerary(Board board) {
        for (PostItineraryDto postItineraryDto : itineraryList) {
            Itinerary itinerary = postItineraryDto.toItinerary();
            itinerary.chaneBoard(board);
        }
    }
}
