package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.UserModel;

import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
public interface UserDao {
    Optional<UserModel> selectUserByUsername(String username);
    List<UserModel> findAllUsers();
    Optional<UserModel> save(UserModel userModel);
    boolean existsByEmail(String email);
    void deleteById(Integer id);
}
