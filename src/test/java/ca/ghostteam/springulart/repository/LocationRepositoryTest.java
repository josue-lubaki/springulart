package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.LocationModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-21
 */
@DataJpaTest
class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @AfterEach
    void tearDown() {
        this.locationRepository.deleteAll();
    }

    @Test
    void findById() {
        // given
        LocationModel locationModel = new LocationModel();
        locationModel.setLatitude(1.0);
        locationModel.setLongitude(2.0);

        this.locationRepository.save(locationModel);

        // when
        LocationModel locationModelFound = this.locationRepository.findById(locationModel.getId()).get();

        // then
        assertEquals(locationModel.getLatitude(), locationModelFound.getLatitude());
    }

    @Test
    void updateLocationById() {
        // given
        LocationModel locationModel = new LocationModel();
        locationModel.setLatitude(1.0);
        locationModel.setLongitude(2.0);

        this.locationRepository.save(locationModel);

        // when
        locationModel.setLatitude(3.0);
        locationModel.setLongitude(4.0);

        this.locationRepository.updateLocationById(locationModel.getId(), locationModel.getLatitude(), locationModel.getLongitude());

        // then
        LocationModel locationModelFound = this.locationRepository.findById(locationModel.getId()).get();

        assertEquals(locationModel.getLatitude(), locationModelFound.getLatitude());
        assertEquals(locationModel.getLongitude(), locationModelFound.getLongitude());
    }
}