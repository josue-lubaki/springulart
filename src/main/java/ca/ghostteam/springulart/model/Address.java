package ca.ghostteam.springulart.model;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String apartement;
    private String zip;
    private String city;
    private String state;
}
