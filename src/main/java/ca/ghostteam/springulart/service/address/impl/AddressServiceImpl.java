package ca.ghostteam.springulart.service.address.impl;

import ca.ghostteam.springulart.dto.AddressDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.repository.AddressRepository;
import ca.ghostteam.springulart.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UtilsAddressService utils;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              UtilsAddressService utils) {
        this.addressRepository = addressRepository;
        this.utils = utils;
    }

    @Override
    public Optional<AddressDTO> findAddressUserById(Long id) {
        return addressRepository
                .findById(id)
                .stream()
                .map(utils::converterAddressModelToAddressDTO)
                .findFirst();
    }

    @Override
    public Optional<AddressModel> findAddressModelUserById(Long id) {
        return addressRepository
                .findById(id)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<AddressDTO> saveAddress(AddressModel addressModel) {
        return Optional.of(
                utils.converterAddressModelToAddressDTO(
                        addressRepository
                        .save(addressModel)
                )
        );
    }

    @Override
    public Optional<AddressDTO> saveAddressModel(AddressModel addressModel) {
        return Optional.of(
                utils.converterAddressModelToAddressDTO(
                    addressRepository
                    .save(addressModel)
                )
        );
    }
}
