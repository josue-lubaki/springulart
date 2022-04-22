package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.UserModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-28
 */
@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    List<UserModel> findAll();

    @Query("select u from UserModel u where u.email = ?1")
    Optional<UserModel> findUserModelByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 " +
            "THEN true ELSE false " +
            "END FROM UserModel u WHERE u.email = ?1")
    boolean existsByEmail(String email);

    @NonNull
    Optional<UserModel> findById(@NonNull Long aLong);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query( "UPDATE UserModel u SET u.password = :password WHERE u.email = :email")
    void updatePassword(@Param("email") String email, @Param("password") String password);
}
