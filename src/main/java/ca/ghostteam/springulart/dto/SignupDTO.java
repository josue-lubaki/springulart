package ca.ghostteam.springulart.dto;

import lombok.Data;

import java.time.LocalDate;

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
    private LocalDate dob;
    private String phone;
    private String role;

    // Address Information
    private String street;
    private String apartement;
    private String zip;
    private String city;
    private String state;
}
