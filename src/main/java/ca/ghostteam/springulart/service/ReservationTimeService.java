package ca.ghostteam.springulart.service;

import ca.ghostteam.springulart.dto.ReservationTimeDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */

public interface ReservationTimeService {
    Optional<ReservationTimeDTO> findById(Long id);
    Optional<ReservationTimeDTO> save(ReservationTimeDTO reservationModel);

    @Transactional
    Optional<ReservationTimeDTO> update(Long id, ReservationTimeDTO reservationModel);
    void delete(Long id);
}
