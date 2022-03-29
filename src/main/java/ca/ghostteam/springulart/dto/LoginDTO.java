package ca.ghostteam.springulart.dto;

import lombok.Data;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-25
 */
@Data
public class LoginDTO {
    private Long id;
    private String email;
    private String token;
    private String role;
}
