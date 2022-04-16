package ca.ghostteam.springulart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.CredentialDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.CredentialRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import ca.ghostteam.springulart.service.credential.CredentialServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CredentialServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CredentialServiceImplTest {
    @MockBean
    private CredentialRepository credentialRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialServiceImpl credentialServiceImpl;

    @Test
    void testFindCredentialUserById() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(new AddressModel());
        userModel.setCreated(null);
        userModel.setCredential(new CredentialModel());
        userModel.setDeleted(true);
        userModel.setDob(null);
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(null);

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel.setUser(userModel);
        credentialModel.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel1);
        credentialModel1.setUsername("janedoe");
        Optional<CredentialModel> ofResult = Optional.of(credentialModel1);
        when(this.credentialRepository.findById(any())).thenReturn(ofResult);
        Optional<CredentialDTO> actualFindCredentialUserByIdResult = this.credentialServiceImpl
                .findCredentialUserById(123L);
        assertTrue(actualFindCredentialUserByIdResult.isPresent());
        CredentialDTO getResult = actualFindCredentialUserByIdResult.get();
        assertEquals("1970-01-02", getResult.getCreated().toString());
        assertEquals("janedoe", getResult.getUsername());
        assertEquals("1970-01-02", getResult.getUpdated().toString());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("JaneDoe", getResult.getGrantedAuthority());
        verify(this.credentialRepository).findById(any());
    }

    @Test
    void testFindCredentialUserById2() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(new AddressModel());
        userModel.setCreated(null);
        userModel.setCredential(new CredentialModel());
        userModel.setDeleted(true);
        userModel.setDob(null);
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(null);

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel.setUser(userModel);
        credentialModel.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        CredentialModel credentialModel1 = mock(CredentialModel.class);
        when(credentialModel1.getId()).thenReturn(123L);
        when(credentialModel1.getGrantedAuthority()).thenReturn("JaneDoe");
        when(credentialModel1.getPassword()).thenReturn("iloveyou");
        when(credentialModel1.getUsername()).thenReturn("janedoe");
        when(credentialModel1.getCreated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(credentialModel1.getUpdated()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(credentialModel1).setCreated(any());
        doNothing().when(credentialModel1).setGrantedAuthority(any());
        doNothing().when(credentialModel1).setId(any());
        doNothing().when(credentialModel1).setPassword(any());
        doNothing().when(credentialModel1).setUpdated(any());
        doNothing().when(credentialModel1).setUser(any());
        doNothing().when(credentialModel1).setUsername(any());
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel1);
        credentialModel1.setUsername("janedoe");
        Optional<CredentialModel> ofResult = Optional.of(credentialModel1);
        when(this.credentialRepository.findById(any())).thenReturn(ofResult);
        Optional<CredentialDTO> actualFindCredentialUserByIdResult = this.credentialServiceImpl
                .findCredentialUserById(123L);
        assertTrue(actualFindCredentialUserByIdResult.isPresent());
        CredentialDTO getResult = actualFindCredentialUserByIdResult.get();
        assertEquals("1970-01-02", getResult.getCreated().toString());
        assertEquals("janedoe", getResult.getUsername());
        assertEquals("1970-01-02", getResult.getUpdated().toString());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("JaneDoe", getResult.getGrantedAuthority());
        verify(this.credentialRepository).findById(any());
        verify(credentialModel1).getId();
        verify(credentialModel1).getGrantedAuthority();
        verify(credentialModel1).getPassword();
        verify(credentialModel1).getUsername();
        verify(credentialModel1).getCreated();
        verify(credentialModel1).getUpdated();
        verify(credentialModel1).setCreated(any());
        verify(credentialModel1).setGrantedAuthority(any());
        verify(credentialModel1).setId(any());
        verify(credentialModel1).setPassword(any());
        verify(credentialModel1).setUpdated(any());
        verify(credentialModel1).setUser(any());
        verify(credentialModel1).setUsername(any());
    }

    @Test
    void testSaveCredential() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel2 = new CredentialModel();
        credentialModel2.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel2.setGrantedAuthority("JaneDoe");
        credentialModel2.setId(123L);
        credentialModel2.setPassword("iloveyou");
        credentialModel2.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel2.setUser(userModel1);
        credentialModel2.setUsername("janedoe");
        when(this.credentialRepository.save(any())).thenReturn(credentialModel2);

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setId(123L);
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setUsers(new HashSet<>());
        addressModel2.setZip("21654");

        UserModel userModel2 = new UserModel();
        userModel2.setAddress(new AddressModel());
        userModel2.setCreated(null);
        userModel2.setCredential(new CredentialModel());
        userModel2.setDeleted(true);
        userModel2.setDob(null);
        userModel2.setEmail("jane.doe@example.org");
        userModel2.setFname("Fname");
        userModel2.setId(123L);
        userModel2.setImageURL("https://example.org/example");
        userModel2.setLname("Lname");
        userModel2.setPassword("iloveyou");
        userModel2.setPhone("4105551212");
        userModel2.setReservationModelBarber(new ArrayList<>());
        userModel2.setReservationModelClient(new ArrayList<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel3 = new CredentialModel();
        credentialModel3.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel3.setGrantedAuthority("JaneDoe");
        credentialModel3.setId(123L);
        credentialModel3.setPassword("iloveyou");
        credentialModel3.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel3.setUser(userModel2);
        credentialModel3.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel2);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel3);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new ArrayList<>());
        userModel3.setReservationModelClient(new ArrayList<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel4 = new CredentialModel();
        credentialModel4.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel4.setGrantedAuthority("JaneDoe");
        credentialModel4.setId(123L);
        credentialModel4.setPassword("iloveyou");
        credentialModel4.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel4.setUser(userModel3);
        credentialModel4.setUsername("janedoe");
        Optional<CredentialDTO> actualSaveCredentialResult = this.credentialServiceImpl.saveCredential(credentialModel4);
        assertTrue(actualSaveCredentialResult.isPresent());
        CredentialDTO getResult = actualSaveCredentialResult.get();
        assertEquals("1970-01-02", getResult.getCreated().toString());
        assertEquals("janedoe", getResult.getUsername());
        assertEquals("1970-01-02", getResult.getUpdated().toString());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("JaneDoe", getResult.getGrantedAuthority());
        verify(this.credentialRepository).save(any());
    }

    @Test
    void testSaveCredential2() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        CredentialModel credentialModel2 = mock(CredentialModel.class);
        when(credentialModel2.getId()).thenReturn(123L);
        when(credentialModel2.getGrantedAuthority()).thenReturn("JaneDoe");
        when(credentialModel2.getPassword()).thenReturn("iloveyou");
        when(credentialModel2.getUsername()).thenReturn("janedoe");
        when(credentialModel2.getCreated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(credentialModel2.getUpdated()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(credentialModel2).setCreated(any());
        doNothing().when(credentialModel2).setGrantedAuthority(any());
        doNothing().when(credentialModel2).setId(any());
        doNothing().when(credentialModel2).setPassword(any());
        doNothing().when(credentialModel2).setUpdated(any());
        doNothing().when(credentialModel2).setUser(any());
        doNothing().when(credentialModel2).setUsername(any());
        credentialModel2.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel2.setGrantedAuthority("JaneDoe");
        credentialModel2.setId(123L);
        credentialModel2.setPassword("iloveyou");
        credentialModel2.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel2.setUser(userModel1);
        credentialModel2.setUsername("janedoe");
        when(this.credentialRepository.save(any())).thenReturn(credentialModel2);

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setId(123L);
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setUsers(new HashSet<>());
        addressModel2.setZip("21654");

        UserModel userModel2 = new UserModel();
        userModel2.setAddress(new AddressModel());
        userModel2.setCreated(null);
        userModel2.setCredential(new CredentialModel());
        userModel2.setDeleted(true);
        userModel2.setDob(null);
        userModel2.setEmail("jane.doe@example.org");
        userModel2.setFname("Fname");
        userModel2.setId(123L);
        userModel2.setImageURL("https://example.org/example");
        userModel2.setLname("Lname");
        userModel2.setPassword("iloveyou");
        userModel2.setPhone("4105551212");
        userModel2.setReservationModelBarber(new ArrayList<>());
        userModel2.setReservationModelClient(new ArrayList<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel3 = new CredentialModel();
        credentialModel3.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel3.setGrantedAuthority("JaneDoe");
        credentialModel3.setId(123L);
        credentialModel3.setPassword("iloveyou");
        credentialModel3.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel3.setUser(userModel2);
        credentialModel3.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel2);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel3);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new ArrayList<>());
        userModel3.setReservationModelClient(new ArrayList<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel4 = new CredentialModel();
        credentialModel4.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel4.setGrantedAuthority("JaneDoe");
        credentialModel4.setId(123L);
        credentialModel4.setPassword("iloveyou");
        credentialModel4.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel4.setUser(userModel3);
        credentialModel4.setUsername("janedoe");
        Optional<CredentialDTO> actualSaveCredentialResult = this.credentialServiceImpl.saveCredential(credentialModel4);
        assertTrue(actualSaveCredentialResult.isPresent());
        CredentialDTO getResult = actualSaveCredentialResult.get();
        assertEquals("1970-01-02", getResult.getCreated().toString());
        assertEquals("janedoe", getResult.getUsername());
        assertEquals("1970-01-02", getResult.getUpdated().toString());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("JaneDoe", getResult.getGrantedAuthority());
        verify(this.credentialRepository).save(any());
        verify(credentialModel2).getId();
        verify(credentialModel2).getGrantedAuthority();
        verify(credentialModel2).getPassword();
        verify(credentialModel2).getUsername();
        verify(credentialModel2).getCreated();
        verify(credentialModel2).getUpdated();
        verify(credentialModel2).setCreated(any());
        verify(credentialModel2).setGrantedAuthority(any());
        verify(credentialModel2).setId(any());
        verify(credentialModel2).setPassword(any());
        verify(credentialModel2).setUpdated(any());
        verify(credentialModel2).setUser(any());
        verify(credentialModel2).setUsername(any());
    }
}

