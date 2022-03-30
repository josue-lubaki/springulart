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
    @ManyToOne
    @JoinColumn(name="reservation_time_id", nullable=false, referencedColumnName = "id")
    private ReservationTimeModel reservationTime;

    // foreign key
    @ManyToOne
    @JoinColumn(name = "haircut_id", referencedColumnName = "id")
    private HaircutModel haircut;

    // foreign key
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private UserModel client;

    // foreign key
    @ManyToOne
    @JoinColumn(name = "barber_id", referencedColumnName = "id")
    private UserModel barber;

    // foreign key
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationModel location; // check frontend

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationModel that = (ReservationModel) o;

        return id.equals(that.id);
    }

}
