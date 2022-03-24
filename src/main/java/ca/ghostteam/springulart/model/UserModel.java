package ca.ghostteam.springulart.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class UserModel {
    private final Integer id;
    private final String fname;
    private final String lname;
    private final String email;
    private final String imageURL;
    private final String phone;
    private final LocalDateTime dob;
    private final AddressModel addressModel;
    private final LocalDateTime created;
    private final LocalDateTime updated ;
    private final boolean deleted = false;
}
