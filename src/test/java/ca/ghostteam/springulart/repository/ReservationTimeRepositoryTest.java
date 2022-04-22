package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.ReservationTimeModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-21
 */
@DataJpaTest
class ReservationTimeRepositoryTest {

    @Autowired
    private ReservationTimeRepository reservationTimeRepository;

    @Test
    void findById() {
        // given
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(12);
        reservationTimeModel.setMinutes(30);
        //save reservationTimeModel
        reservationTimeRepository.save(reservationTimeModel);

        // when
        ReservationTimeModel reservationTimeRetrieved = reservationTimeRepository.findById(reservationTimeModel.getId()).get();

        // then
        assertEquals(reservationTimeModel.getHours(), reservationTimeRetrieved.getHours());
        assertEquals(reservationTimeModel.getMinutes(), reservationTimeRetrieved.getMinutes());
        assertNotNull(reservationTimeRetrieved.getId());
    }

    @Test
    void save() {
        // given
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(12);
        reservationTimeModel.setMinutes(30);

        // when
        reservationTimeRepository.save(reservationTimeModel);

        // then
        assertNotNull(reservationTimeModel.getId());
        assertEquals(reservationTimeModel.getHours(), reservationTimeModel.getHours());
        assertEquals(reservationTimeModel.getMinutes(), reservationTimeModel.getMinutes());
    }

    @Test
    void deleteById() {
        // given
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(12);
        reservationTimeModel.setMinutes(30);
        //save reservationTimeModel
        reservationTimeRepository.save(reservationTimeModel);

        // when
        reservationTimeRepository.deleteById(reservationTimeModel.getId());

        // then
        assertEquals(Optional.empty() ,reservationTimeRepository.findById(reservationTimeModel.getId()));
    }

    @Test
    void updateReservationTimeById() {
        // given
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(12);
        reservationTimeModel.setMinutes(30);
        //save reservationTimeModel
        reservationTimeRepository.save(reservationTimeModel);

        // when
        reservationTimeModel.setHours(13);
        reservationTimeModel.setMinutes(30);
        reservationTimeRepository.save(reservationTimeModel);

        // then
        assertEquals(reservationTimeModel.getHours(), reservationTimeModel.getHours());
        assertEquals(reservationTimeModel.getMinutes(), reservationTimeModel.getMinutes());
        assertNotNull(reservationTimeModel.getId());
    }
}