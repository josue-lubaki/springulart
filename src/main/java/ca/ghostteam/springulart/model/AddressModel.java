package ca.ghostteam.springulart.model;

import lombok.*;

@Data
@RequiredArgsConstructor
public class AddressModel {
    private String street;
    private String apartement;
    private String zip;
    private String city;
    private String state;
}
