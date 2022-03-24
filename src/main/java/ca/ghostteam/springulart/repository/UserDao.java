package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.dto.UserDetailsDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
public interface UserDao {
    Optional<UserDetailsDTO> selectUserByUsername(String username);
    List<UserDetailsDTO> findAllUsers();
}
