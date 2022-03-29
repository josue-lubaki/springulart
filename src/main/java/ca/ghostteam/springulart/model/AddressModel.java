package ca.ghostteam.springulart.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "address_model")
public class AddressModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String apartement;
    private String zip;
    private String city;
    private String state;
}
