package ca.ghostteam.springulart.auth.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.auth.UserDao;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.model.Address;
import ca.ghostteam.springulart.model.Credential;
import ca.ghostteam.springulart.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean(name = "fake")
    private UserDao userDao;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        HashSet<GrantedAuthority> grantedAuthority = new HashSet<>();
        LocalDateTime created = LocalDateTime.of(1, 1, 1, 1, 1);
        Credential credentials = new Credential(1, "janedoe", "iloveyou", grantedAuthority, created,
                LocalDateTime.of(1, 1, 1, 1, 1));

        LocalDateTime dob = LocalDateTime.of(1, 1, 1, 1, 1);
        Address address = new Address("Street", "Apartement", "21654", "Oxford", "MD");

        LocalDateTime created1 = LocalDateTime.of(1, 1, 1, 1, 1);
        UserDTO userDTO = new UserDTO(credentials, new User(1, "Fname", "Lname", "jane.doe@example.org",
                "https://example.org/example", "4105551212", dob, address, created1, LocalDateTime.of(1, 1, 1, 1, 1)), true,
                true, true, true);

        when(this.userDao.selectUserByUsername((String) any())).thenReturn(Optional.of(userDTO));
        assertSame(userDTO, this.userServiceImpl.loadUserByUsername("janedoe"));
        verify(this.userDao).selectUserByUsername((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.userDao.selectUserByUsername((String) any())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.loadUserByUsername("janedoe"));
        verify(this.userDao).selectUserByUsername((String) any());
    }

    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        when(this.userDao.selectUserByUsername((String) any()))
                .thenThrow(new UsernameNotFoundException("Username %s not found"));
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.loadUserByUsername("janedoe"));
        verify(this.userDao).selectUserByUsername((String) any());
    }
}

