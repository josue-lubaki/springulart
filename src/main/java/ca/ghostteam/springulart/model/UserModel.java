package ca.ghostteam.springulart.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class UserModel {
    private Integer id;
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
