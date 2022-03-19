package ca.ghostteam.springulart.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class HaircutDTO {
    private final Integer id;
    private final String imageURL;
    private final Integer price;
    private final String title;
    private final String estimatedTime;// check frontend
    private final String description;


}
