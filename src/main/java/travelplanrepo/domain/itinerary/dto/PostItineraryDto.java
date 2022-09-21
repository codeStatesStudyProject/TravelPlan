package travelplanrepo.domain.itinerary.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import travelplanrepo.domain.itinerary.entity.Itinerary;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostItineraryDto {

    @NotNull
    private long number;

    @NotBlank
    private String title;

    private String link;

    private MultipartFile img;

    private String explanation;

    @NotNull
    private int day;

    public Itinerary toItinerary() {
        Itinerary itinerary = new Itinerary();

        itinerary.setNumber(number);
        itinerary.setTitle(title);
        itinerary.setLink(link);
        itinerary.setExplanation(explanation);
        itinerary.setDay(day);

        return itinerary;
    }
}
