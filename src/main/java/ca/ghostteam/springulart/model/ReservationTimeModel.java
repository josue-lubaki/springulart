package ca.ghostteam.springulart.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "reservation_time_model")
public class ReservationTimeModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer hours;
    private Integer minutes;

    @OneToMany(mappedBy="reservationTime")
    private Set<ReservationModel> reservationModel;
}
