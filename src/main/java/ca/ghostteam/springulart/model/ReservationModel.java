package ca.ghostteam.springulart.model;

import ca.ghostteam.springulart.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservationModel {
    private String id;
    private LocalDate reservationDate;
    private ReservationTimeModel reservationTimeModel;
    private HaircutModel haircutModel;
    private String status;
    private UserDTO client;
    private LocationModel locationModel; // check frontend
}
