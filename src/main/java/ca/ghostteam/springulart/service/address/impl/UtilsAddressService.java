package ca.ghostteam.springulart.service.address.impl;

import ca.ghostteam.springulart.dto.AddressDTO;
import ca.ghostteam.springulart.model.AddressModel;
import org.springframework.stereotype.Component;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-04
 */
@Component
public class UtilsAddressService {


    /**
     * Method to convert AddressDTO to AddressModel
     * @param addressDTO AddressDTO to convert
     * @return AddressModel
     * */
    public AddressModel converterAddressDtoToAddressModel(AddressDTO addressDTO){
        AddressModel address = new AddressModel();
        address.setId(addressDTO.getId());
        address.setApartment(addressDTO.getApartment());
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
        address.setApartment(addressModel.getApartment());
        address.setStreet(addressModel.getStreet());
        address.setState(addressModel.getState());
        address.setCity(addressModel.getCity());
        address.setZip(addressModel.getZip());

        return address;
    }
}
