package ca.ghostteam.springulart.service;


import ca.ghostteam.springulart.dto.ReservationDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-27
 */
public interface ReservationService {
//    List<ReservationDTO> findAll(long idUserCurrent);
    List<ReservationDTO> findAll();
    Optional<ReservationDTO> findById(Long id);
    Optional<ReservationDTO> save(ReservationDTO reservation);
    Optional<ReservationDTO> update(Long id, ReservationDTO reservation);
    void deleteReservationById(Long id);
    long count();
}
