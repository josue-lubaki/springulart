package ca.ghostteam.springulart.service.user;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import org.springframework.scheduling.annotation.Async;
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
    Optional<UserDTO> findUserById(Long id);
    boolean existsUserByEmail(String email);
    void deleteUserById(Long id);
    Optional<UserDTO> updateUser(Long id, UserDTO userDTO) throws Exception;
    Optional<UserDTO> findUserByEmail(String email);

    @Async
    void updatePassword(String email, String password);
}
