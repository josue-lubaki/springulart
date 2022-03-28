package ca.ghostteam.springulart.model;

import ca.ghostteam.springulart.dto.UserDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationModel {
    private String id;
    private LocalDate reservationDate;
    private ReservationTimeModel reservationTime;
    private HaircutModel haircut;
    private String status;
    private UserDTO client;
    private LocationModel location; // check frontend
}
