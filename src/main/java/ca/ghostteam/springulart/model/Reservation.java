package ca.ghostteam.springulart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Reservation {
    private String id;
    private LocalDateTime reservationDate;
    private ReservationTime reservationTime;
    private Haircut haircut;
    private String status;
    private User client;
    private Location location; // check frontend
}
