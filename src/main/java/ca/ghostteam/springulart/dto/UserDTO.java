package ca.ghostteam.springulart.dto;

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
    private AddressDTO address;
    private LocalDate created = LocalDate.now();
    private LocalDate updated = LocalDate.now();
    private boolean deleted = false;

    public String getFullName() {
        return fname + " " + lname;
    }
}
