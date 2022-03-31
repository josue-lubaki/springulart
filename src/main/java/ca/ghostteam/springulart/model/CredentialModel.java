package ca.ghostteam.springulart.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
                CredentialModel that = (CredentialModel) o;
                return id != null && Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
                return getClass().hashCode();
        }
}
