package ca.ghostteam.springulart.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.ghostteam.springulart.model.Address;
import ca.ghostteam.springulart.model.Credential;
import ca.ghostteam.springulart.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserDTOTest {
    @Test
    void testGetAuthorities() {
        HashSet<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        LocalDateTime created = LocalDateTime.of(1, 1, 1, 1, 1);
        Credential credentials = new Credential(1, "janedoe", "iloveyou", grantedAuthoritySet, created,
                LocalDateTime.of(1, 1, 1, 1, 1));

        LocalDateTime dob = LocalDateTime.of(1, 1, 1, 1, 1);
        Address address = new Address("Street", "Apartement", "21654", "Oxford", "MD");

        LocalDateTime created1 = LocalDateTime.of(1, 1, 1, 1, 1);
        Collection<? extends GrantedAuthority> actualAuthorities = (new UserDTO(credentials,
                new User(1, "Fname", "Lname", "jane.doe@example.org", "https://example.org/example", "4105551212", dob, address,
                        created1, LocalDateTime.of(1, 1, 1, 1, 1)),
                true, true, true, true)).getAuthorities();
        assertSame(grantedAuthoritySet, actualAuthorities);
        assertTrue(actualAuthorities.isEmpty());
    }


    @Test
    void testGetPassword() {
        HashSet<GrantedAuthority> grantedAuthority = new HashSet<>();
        LocalDateTime created = LocalDateTime.of(1, 1, 1, 1, 1);
        Credential credentials = new Credential(1, "janedoe", "iloveyou", grantedAuthority, created,
                LocalDateTime.of(1, 1, 1, 1, 1));

        LocalDateTime dob = LocalDateTime.of(1, 1, 1, 1, 1);
        Address address = new Address("Street", "Apartement", "21654", "Oxford", "MD");

        LocalDateTime created1 = LocalDateTime.of(1, 1, 1, 1, 1);
        assertEquals("iloveyou",
                (new UserDTO(credentials, new User(1, "Fname", "Lname", "jane.doe@example.org", "https://example.org/example",
                        "4105551212", dob, address, created1, LocalDateTime.of(1, 1, 1, 1, 1)), true, true, true, true))
                        .getPassword());
    }

    @Test
    void testGetUsername() {
        HashSet<GrantedAuthority> grantedAuthority = new HashSet<>();
        LocalDateTime created = LocalDateTime.of(1, 1, 1, 1, 1);
        Credential credentials = new Credential(1, "janedoe", "iloveyou", grantedAuthority, created,
                LocalDateTime.of(1, 1, 1, 1, 1));

        LocalDateTime dob = LocalDateTime.of(1, 1, 1, 1, 1);
        Address address = new Address("Street", "Apartement", "21654", "Oxford", "MD");

        LocalDateTime created1 = LocalDateTime.of(1, 1, 1, 1, 1);
        assertEquals("janedoe",
                (new UserDTO(credentials, new User(1, "Fname", "Lname", "jane.doe@example.org", "https://example.org/example",
                        "4105551212", dob, address, created1, LocalDateTime.of(1, 1, 1, 1, 1)), true, true, true, true))
                        .getUsername());
    }

    @Test
    void testConstructor() {
        HashSet<GrantedAuthority> grantedAuthority = new HashSet<>();
        LocalDateTime created = LocalDateTime.of(1, 1, 1, 1, 1);
        Credential credentials = new Credential(1, "janedoe", "iloveyou", grantedAuthority, created,
                LocalDateTime.of(1, 1, 1, 1, 1));

        LocalDateTime dob = LocalDateTime.of(1, 1, 1, 1, 1);
        Address address = new Address("Street", "Apartement", "21654", "Oxford", "MD");

        LocalDateTime created1 = LocalDateTime.of(1, 1, 1, 1, 1);
        UserDTO actualUserDTO = new UserDTO(credentials, new User(1, "Fname", "Lname", "jane.doe@example.org",
                "https://example.org/example", "4105551212", dob, address, created1, LocalDateTime.of(1, 1, 1, 1, 1)), true,
                true, true, true);

        assertTrue(actualUserDTO.isAccountNonExpired());
        assertTrue(actualUserDTO.isAccountNonLocked());
        assertTrue(actualUserDTO.isCredentialsNonExpired());
        assertTrue(actualUserDTO.isEnabled());
    }
}

