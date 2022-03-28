package ca.ghostteam.springulart.dto;

import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-27
 */
@Data
public class ReservationDTO {
    private String id;
    private LocalDate reservationDate;
    private ReservationTimeModel reservationTimeModel;
    private HaircutModel haircutModel;
    private String status;
    private UserDTO client;
    private LocationModel locationModel; // check frontend
}
