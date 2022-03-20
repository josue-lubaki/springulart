package ca.ghostteam.springulart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private Double latitude;
    private Double longitude;
}
