package ca.ghostteam.springulart.dto;

import lombok.*;

@Data
public class HaircutDTO {
    private String id;
    private String imageURL;
    private Integer price;
    private String title;
    private String estimatedTime;// check frontend
    private String description;
}
