package ca.ghostteam.springulart.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address_model")
public class AddressModel {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String street;
    private String apartement;
    private String zip;
    private String city;
    private String state;
}
