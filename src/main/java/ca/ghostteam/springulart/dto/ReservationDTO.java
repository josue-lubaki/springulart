package ca.ghostteam.springulart.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-27
 */
@Data
public class ReservationDTO {
    private Long id;
    private LocalDate reservationDate;
    private ReservationTimeDTO reservationTime;
    private HaircutDTO haircut;
    private String status;
    private UserDTO client;
    private UserDTO barber;
    private LocationDTO location; // check frontend
}
