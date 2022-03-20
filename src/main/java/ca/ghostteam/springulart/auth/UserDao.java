package ca.ghostteam.springulart.auth;

import ca.ghostteam.springulart.dto.UserDTO;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
public interface UserDao {
    Optional<UserDTO> selectUserByUsername(String username);
}
