package ca.ghostteam.springulart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReservationModel {
    private String id;
    private LocalDateTime reservationDate;
    private ReservationTimeModel reservationTimeModel;
    private HaircutModel haircutModel;
    private String status;
    private UserModel client;
    private LocationModel locationModel; // check frontend
}
