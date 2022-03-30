package ca.ghostteam.springulart.dto;

import ca.ghostteam.springulart.model.AddressModel;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-23
 */
@Data
public class SignupDTO {
    private Long id;
    private String fname;
    private String lname;
    private String email; // correspond to username
    private String password;
    private String imageURL;
    private LocalDate dob;
    private AddressDTO address;
    private String phone;
    private String role;
}
