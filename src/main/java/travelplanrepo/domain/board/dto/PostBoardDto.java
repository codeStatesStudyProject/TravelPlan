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
        Board board = new Board();
        board.setTitle(title);
        board.setPreface(preface);
        addItinerary(board);

        return board;
    }

    public void addItinerary(Board board) {
        List<Itinerary> boardItineraryList = board.getItineraryList();
        for (int i = 0; i < itineraryList.size(); i++) {
            PostItineraryDto postItineraryDto = itineraryList.get(i);
            Itinerary itinerary = postItineraryDto.toItinerary();

            boardItineraryList.add(itinerary);
            Itinerary boardItinerary = boardItineraryList.get(i);
            boardItinerary.setBoard(board);
        }
    }
}
