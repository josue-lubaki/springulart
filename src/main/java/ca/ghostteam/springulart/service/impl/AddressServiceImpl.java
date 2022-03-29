package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.AddressDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.repository.AddressRepository;
import ca.ghostteam.springulart.service.AddressService;
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

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<AddressDTO> findAddressUserById(String id) {
        return addressRepository
                .findById(id)
                .stream()
                .map(this::converterAddressModelToAddressDTO)
                .findFirst();
    }

    @Override
    public Optional<AddressModel> findAddressModelUserById(String id) {
        return addressRepository
                .findById(id)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<AddressDTO> saveAddress(AddressModel addressModel) {
        return Optional.of(
                converterAddressModelToAddressDTO(
                        addressRepository
                        .save(addressModel)
                )
        );
    }

    @Override
    public Optional<AddressModel> saveAddressModel(AddressModel addressModel) {
        return Optional.of(
                addressRepository
                .save(addressModel)
        );
    }

    private AddressModel converterAddressDtoToAddressModel(AddressDTO addressDTO){
        AddressModel address = new AddressModel();
        address.setApartement(addressDTO.getApartement());
        address.setStreet(addressDTO.getStreet());
        address.setState(addressDTO.getState());
        address.setCity(addressDTO.getCity());
        address.setZip(addressDTO.getZip());

        return address;
    }

    private AddressDTO converterAddressModelToAddressDTO(AddressModel addressModel){
        AddressDTO address = new AddressDTO();
        address.setApartement(addressModel.getApartement());
        address.setStreet(addressModel.getStreet());
        address.setState(addressModel.getState());
        address.setCity(addressModel.getCity());
        address.setZip(addressModel.getZip());

        return address;
    }
}
