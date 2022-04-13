package ca.ghostteam.springulart.dto;

import lombok.Data;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */
@Data
public class AddressDTO {
    private Long id;
    private String street;
    private String apartment;
    private String zip;
    private String city;
    private String state;
}
