package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.CredentialModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-28
 */
@Repository
public interface CredentialRepository extends CrudRepository<CredentialModel, Long> {
    @Override
    @NonNull
    CredentialModel save(@NonNull CredentialModel credential);

    @Modifying
    @Transactional
    @Query("UPDATE CredentialModel c set c.password =:password where c.username = :username")
    void updatePassword(@Param("username") String email, @Param("password") String password);

    CredentialModel findCredentialByUsername(String username);
}
