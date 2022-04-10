package ca.ghostteam.springulart.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
@Data
public class RegisterDTO implements Serializable {
    private String fname;
    private String lname;
    private String email; // correspond to username
    private String password;
    private MultipartFile imageURL;
    private String dob;
    private String phone;
    private String role;
    private String street;
    private String apartement;
    private String zip;
    private String city;
    private String state;
}
