package travelplanrepo.domain.itinerary.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import travelplanrepo.domain.itinerary.entity.Itinerary;

@Data
public class PostItineraryDto {
    private long number;
    private String content;
    private String link;
    private MultipartFile img;
    private String explanation;
    private int day;

    public Itinerary toItinerary() {
        Itinerary itinerary = new Itinerary();

        itinerary.setNumber(number);
        itinerary.setContent(content);
        itinerary.setLink(link);
        itinerary.setExplanation(explanation);
        itinerary.setDay(day);

        return itinerary;
    }
}
