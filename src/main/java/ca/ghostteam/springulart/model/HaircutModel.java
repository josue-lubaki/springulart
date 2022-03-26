package ca.ghostteam.springulart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class HaircutModel {
    private String id;
    private String imageURL;
    private Integer price;
    private String title;
    private String estimatedTime;// check frontend
    private String description;
}
