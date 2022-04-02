package ca.ghostteam.springulart.service.address;

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

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<AddressDTO> findAddressUserById(Long id) {
        return addressRepository
                .findById(id)
                .stream()
                .map(this::converterAddressModelToAddressDTO)
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
                converterAddressModelToAddressDTO(
                        addressRepository
                        .save(addressModel)
                )
        );
    }

    @Override
    public Optional<AddressDTO> saveAddressModel(AddressModel addressModel) {
        return Optional.of(
                converterAddressModelToAddressDTO(
                    addressRepository
                    .save(addressModel)
                )
        );
    }

    /**
     * Method to convert AddressDTO to AddressModel
     * @param addressDTO AddressDTO to convert
     * @return AddressModel
     * */
    public AddressModel converterAddressDtoToAddressModel(AddressDTO addressDTO){
        AddressModel address = new AddressModel();
        address.setId(addressDTO.getId());
        address.setApartement(addressDTO.getApartement());
        address.setStreet(addressDTO.getStreet());
        address.setState(addressDTO.getState());
        address.setCity(addressDTO.getCity());
        address.setZip(addressDTO.getZip());

        return address;
    }

    /**
     * Method to convert AddressModel to AddressDTO
     * @param addressModel AddressModel to convert
     * @return AddressDTO
     * */
    public AddressDTO converterAddressModelToAddressDTO(AddressModel addressModel){
        AddressDTO address = new AddressDTO();
        address.setId(addressModel.getId());
        address.setApartement(addressModel.getApartement());
        address.setStreet(addressModel.getStreet());
        address.setState(addressModel.getState());
        address.setCity(addressModel.getCity());
        address.setZip(addressModel.getZip());

        return address;
    }
}
