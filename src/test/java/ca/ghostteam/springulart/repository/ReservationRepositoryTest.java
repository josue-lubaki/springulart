package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.dto.ReservationStatus;
import ca.ghostteam.springulart.model.*;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.model.ReservationModel;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.model.UserModel;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-21
 */
@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationTimeRepository reservationTimeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private HaircutRepository haircutRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    private ReservationModel reservationModel;

    @BeforeEach
    void setUp() {
        // given
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");
        // save address
        this.addressRepository.save(addressModel);

        UserModel userModel = new UserModel();
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(LocalDate.now());
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(LocalDate.now());
        credentialModel.setUsername("janedoe");
        // save credential
        this.credentialRepository.save(credentialModel);

        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.now());
        userModel.setDeleted(true);
        userModel.setDob(null);
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("ROLE_CLIENT");
        userModel.setUpdated(LocalDate.now());

        credentialModel.setUser(userModel);
        userModel.setCredential(credentialModel);
        // save user
        this.userRepository.save(userModel);

        UserModel userModel2 = new UserModel();
        CredentialModel credentialModel2 = new CredentialModel();
        credentialModel2.setCreated(LocalDate.now());
        credentialModel2.setGrantedAuthority("JaneDoe");
        credentialModel2.setPassword("iloveyou");
        credentialModel2.setUpdated(LocalDate.now());
        credentialModel2.setUsername("janedoe");
        // save credential
        this.credentialRepository.save(credentialModel2);

        userModel2.setAddress(addressModel);
        userModel2.setCreated(LocalDate.now());
        userModel2.setDeleted(true);
        userModel2.setDob(null);
        userModel2.setEmail("jane2.doe@example.org");
        userModel2.setFname("Fname");
        userModel2.setImageURL("https://example.org/example");
        userModel2.setLname("Lname");
        userModel2.setPassword("iloveyou");
        userModel2.setPhone("4105551212");
        userModel2.setReservationModelBarber(new ArrayList<>());
        userModel2.setReservationModelClient(new ArrayList<>());
        userModel2.setRole("ROLE_CLIENT");
        userModel2.setUpdated(LocalDate.now());

        credentialModel2.setUser(userModel2);
        userModel2.setCredential(credentialModel2);
        // save user
        this.userRepository.save(userModel2);

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new ArrayList<>());
        haircutModel.setTitle("Dr");
        // save haircut
        this.haircutRepository.save(haircutModel);

        LocationModel locationModel = new LocationModel();
        locationModel.setLatitude(10.0d);
        locationModel.setLongitude(10.0d);
        locationModel.setReservationModel(reservationModel);
        // saving location before saving reservation
        this.locationRepository.save(locationModel);

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new ArrayList<>());
        // save reservationTime
        this.reservationTimeRepository.save(reservationTimeModel);

        reservationModel = new ReservationModel();
        reservationModel.setBarber(userModel);
        reservationModel.setClient(userModel2);
        reservationModel.setHaircut(haircutModel);
        reservationModel.setLocation(locationModel);
        reservationModel.setReservationDate(LocalDate.now());
        reservationModel.setReservationTime(reservationTimeModel);
        reservationModel.setStatus(ReservationStatus.PENDING.getEtat());
    }

    @AfterEach
    void tearDown() {
        this.reservationRepository.deleteAll();
        this.haircutRepository.deleteAll();
        this.locationRepository.deleteAll();
        this.reservationTimeRepository.deleteAll();
        this.userRepository.deleteAll();
        this.credentialRepository.deleteAll();
        reservationModel = null;
    }

    @Test
    void findAll() {
        // given
        this.reservationRepository.save(reservationModel);

        // when
        boolean isEmpty = this.reservationRepository.findAll().isEmpty();

        // then
        assertFalse(isEmpty);
    }

    @Test
    @DisplayName("Test findAll method with no reservations")
    void findAll_with_no_reservations() {
        // given
        Iterable<ReservationModel> reservations = this.reservationRepository.findAll();

        // then
        assertFalse(reservations.iterator().hasNext());
    }

    @Test
    void findById() {
        // given
        this.reservationRepository.save(reservationModel);

        // when
        Long id = reservationModel.getId();
        ReservationModel reservationModel = this.reservationRepository.findById(id).get();

        // then
        assertNotNull(reservationModel);
        assertEquals("Dr", reservationModel.getHaircut().getTitle());
        assertEquals("The characteristics of someone or something", reservationModel.getHaircut().getDescription());
        assertEquals("Estimated Time", reservationModel.getHaircut().getEstimatedTime());
        assertEquals("https://example.org/example", reservationModel.getHaircut().getImageURL());
        assertEquals(1, reservationModel.getHaircut().getPrice());
        assertEquals("Fname", reservationModel.getClient().getFname());
        assertEquals("Lname", reservationModel.getClient().getLname());
        assertEquals("jane2.doe@example.org", reservationModel.getClient().getEmail());
        assertEquals("jane.doe@example.org", reservationModel.getBarber().getEmail());
    }

    @Test
    void deleteById() {
        // given
        this.reservationRepository.save(reservationModel);

        // when
        this.reservationRepository.deleteById(reservationModel.getId());

        // then
        assertEquals(Optional.empty(), this.reservationRepository.findById(reservationModel.getId()));
        assertFalse(this.reservationRepository.findAll().iterator().hasNext());
    }

    @Test
    void delete() {
        // save reservation
        this.reservationRepository.save(reservationModel);

        // when
        this.reservationRepository.delete(reservationModel);

        // then
        assertThat(this.reservationRepository.findAll()).hasSize(0);
    }

    @Test
    void existsById() {
        // given
        this.reservationRepository.save(reservationModel);

        // when
        boolean exists = this.reservationRepository.existsById(1L);

        // then
        assertFalse(exists);
    }

    @Test
    void save() {
        // when
        this.reservationRepository.save(reservationModel);

        // then
        Optional<ReservationModel> reservationRetrieved =
                this.reservationRepository
                .findById(reservationModel.getId());

        assertTrue(reservationRetrieved.isPresent());
        assertNotNull(reservationRetrieved);
        assertEquals("Dr", reservationRetrieved.get().getHaircut().getTitle());
        assertEquals("The characteristics of someone or something", reservationRetrieved.get().getHaircut().getDescription());
        assertEquals("Estimated Time", reservationRetrieved.get().getHaircut().getEstimatedTime());
        assertEquals("https://example.org/example", reservationRetrieved.get().getHaircut().getImageURL());
        assertEquals("Fname", reservationRetrieved.get().getClient().getFname());
        assertEquals("Lname", reservationRetrieved.get().getClient().getLname());
    }

    @Test
    void count() {
        // given
        this.reservationRepository.save(reservationModel);

        // when
        long count = this.reservationRepository.count();

        // then
        assertEquals(1, count);
    }

    @Test
    void update() {
        // given
        this.reservationRepository.save(reservationModel);
        UserModel barber = new UserModel();
        AddressModel address = new AddressModel();
        address.setApartement("23");
        address.setCity("Trois-Rivières");
        address.setState("QC");
        address.setStreet("Street");
        address.setUsers(new HashSet<>());
        address.setZip("G1V 2C3");
        // save address
        this.addressRepository.save(address);

        // create credentials
        CredentialModel credentials = new CredentialModel();
        credentials.setUsername("joe@gmail.com");
        credentials.setPassword("password");

        barber.setFname("barber");
        barber.setLname("barber");
        barber.setEmail("joe@gmail.com");
        barber.setPassword("password");
        barber.setRole("ROLE_BARBER");
        barber.setPhone("123456789");
        barber.setAddress(address);
        credentials.setUser(barber);
        credentials.setGrantedAuthority("ROLE_BARBER");
        // save credentials
        this.credentialRepository.save(credentials);

        barber.setCredential(credentials);

        // save barber
        this.userRepository.save(barber);

        reservationModel.setStatus("ACCEPTED");
        reservationModel.setReservationDate(LocalDate.now());
        reservationModel.setBarber(barber);

        // when
        this.reservationRepository.update(
                reservationModel.getId(),
                reservationModel.getReservationDate(),
                barber,
                reservationModel.getStatus()
        );

        // then
        assertEquals("ACCEPTED", this.reservationRepository.findById(reservationModel.getId()).get().getStatus());
        assertEquals(barber, this.reservationRepository.findById(reservationModel.getId()).get().getBarber());
    }

    @Test
    void flush() {
        // given
        this.reservationRepository.save(reservationModel);

        // when
        this.reservationRepository.flush();

        // then
        assertNotNull(this.reservationRepository.findById(reservationModel.getId()));
    }
}