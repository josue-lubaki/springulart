package ca.ghostteam.springulart.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "location_model")
public class LocationModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double latitude;
    private Double longitude;

    @OneToOne(mappedBy="location")
    private ReservationModel reservationModel;
}
