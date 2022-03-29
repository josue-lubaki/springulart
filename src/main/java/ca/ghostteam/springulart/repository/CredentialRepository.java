package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.CredentialModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-28
 */
@Repository
public interface CredentialRepository extends CrudRepository<CredentialModel, Long> {
    @Override
    CredentialModel save(@NonNull CredentialModel credential);
}
