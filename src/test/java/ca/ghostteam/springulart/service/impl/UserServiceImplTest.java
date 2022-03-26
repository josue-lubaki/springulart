package ca.ghostteam.springulart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.UserDao;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Optional;

import ca.ghostteam.springulart.service.UserService;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean(name = "fake-repository-users")
    private UserDao userDao;

    @Autowired
    private UserService userServiceImpl;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel);
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        userModel.setCreated(ofEpochDayResult);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel);
        when(this.userDao.selectUserByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.userServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertSame(userModel, ((UserDetailsDTO) actualLoadUserByUsernameResult).getUserModel());
        CredentialModel credentials = ((UserDetailsDTO) actualLoadUserByUsernameResult).getCredentials();
        assertEquals("Role", credentials.getGrantedAuthority());
        assertEquals("iloveyou", credentials.getPassword());
        assertEquals("jane.doe@example.org", credentials.getUsername());
        assertSame(ofEpochDayResult, credentials.getCreated());
        assertEquals("1970-01-02", credentials.getUpdated().toString());
        assertEquals(1, credentials.getId().intValue());
        verify(this.userDao).selectUserByUsername((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");
        UserModel userModel = mock(UserModel.class);
        when(userModel.getId()).thenReturn(1);
        when(userModel.getEmail()).thenReturn("jane.doe@example.org");
        when(userModel.getPassword()).thenReturn("iloveyou");
        when(userModel.getRole()).thenReturn("Role");
        when(userModel.getCreated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getUpdated()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(userModel).setAddress((AddressModel) any());
        doNothing().when(userModel).setCreated((LocalDate) any());
        doNothing().when(userModel).setDeleted(anyBoolean());
        doNothing().when(userModel).setDob((LocalDate) any());
        doNothing().when(userModel).setEmail((String) any());
        doNothing().when(userModel).setFname((String) any());
        doNothing().when(userModel).setId((Integer) any());
        doNothing().when(userModel).setImageURL((String) any());
        doNothing().when(userModel).setLname((String) any());
        doNothing().when(userModel).setPassword((String) any());
        doNothing().when(userModel).setPhone((String) any());
        doNothing().when(userModel).setRole((String) any());
        doNothing().when(userModel).setUpdated((LocalDate) any());
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel);
        when(this.userDao.selectUserByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.userServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        CredentialModel credentials = ((UserDetailsDTO) actualLoadUserByUsernameResult).getCredentials();
        assertEquals("Role", credentials.getGrantedAuthority());
        assertEquals(1, credentials.getId().intValue());
        assertEquals("1970-01-02", credentials.getUpdated().toString());
        assertEquals("jane.doe@example.org", credentials.getUsername());
        assertEquals("1970-01-02", credentials.getCreated().toString());
        assertEquals("iloveyou", credentials.getPassword());
        verify(this.userDao).selectUserByUsername((String) any());
        verify(userModel).getId();
        verify(userModel).getEmail();
        verify(userModel).getPassword();
        verify(userModel).getRole();
        verify(userModel).getCreated();
        verify(userModel).getUpdated();
        verify(userModel).setAddress((AddressModel) any());
        verify(userModel).setCreated((LocalDate) any());
        verify(userModel).setDeleted(anyBoolean());
        verify(userModel).setDob((LocalDate) any());
        verify(userModel).setEmail((String) any());
        verify(userModel).setFname((String) any());
        verify(userModel).setId((Integer) any());
        verify(userModel).setImageURL((String) any());
        verify(userModel).setLname((String) any());
        verify(userModel).setPassword((String) any());
        verify(userModel).setPhone((String) any());
        verify(userModel).setRole((String) any());
        verify(userModel).setUpdated((LocalDate) any());
    }

    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        when(this.userDao.selectUserByUsername((String) any())).thenReturn(Optional.empty());

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");
        UserModel userModel = mock(UserModel.class);
        when(userModel.getId()).thenReturn(1);
        when(userModel.getEmail()).thenReturn("jane.doe@example.org");
        when(userModel.getPassword()).thenReturn("iloveyou");
        when(userModel.getRole()).thenReturn("Role");
        when(userModel.getCreated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getUpdated()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(userModel).setAddress((AddressModel) any());
        doNothing().when(userModel).setCreated((LocalDate) any());
        doNothing().when(userModel).setDeleted(anyBoolean());
        doNothing().when(userModel).setDob((LocalDate) any());
        doNothing().when(userModel).setEmail((String) any());
        doNothing().when(userModel).setFname((String) any());
        doNothing().when(userModel).setId((Integer) any());
        doNothing().when(userModel).setImageURL((String) any());
        doNothing().when(userModel).setLname((String) any());
        doNothing().when(userModel).setPassword((String) any());
        doNothing().when(userModel).setPhone((String) any());
        doNothing().when(userModel).setRole((String) any());
        doNothing().when(userModel).setUpdated((LocalDate) any());
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.loadUserByUsername("janedoe"));
        verify(this.userDao).selectUserByUsername((String) any());
        verify(userModel).setAddress((AddressModel) any());
        verify(userModel).setCreated((LocalDate) any());
        verify(userModel).setDeleted(anyBoolean());
        verify(userModel).setDob((LocalDate) any());
        verify(userModel).setEmail((String) any());
        verify(userModel).setFname((String) any());
        verify(userModel).setId((Integer) any());
        verify(userModel).setImageURL((String) any());
        verify(userModel).setLname((String) any());
        verify(userModel).setPassword((String) any());
        verify(userModel).setPhone((String) any());
        verify(userModel).setRole((String) any());
        verify(userModel).setUpdated((LocalDate) any());
    }

    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel);
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        userModel.setCreated(ofEpochDayResult);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel);
        when(this.userDao.selectUserByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.userServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertSame(userModel, ((UserDetailsDTO) actualLoadUserByUsernameResult).getUserModel());
        CredentialModel credentials = ((UserDetailsDTO) actualLoadUserByUsernameResult).getCredentials();
        assertEquals("Role", credentials.getGrantedAuthority());
        assertEquals("iloveyou", credentials.getPassword());
        assertEquals("jane.doe@example.org", credentials.getUsername());
        assertSame(ofEpochDayResult, credentials.getCreated());
        assertEquals("1970-01-02", credentials.getUpdated().toString());
        assertEquals(1, credentials.getId().intValue());
        verify(this.userDao).selectUserByUsername((String) any());
    }

    @Test
    void testLoadUserByUsername5() throws UsernameNotFoundException {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");
        UserModel userModel = mock(UserModel.class);
        when(userModel.getId()).thenReturn(1);
        when(userModel.getEmail()).thenReturn("jane.doe@example.org");
        when(userModel.getPassword()).thenReturn("iloveyou");
        when(userModel.getRole()).thenReturn("Role");
        when(userModel.getCreated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getUpdated()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(userModel).setAddress((AddressModel) any());
        doNothing().when(userModel).setCreated((LocalDate) any());
        doNothing().when(userModel).setDeleted(anyBoolean());
        doNothing().when(userModel).setDob((LocalDate) any());
        doNothing().when(userModel).setEmail((String) any());
        doNothing().when(userModel).setFname((String) any());
        doNothing().when(userModel).setId((Integer) any());
        doNothing().when(userModel).setImageURL((String) any());
        doNothing().when(userModel).setLname((String) any());
        doNothing().when(userModel).setPassword((String) any());
        doNothing().when(userModel).setPhone((String) any());
        doNothing().when(userModel).setRole((String) any());
        doNothing().when(userModel).setUpdated((LocalDate) any());
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel);
        when(this.userDao.selectUserByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.userServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        CredentialModel credentials = ((UserDetailsDTO) actualLoadUserByUsernameResult).getCredentials();
        assertEquals("Role", credentials.getGrantedAuthority());
        assertEquals(1, credentials.getId().intValue());
        assertEquals("1970-01-02", credentials.getUpdated().toString());
        assertEquals("jane.doe@example.org", credentials.getUsername());
        assertEquals("1970-01-02", credentials.getCreated().toString());
        assertEquals("iloveyou", credentials.getPassword());
        verify(this.userDao).selectUserByUsername((String) any());
        verify(userModel).getId();
        verify(userModel).getEmail();
        verify(userModel).getPassword();
        verify(userModel).getRole();
        verify(userModel).getCreated();
        verify(userModel).getUpdated();
        verify(userModel).setAddress((AddressModel) any());
        verify(userModel).setCreated((LocalDate) any());
        verify(userModel).setDeleted(anyBoolean());
        verify(userModel).setDob((LocalDate) any());
        verify(userModel).setEmail((String) any());
        verify(userModel).setFname((String) any());
        verify(userModel).setId((Integer) any());
        verify(userModel).setImageURL((String) any());
        verify(userModel).setLname((String) any());
        verify(userModel).setPassword((String) any());
        verify(userModel).setPhone((String) any());
        verify(userModel).setRole((String) any());
        verify(userModel).setUpdated((LocalDate) any());
    }

    @Test
    void testLoadUserByUsername6() throws UsernameNotFoundException {
        when(this.userDao.selectUserByUsername((String) any())).thenReturn(Optional.empty());

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");
        UserModel userModel = mock(UserModel.class);
        when(userModel.getId()).thenReturn(1);
        when(userModel.getEmail()).thenReturn("jane.doe@example.org");
        when(userModel.getPassword()).thenReturn("iloveyou");
        when(userModel.getRole()).thenReturn("Role");
        when(userModel.getCreated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getUpdated()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(userModel).setAddress((AddressModel) any());
        doNothing().when(userModel).setCreated((LocalDate) any());
        doNothing().when(userModel).setDeleted(anyBoolean());
        doNothing().when(userModel).setDob((LocalDate) any());
        doNothing().when(userModel).setEmail((String) any());
        doNothing().when(userModel).setFname((String) any());
        doNothing().when(userModel).setId((Integer) any());
        doNothing().when(userModel).setImageURL((String) any());
        doNothing().when(userModel).setLname((String) any());
        doNothing().when(userModel).setPassword((String) any());
        doNothing().when(userModel).setPhone((String) any());
        doNothing().when(userModel).setRole((String) any());
        doNothing().when(userModel).setUpdated((LocalDate) any());
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.loadUserByUsername("janedoe"));
        verify(this.userDao).selectUserByUsername((String) any());
        verify(userModel).setAddress((AddressModel) any());
        verify(userModel).setCreated((LocalDate) any());
        verify(userModel).setDeleted(anyBoolean());
        verify(userModel).setDob((LocalDate) any());
        verify(userModel).setEmail((String) any());
        verify(userModel).setFname((String) any());
        verify(userModel).setId((Integer) any());
        verify(userModel).setImageURL((String) any());
        verify(userModel).setLname((String) any());
        verify(userModel).setPassword((String) any());
        verify(userModel).setPhone((String) any());
        verify(userModel).setRole((String) any());
        verify(userModel).setUpdated((LocalDate) any());
    }

    @Test
    void testFindAllUsers() {
        when(this.userDao.findAllUsers()).thenReturn(new ArrayList<>());
        assertTrue(this.userServiceImpl.findAllUsers().isEmpty());
        verify(this.userDao).findAllUsers();
    }

    @Test
    void testFindAllUsers2() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        ArrayList<UserModel> userModelList = new ArrayList<>();
        userModelList.add(userModel);
        when(this.userDao.findAllUsers()).thenReturn(userModelList);
        assertEquals(1, this.userServiceImpl.findAllUsers().size());
        verify(this.userDao).findAllUsers();
    }

    @Test
    void testFindAllUsers3() {
        when(this.userDao.findAllUsers()).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.findAllUsers());
        verify(this.userDao).findAllUsers();
    }

    @Test
    void testFindAllUsers4() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel1);
        userModel1.setCreated(LocalDate.ofEpochDay(3L));
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(3L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(1);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(3L));

        ArrayList<UserModel> userModelList = new ArrayList<>();
        userModelList.add(userModel1);
        userModelList.add(userModel);
        when(this.userDao.findAllUsers()).thenReturn(userModelList);
        assertEquals(2, this.userServiceImpl.findAllUsers().size());
        verify(this.userDao).findAllUsers();
    }

    @Test
    void testFindAllUsers5() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");
        UserModel userModel = mock(UserModel.class);
        when(userModel.isDeleted()).thenReturn(true);
        when(userModel.getAddress()).thenReturn(addressModel1);
        when(userModel.getId()).thenReturn(1);
        when(userModel.getEmail()).thenReturn("jane.doe@example.org");
        when(userModel.getFname()).thenReturn("Fname");
        when(userModel.getImageURL()).thenReturn("https://example.org/example");
        when(userModel.getLname()).thenReturn("Lname");
        when(userModel.getPhone()).thenReturn("4105551212");
        when(userModel.getRole()).thenReturn("Role");
        when(userModel.getCreated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getDob()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getUpdated()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(userModel).setAddress((AddressModel) any());
        doNothing().when(userModel).setCreated((LocalDate) any());
        doNothing().when(userModel).setDeleted(anyBoolean());
        doNothing().when(userModel).setDob((LocalDate) any());
        doNothing().when(userModel).setEmail((String) any());
        doNothing().when(userModel).setFname((String) any());
        doNothing().when(userModel).setId((Integer) any());
        doNothing().when(userModel).setImageURL((String) any());
        doNothing().when(userModel).setLname((String) any());
        doNothing().when(userModel).setPassword((String) any());
        doNothing().when(userModel).setPhone((String) any());
        doNothing().when(userModel).setRole((String) any());
        doNothing().when(userModel).setUpdated((LocalDate) any());
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        ArrayList<UserModel> userModelList = new ArrayList<>();
        userModelList.add(userModel);
        when(this.userDao.findAllUsers()).thenReturn(userModelList);
        assertEquals(1, this.userServiceImpl.findAllUsers().size());
        verify(this.userDao).findAllUsers();
        verify(userModel).isDeleted();
        verify(userModel).getAddress();
        verify(userModel).getId();
        verify(userModel).getEmail();
        verify(userModel).getFname();
        verify(userModel).getImageURL();
        verify(userModel).getLname();
        verify(userModel).getPhone();
        verify(userModel).getRole();
        verify(userModel).getCreated();
        verify(userModel).getDob();
        verify(userModel).getUpdated();
        verify(userModel).setAddress((AddressModel) any());
        verify(userModel).setCreated((LocalDate) any());
        verify(userModel).setDeleted(anyBoolean());
        verify(userModel).setDob((LocalDate) any());
        verify(userModel).setEmail((String) any());
        verify(userModel).setFname((String) any());
        verify(userModel).setId((Integer) any());
        verify(userModel).setImageURL((String) any());
        verify(userModel).setLname((String) any());
        verify(userModel).setPassword((String) any());
        verify(userModel).setPhone((String) any());
        verify(userModel).setRole((String) any());
        verify(userModel).setUpdated((LocalDate) any());
    }

    @Test
    void testFindAllUsers6() {
        when(this.userDao.findAllUsers()).thenReturn(new ArrayList<>());
        assertTrue(this.userServiceImpl.findAllUsers().isEmpty());
        verify(this.userDao).findAllUsers();
    }

    @Test
    void testFindAllUsers7() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        ArrayList<UserModel> userModelList = new ArrayList<>();
        userModelList.add(userModel);
        when(this.userDao.findAllUsers()).thenReturn(userModelList);
        assertEquals(1, this.userServiceImpl.findAllUsers().size());
        verify(this.userDao).findAllUsers();
    }

    @Test
    void testFindAllUsers8() {
        when(this.userDao.findAllUsers()).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.findAllUsers());
        verify(this.userDao).findAllUsers();
    }

    @Test
    void testFindAllUsers9() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel1);
        userModel1.setCreated(LocalDate.ofEpochDay(3L));
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(3L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(1);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(3L));

        ArrayList<UserModel> userModelList = new ArrayList<>();
        userModelList.add(userModel1);
        userModelList.add(userModel);
        when(this.userDao.findAllUsers()).thenReturn(userModelList);
        assertEquals(2, this.userServiceImpl.findAllUsers().size());
        verify(this.userDao).findAllUsers();
    }

    @Test
    void testFindAllUsers10() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");
        UserModel userModel = mock(UserModel.class);
        when(userModel.isDeleted()).thenReturn(true);
        when(userModel.getAddress()).thenReturn(addressModel1);
        when(userModel.getId()).thenReturn(1);
        when(userModel.getEmail()).thenReturn("jane.doe@example.org");
        when(userModel.getFname()).thenReturn("Fname");
        when(userModel.getImageURL()).thenReturn("https://example.org/example");
        when(userModel.getLname()).thenReturn("Lname");
        when(userModel.getPhone()).thenReturn("4105551212");
        when(userModel.getRole()).thenReturn("Role");
        when(userModel.getCreated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getDob()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getUpdated()).thenReturn(LocalDate.ofEpochDay(1L));
        doNothing().when(userModel).setAddress((AddressModel) any());
        doNothing().when(userModel).setCreated((LocalDate) any());
        doNothing().when(userModel).setDeleted(anyBoolean());
        doNothing().when(userModel).setDob((LocalDate) any());
        doNothing().when(userModel).setEmail((String) any());
        doNothing().when(userModel).setFname((String) any());
        doNothing().when(userModel).setId((Integer) any());
        doNothing().when(userModel).setImageURL((String) any());
        doNothing().when(userModel).setLname((String) any());
        doNothing().when(userModel).setPassword((String) any());
        doNothing().when(userModel).setPhone((String) any());
        doNothing().when(userModel).setRole((String) any());
        doNothing().when(userModel).setUpdated((LocalDate) any());
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        ArrayList<UserModel> userModelList = new ArrayList<>();
        userModelList.add(userModel);
        when(this.userDao.findAllUsers()).thenReturn(userModelList);
        assertEquals(1, this.userServiceImpl.findAllUsers().size());
        verify(this.userDao).findAllUsers();
        verify(userModel).isDeleted();
        verify(userModel).getAddress();
        verify(userModel).getId();
        verify(userModel).getEmail();
        verify(userModel).getFname();
        verify(userModel).getImageURL();
        verify(userModel).getLname();
        verify(userModel).getPhone();
        verify(userModel).getRole();
        verify(userModel).getCreated();
        verify(userModel).getDob();
        verify(userModel).getUpdated();
        verify(userModel).setAddress((AddressModel) any());
        verify(userModel).setCreated((LocalDate) any());
        verify(userModel).setDeleted(anyBoolean());
        verify(userModel).setDob((LocalDate) any());
        verify(userModel).setEmail((String) any());
        verify(userModel).setFname((String) any());
        verify(userModel).setId((Integer) any());
        verify(userModel).setImageURL((String) any());
        verify(userModel).setLname((String) any());
        verify(userModel).setPassword((String) any());
        verify(userModel).setPhone((String) any());
        verify(userModel).setRole((String) any());
        verify(userModel).setUpdated((LocalDate) any());
    }

    @Test
    public void testFindUserById() {
        AddressModel addressModel = mock(AddressModel.class);
        when(addressModel.getCity()).thenReturn("City");
        when(addressModel.getState()).thenReturn("State");
        when(addressModel.getStreet()).thenReturn("Street");
        when(addressModel.getZip()).thenReturn("21654");
        UserModel userModel = mock(UserModel.class);
        when(userModel.isDeleted()).thenReturn(true);
        when(userModel.getAddress()).thenReturn(addressModel);
        when(userModel.getId()).thenReturn(1);
        when(userModel.getEmail()).thenReturn("jane.doe@example.org");
        when(userModel.getFname()).thenReturn("Fname");
        when(userModel.getImageURL()).thenReturn("https://example.org/example");
        when(userModel.getLname()).thenReturn("Lname");
        when(userModel.getPassword()).thenReturn("iloveyou");
        when(userModel.getPhone()).thenReturn("4105551212");
        when(userModel.getRole()).thenReturn("Role");
        when(userModel.getCreated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getDob()).thenReturn(LocalDate.ofEpochDay(1L));
        when(userModel.getUpdated()).thenReturn(LocalDate.ofEpochDay(1L));
        when(this.userDao.selectUserByUsername("jane.doe@example.org")).thenReturn(Optional.of(userModel));
        assertEquals(userModel.getPassword(), this.userServiceImpl.loadUserByUsername("jane.doe@example.org").getPassword());
        assertEquals(userModel.getEmail(), this.userServiceImpl.loadUserByUsername("jane.doe@example.org").getUsername());
        assertTrue(this.userServiceImpl.loadUserByUsername("jane.doe@example.org").isAccountNonExpired());
    }

    @Test
    void testSaveUser2() throws Exception {
        when(this.userDao.findAllUsers()).thenReturn(new ArrayList<>());
        when(this.passwordEncoder.encode((CharSequence) any())).thenThrow(new UsernameNotFoundException("Msg"));

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setAddress(addressModel);
        signupDTO.setDob("Dob");
        signupDTO.setEmail("jane.doe@example.org");
        signupDTO.setFname("Fname");
        signupDTO.setImageURL("https://example.org/example");
        signupDTO.setLname("Lname");
        signupDTO.setPassword("iloveyou");
        signupDTO.setPhone("4105551212");
        signupDTO.setRole("Role");
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.saveUser(signupDTO));
        verify(this.userDao).findAllUsers();
        verify(this.passwordEncoder).encode((CharSequence) any());
    }

    @Test
    void testSaveUser3() throws Exception {
        when(this.userDao.findAllUsers()).thenReturn(new ArrayList<>());
        when(this.passwordEncoder.encode((CharSequence) any())).thenThrow(new UsernameNotFoundException("Msg"));

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setAddress(addressModel);
        signupDTO.setDob("Dob");
        signupDTO.setEmail("jane.doe@example.org");
        signupDTO.setFname("Fname");
        signupDTO.setImageURL("https://example.org/example");
        signupDTO.setLname("Lname");
        signupDTO.setPassword("iloveyou");
        signupDTO.setPhone("4105551212");
        signupDTO.setRole("Role");
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.saveUser(signupDTO));
        verify(this.userDao).findAllUsers();
        verify(this.passwordEncoder).encode((CharSequence) any());
    }

    @Test
    void testFindUserByEmail() {
        when(this.userDao.existsByEmail((String) any())).thenReturn(true);
        assertTrue(this.userServiceImpl.findUserByEmail("jane.doe@example.org"));
        verify(this.userDao).existsByEmail((String) any());
    }

    @Test
    void testFindUserByEmail2() {
        when(this.userDao.existsByEmail((String) any())).thenReturn(false);
        assertFalse(this.userServiceImpl.findUserByEmail("jane.doe@example.org"));
        verify(this.userDao).existsByEmail((String) any());
    }

    @Test
    void testFindUserByEmail3() {
        when(this.userDao.existsByEmail((String) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.findUserByEmail("jane.doe@example.org"));
        verify(this.userDao).existsByEmail((String) any());
    }

    @Test
    void testFindUserByEmail4() {
        when(this.userDao.existsByEmail((String) any())).thenReturn(true);
        assertTrue(this.userServiceImpl.findUserByEmail("jane.doe@example.org"));
        verify(this.userDao).existsByEmail((String) any());
    }

    @Test
    void testFindUserByEmail5() {
        when(this.userDao.existsByEmail((String) any())).thenReturn(false);
        assertFalse(this.userServiceImpl.findUserByEmail("jane.doe@example.org"));
        verify(this.userDao).existsByEmail((String) any());
    }

    @Test
    void testFindUserByEmail6() {
        when(this.userDao.existsByEmail((String) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.findUserByEmail("jane.doe@example.org"));
        verify(this.userDao).existsByEmail((String) any());
    }
}

