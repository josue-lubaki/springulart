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
public class UserDTO {
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String imageURL;
    private String phone;
    private String role;
    private LocalDate dob = LocalDate.now();
    private AddressModel address;
    private LocalDate created = LocalDate.now();
    private LocalDate updated = LocalDate.now();
    private boolean deleted = false;
}
