package ca.ghostteam.springulart.model;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Credential{
        private String id;
        private String username;
        private String password;
        private String role;
        private LocalDateTime created;
        private LocalDateTime updated;
}
