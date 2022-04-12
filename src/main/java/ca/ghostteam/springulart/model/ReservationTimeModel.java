package ca.ghostteam.springulart.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "reservation_time_model")
public class ReservationTimeModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer hours;
    private Integer minutes;

    @OneToMany(mappedBy="reservationTime")
    @ToString.Exclude
    private List<ReservationModel> reservationModel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservationTimeModel that = (ReservationTimeModel) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return hours + ":" + (minutes < 10 ? "0" + minutes : minutes);
    }
}
