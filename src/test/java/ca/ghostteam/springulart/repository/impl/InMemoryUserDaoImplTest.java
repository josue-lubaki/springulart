package ca.ghostteam.springulart.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.UserModel;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {InMemoryUserDaoImpl.class})
@ExtendWith(SpringExtension.class)
class InMemoryUserDaoImplTest {
    @Autowired
    private InMemoryUserDaoImpl inMemoryUserDaoImpl;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testSelectUserByUsername() {
        assertFalse(this.inMemoryUserDaoImpl.selectUserByUsername("janedoe").isPresent());
    }

    @Test
    void testExistsByEmail() {
        assertTrue(this.inMemoryUserDaoImpl.existsByEmail("ismaelcoulibaly@gmail.com"));
    }

    @Test
    void testUpdate() {
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
        Optional<UserModel> actualUpdateResult = this.inMemoryUserDaoImpl.update(1, userModel);
        assertTrue(actualUpdateResult.isPresent());
        UserModel getResult = actualUpdateResult.get();
        assertNull(getResult.getPassword());
        assertEquals(1, getResult.getId().intValue());
        assertEquals(3, this.inMemoryUserDaoImpl.findAllUsers().size());
    }

    @Test
    void testUpdate4() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");
        UserModel userModel = mock(UserModel.class);
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
        this.inMemoryUserDaoImpl.update(1, userModel);
        verify(userModel).setAddress((AddressModel) any());
        verify(userModel).setCreated((LocalDate) any());
        verify(userModel).setDeleted(anyBoolean());
        verify(userModel).setDob((LocalDate) any());
        verify(userModel).setEmail((String) any());
        verify(userModel).setFname((String) any());
        verify(userModel).setImageURL((String) any());
        verify(userModel).setLname((String) any());
        verify(userModel).setPassword("iloveyou");
        verify(userModel).setPhone((String) any());
        verify(userModel).setRole((String) any());
        verify(userModel).setUpdated((LocalDate) any());
    }

    @Test
    void testDeleteById() {
        this.inMemoryUserDaoImpl.deleteById(1);
        assertEquals(2, this.inMemoryUserDaoImpl.findAllUsers().size());
    }

    @Test
    void testDeleteById2() {
        this.inMemoryUserDaoImpl.deleteById(1);
        assertEquals(2, this.inMemoryUserDaoImpl.findAllUsers().size());
    }

    @Test
    void testFindAllUsers() {
        assertEquals(3, this.inMemoryUserDaoImpl.findAllUsers().size());
    }

    @Test
    void testSave() {
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
        assertTrue(this.inMemoryUserDaoImpl.save(userModel).isPresent());
    }

    @Test
    void testSave2() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");
        UserModel userModel = mock(UserModel.class);
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
        assertTrue(this.inMemoryUserDaoImpl.save(userModel).isPresent());
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
}

