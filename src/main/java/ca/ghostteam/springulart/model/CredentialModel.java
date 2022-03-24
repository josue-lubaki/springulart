package ca.ghostteam.springulart.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Data
public class CredentialModel {
        private Integer id;
        private String username;
        private String password;
        private String grantedAuthority;
        private LocalDate created = LocalDate.now();
        private LocalDate updated = LocalDate.now();
}
