package ca.ghostteam.springulart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationTimeModel {
    private Integer hours;
    private Integer minutes;
}
