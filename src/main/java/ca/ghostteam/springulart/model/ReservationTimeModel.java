package ca.ghostteam.springulart.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reservation_time_model")
public class ReservationTimeModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer hours;
    private Integer minutes;

    @OneToOne(mappedBy="reservationTime")
    private ReservationModel reservationModel;
}
