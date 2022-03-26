package ca.ghostteam.springulart.dto;

import ca.ghostteam.springulart.model.AddressModel;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-23
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupDTO {
    private String fname;
    private String lname;
    private String email; // correspond to username
    private String password;
    private String imageURL;
    private String dob;
    private AddressModel address;
    private String phone;
    private String role;
}
