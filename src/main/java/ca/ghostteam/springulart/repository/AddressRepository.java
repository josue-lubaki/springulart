package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */
@Repository
public interface AddressRepository extends CrudRepository<AddressModel, Long> {
    @Override
    @NonNull
    AddressModel save(AddressModel addressModel);
}
