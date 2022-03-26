package ca.ghostteam.springulart.service;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
public interface UserService extends UserDetailsService {
    List<UserDTO> findAllUsers();
    Optional<UserDTO> saveUser(SignupDTO signupDTO) throws Exception;
    boolean findUserByEmail(String email);
}
