package ca.ghostteam.springulart.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "user_model")
public class UserModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    @Column(name = "image_url")
    private String imageURL;
    private String phone;
    private String role;
    private LocalDate dob = LocalDate.now();
    private LocalDate created = LocalDate.now();
    private LocalDate updated = LocalDate.now();
    private boolean deleted = false;

    // clé étrangère UUID
    @ManyToOne
    @JoinColumn(name="address_id", nullable=false)
    private AddressModel address;

    // clé étrangère
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credential_id", referencedColumnName = "id")
    private CredentialModel credential;
}
