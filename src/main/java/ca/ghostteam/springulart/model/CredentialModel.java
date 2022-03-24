package ca.ghostteam.springulart.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@ToString
public class CredentialModel {
        private final Integer id;
        private final String username;
        private final String password;
        private final String grantedAuthority;
        private final LocalDateTime created;
        private final LocalDateTime updated;
}
