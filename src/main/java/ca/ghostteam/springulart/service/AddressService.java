package ca.ghostteam.springulart.service;

import ca.ghostteam.springulart.dto.AddressDTO;
import ca.ghostteam.springulart.model.AddressModel;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */
public interface AddressService {
    Optional<AddressDTO> findAddressUserById(String id);

    Optional<AddressModel> findAddressModelUserById(String id);

    Optional<AddressDTO> saveAddress(AddressModel addressModel);

    Optional<AddressModel> saveAddressModel(AddressModel addressModel);

}
