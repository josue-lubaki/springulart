package ca.ghostteam.springulart.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@Entity
@Table(name = "credential_model")
public class CredentialModel {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String password;
        @Column(name = "granted_authorities")
        private String grantedAuthority;
        private LocalDate created = LocalDate.now();
        private LocalDate updated = LocalDate.now();

        @OneToOne(mappedBy="credential", cascade = CascadeType.ALL)
        private UserModel user;
}
