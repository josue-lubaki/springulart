package ca.ghostteam.springulart.service.address;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.AddressDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.repository.AddressRepository;

import java.util.HashSet;
import java.util.Optional;

import ca.ghostteam.springulart.service.address.impl.AddressServiceImpl;
import ca.ghostteam.springulart.service.address.impl.UtilsAddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AddressServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AddressServiceImplTest {
    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @MockBean
    private UtilsAddressService utilsAddressService;

    @Test
    void testFindAddressUserById() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");
        when(this.utilsAddressService.converterAddressModelToAddressDTO(any())).thenReturn(addressDTO);

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");
        Optional<AddressModel> ofResult = Optional.of(addressModel);
        when(this.addressRepository.findById(any())).thenReturn(ofResult);
        assertTrue(this.addressServiceImpl.findAddressUserById(123L).isPresent());
        verify(this.utilsAddressService).converterAddressModelToAddressDTO(any());
        verify(this.addressRepository).findById(any());
    }

    @Test
    void testFindAddressModelUserById() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");
        Optional<AddressModel> ofResult = Optional.of(addressModel);
        when(this.addressRepository.findById(any())).thenReturn(ofResult);
        assertTrue(this.addressServiceImpl.findAddressModelUserById(123L).isPresent());
        verify(this.addressRepository).findById(any());
    }

    @Test
    void testSaveAddress() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");
        when(this.utilsAddressService.converterAddressModelToAddressDTO(any())).thenReturn(addressDTO);

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");
        when(this.addressRepository.save(any())).thenReturn(addressModel);

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");
        assertTrue(this.addressServiceImpl.saveAddress(addressModel1).isPresent());
        verify(this.utilsAddressService).converterAddressModelToAddressDTO(any());
        verify(this.addressRepository).save(any());
    }

    @Test
    void testSaveAddressModel() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");
        when(this.utilsAddressService.converterAddressModelToAddressDTO(any())).thenReturn(addressDTO);

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");
        when(this.addressRepository.save(any())).thenReturn(addressModel);

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");
        assertTrue(this.addressServiceImpl.saveAddressModel(addressModel1).isPresent());
        verify(this.utilsAddressService).converterAddressModelToAddressDTO(any());
        verify(this.addressRepository).save(any());
    }
}

