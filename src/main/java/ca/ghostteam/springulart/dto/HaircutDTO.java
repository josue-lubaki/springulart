package ca.ghostteam.springulart.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class HaircutDTO {
    private Integer id;
    private String imageURL;
    private Integer price;
    private String title;
    private String estimatedTime;// check frontend
    private String description;
}
