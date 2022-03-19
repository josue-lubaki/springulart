package ca.ghostteam.springulart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {

    private String fname;
    private String lname;
    private String email;
    private String imageURL;
    private String phone;
    private LocalDateTime dob;
    private Address address;
    private String role;
    private LocalDateTime created;
    private LocalDateTime updated ;
    private boolean deleted = false;
}
