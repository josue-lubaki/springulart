package ca.ghostteam.springulart.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservation_model")
public class ReservationModel {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private LocalDate reservationDate = LocalDate.now();
    private Long reservationTime;
    private String haircut;
    private String status;
    private Long client;
    private Long barber;
    private Long location; // check frontend
}
