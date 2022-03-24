package ca.ghostteam.springulart.dto;

import ca.ghostteam.springulart.model.AddressModel;
import lombok.Data;

import java.util.Date;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-23
 */
@Data
public class SignupDTO {
    private String fname;
    private String lname;
    private String email; // correspond to username
    private String password;
    private String imageURL;
    private Date dob;
    private AddressModel address;
    private String phone;
    private String role;
}
