package ca.ghostteam.springulart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.AddressDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.AddressRepository;

import java.util.HashSet;
import java.util.Optional;

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

    @Test
    void testFindAddressUserById() {
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
        Optional<AddressDTO> actualFindAddressUserByIdResult = this.addressServiceImpl.findAddressUserById(123L);
        assertTrue(actualFindAddressUserByIdResult.isPresent());
        AddressDTO getResult = actualFindAddressUserByIdResult.get();
        assertEquals("Apartement", getResult.getApartement());
        assertEquals("21654", getResult.getZip());
        assertEquals("Street", getResult.getStreet());
        assertEquals("MD", getResult.getState());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("Oxford", getResult.getCity());
        verify(this.addressRepository).findById(any());
    }

    @Test
    void testFindAddressUserById2() {
        Optional<AddressModel> emptyResult = Optional.empty();
        when(this.addressRepository.findById(any())).thenReturn(emptyResult);
        Optional<AddressDTO> actualFindAddressUserByIdResult = this.addressServiceImpl.findAddressUserById(123L);
        assertFalse(actualFindAddressUserByIdResult.isPresent());
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
        Optional<AddressDTO> actualSaveAddressResult = this.addressServiceImpl.saveAddress(addressModel1);
        assertTrue(actualSaveAddressResult.isPresent());
        AddressDTO getResult = actualSaveAddressResult.get();
        assertEquals("Apartement", getResult.getApartement());
        assertEquals("21654", getResult.getZip());
        assertEquals("Street", getResult.getStreet());
        assertEquals("MD", getResult.getState());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("Oxford", getResult.getCity());
        verify(this.addressRepository).save(any());
    }

    @Test
    void testSaveAddressModel() {
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
        Optional<AddressDTO> actualSaveAddressModelResult = this.addressServiceImpl.saveAddressModel(addressModel1);
        assertTrue(actualSaveAddressModelResult.isPresent());
        AddressDTO getResult = actualSaveAddressModelResult.get();
        assertEquals("Apartement", getResult.getApartement());
        assertEquals("21654", getResult.getZip());
        assertEquals("Street", getResult.getStreet());
        assertEquals("MD", getResult.getState());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("Oxford", getResult.getCity());
        verify(this.addressRepository).save(any());
    }

    @Test
    void testSaveAddressModel2() {
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
        Optional<AddressDTO> actualSaveAddressModelResult = this.addressServiceImpl.saveAddressModel(addressModel1);
        assertTrue(actualSaveAddressModelResult.isPresent());
        AddressDTO getResult = actualSaveAddressModelResult.get();
        assertEquals("Apartement", getResult.getApartement());
        assertEquals("21654", getResult.getZip());
        assertEquals("Street", getResult.getStreet());
        assertEquals("MD", getResult.getState());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("Oxford", getResult.getCity());
        verify(this.addressRepository).save(any());
    }

    @Test
    void testConverterAddressDtoToAddressModel() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");
        AddressModel actualConverterAddressDtoToAddressModelResult = this.addressServiceImpl
                .converterAddressDtoToAddressModel(addressDTO);
        assertEquals("Apartement", actualConverterAddressDtoToAddressModelResult.getApartement());
        assertEquals("21654", actualConverterAddressDtoToAddressModelResult.getZip());
        assertEquals("Street", actualConverterAddressDtoToAddressModelResult.getStreet());
        assertEquals("MD", actualConverterAddressDtoToAddressModelResult.getState());
        assertEquals(123L, actualConverterAddressDtoToAddressModelResult.getId().longValue());
        assertEquals("Oxford", actualConverterAddressDtoToAddressModelResult.getCity());
    }

    @Test
    void testConverterAddressDtoToAddressModel2() {
        AddressDTO addressDTO = mock(AddressDTO.class);
        when(addressDTO.getId()).thenReturn(123L);
        when(addressDTO.getApartement()).thenReturn("Apartement");
        when(addressDTO.getCity()).thenReturn("Oxford");
        when(addressDTO.getState()).thenReturn("MD");
        when(addressDTO.getStreet()).thenReturn("Street");
        when(addressDTO.getZip()).thenReturn("21654");
        doNothing().when(addressDTO).setApartement(any());
        doNothing().when(addressDTO).setCity(any());
        doNothing().when(addressDTO).setId(any());
        doNothing().when(addressDTO).setState(any());
        doNothing().when(addressDTO).setStreet(any());
        doNothing().when(addressDTO).setZip(any());
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");
        AddressModel actualConverterAddressDtoToAddressModelResult = this.addressServiceImpl
                .converterAddressDtoToAddressModel(addressDTO);
        assertEquals("Apartement", actualConverterAddressDtoToAddressModelResult.getApartement());
        assertEquals("21654", actualConverterAddressDtoToAddressModelResult.getZip());
        assertEquals("Street", actualConverterAddressDtoToAddressModelResult.getStreet());
        assertEquals("MD", actualConverterAddressDtoToAddressModelResult.getState());
        assertEquals(123L, actualConverterAddressDtoToAddressModelResult.getId().longValue());
        assertEquals("Oxford", actualConverterAddressDtoToAddressModelResult.getCity());
        verify(addressDTO).getId();
        verify(addressDTO).getApartement();
        verify(addressDTO).getCity();
        verify(addressDTO).getState();
        verify(addressDTO).getStreet();
        verify(addressDTO).getZip();
        verify(addressDTO).setApartement(any());
        verify(addressDTO).setCity(any());
        verify(addressDTO).setId(any());
        verify(addressDTO).setState(any());
        verify(addressDTO).setStreet(any());
        verify(addressDTO).setZip(any());
    }

    @Test
    void testConverterAddressModelToAddressDTO() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");
        AddressDTO actualConverterAddressModelToAddressDTOResult = this.addressServiceImpl
                .converterAddressModelToAddressDTO(addressModel);
        assertEquals("Apartement", actualConverterAddressModelToAddressDTOResult.getApartement());
        assertEquals("21654", actualConverterAddressModelToAddressDTOResult.getZip());
        assertEquals("Street", actualConverterAddressModelToAddressDTOResult.getStreet());
        assertEquals("MD", actualConverterAddressModelToAddressDTOResult.getState());
        assertEquals(123L, actualConverterAddressModelToAddressDTOResult.getId().longValue());
        assertEquals("Oxford", actualConverterAddressModelToAddressDTOResult.getCity());
    }

    @Test
    void testConverterAddressModelToAddressDTO2() {
        AddressModel addressModel = mock(AddressModel.class);
        when(addressModel.getId()).thenReturn(123L);
        when(addressModel.getApartement()).thenReturn("Apartement");
        when(addressModel.getCity()).thenReturn("Oxford");
        when(addressModel.getState()).thenReturn("MD");
        when(addressModel.getStreet()).thenReturn("Street");
        when(addressModel.getZip()).thenReturn("21654");
        doNothing().when(addressModel).setApartement(any());
        doNothing().when(addressModel).setCity(any());
        doNothing().when(addressModel).setId(any());
        doNothing().when(addressModel).setState(any());
        doNothing().when(addressModel).setStreet(any());
        doNothing().when(addressModel).setUsers(any());
        doNothing().when(addressModel).setZip(any());
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");
        AddressDTO actualConverterAddressModelToAddressDTOResult = this.addressServiceImpl
                .converterAddressModelToAddressDTO(addressModel);
        assertEquals("Apartement", actualConverterAddressModelToAddressDTOResult.getApartement());
        assertEquals("21654", actualConverterAddressModelToAddressDTOResult.getZip());
        assertEquals("Street", actualConverterAddressModelToAddressDTOResult.getStreet());
        assertEquals("MD", actualConverterAddressModelToAddressDTOResult.getState());
        assertEquals(123L, actualConverterAddressModelToAddressDTOResult.getId().longValue());
        assertEquals("Oxford", actualConverterAddressModelToAddressDTOResult.getCity());
        verify(addressModel).getId();
        verify(addressModel).getApartement();
        verify(addressModel).getCity();
        verify(addressModel).getState();
        verify(addressModel).getStreet();
        verify(addressModel).getZip();
        verify(addressModel).setApartement(any());
        verify(addressModel).setCity(any());
        verify(addressModel).setId(any());
        verify(addressModel).setState(any());
        verify(addressModel).setStreet(any());
        verify(addressModel).setUsers(any());
        verify(addressModel).setZip(any());
    }
}

