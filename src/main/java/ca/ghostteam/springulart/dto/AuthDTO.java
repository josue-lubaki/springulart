package ca.ghostteam.springulart.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthDTO {
    private String username;
    private String password;
}
