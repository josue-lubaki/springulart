package ca.ghostteam.springulart.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "haircut_model")
public class HaircutModel {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String imageURL;
    private Integer price;
    private String title;
    private String estimatedTime;// check frontend
    private String description;

    @OneToMany(mappedBy = "haircut", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ReservationModel> reservationModel;
}
