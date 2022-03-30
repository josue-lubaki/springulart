package ca.ghostteam.springulart.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservation_model")
public class ReservationModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reservationDate = LocalDate.now();
    private String status = "Non Traitée";

    // foreign key,
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "reservation_time_id", referencedColumnName = "id")
    private ReservationTimeModel reservationTime;

    // foreign key
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "haircut_id", referencedColumnName = "id")
    private HaircutModel haircut;

    // foreign key
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private UserModel client;

    // foreign key
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "barber_id", referencedColumnName = "id")
    private UserModel barber;

    // foreign key
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationModel location; // check frontend
}
