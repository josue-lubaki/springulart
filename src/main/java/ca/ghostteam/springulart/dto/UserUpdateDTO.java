package ca.ghostteam.springulart.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-10
 */
@Data
public class UserUpdateDTO implements Serializable {
    private Long id;
    private String fname;
    private String lname;
    private Object imageURL;
    private String password;
    private String phone;
    private String street;
    private String apartement;
    private String zip;
    private String city;
    private String state;
    private LocalDate Updated = LocalDate.now();
}
