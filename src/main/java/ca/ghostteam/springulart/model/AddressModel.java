package ca.ghostteam.springulart.model;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
public class AddressModel {
    private final String street;
    private final String apartement;
    private final String zip;
    private final String city;
    private final String state;
}
