package ca.ghostteam.springulart.service;

import ca.ghostteam.springulart.dto.UserDetailsDTO;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
public interface UserDao {
    Optional<UserDetailsDTO> selectUserByUsername(String username);
}
