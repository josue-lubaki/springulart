package ca.ghostteam.springulart.model;

import lombok.Data;

@Data
public class Haircut {
    private Integer id;
    private String imageURL;
    private Integer price;
    private String title;
    private String estimatedTime;// check frontend
    private String description;
}
