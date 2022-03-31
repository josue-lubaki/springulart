package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.AddressModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

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
