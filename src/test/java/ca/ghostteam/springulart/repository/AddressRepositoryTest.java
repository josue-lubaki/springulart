package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.AddressModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Method under test: {@link AddressRepository#save(AddressModel)}
     */
    @Test
    void testSave() {

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");
        this.addressRepository.save(addressModel);

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");
        this.addressRepository.save(addressModel1);

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setUsers(new HashSet<>());
        addressModel2.setZip("21654");
        this.addressRepository.save(addressModel2);

        assertEquals(3, this.addressRepository.count());
    }

    @Test
    @DisplayName("Test with empty collection")
    void testEmptyCollection(){
        assertEquals(0, this.addressRepository.count());
    }
}

