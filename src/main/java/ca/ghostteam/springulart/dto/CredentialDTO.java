package ca.ghostteam.springulart.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-28
 */
@Data
public class CredentialDTO {
    private Long id;
    private String username;
    private String password;
    private String grantedAuthority;
    private LocalDate created = LocalDate.now();
    private LocalDate updated = LocalDate.now();
}
