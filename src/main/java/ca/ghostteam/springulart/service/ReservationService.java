package ca.ghostteam.springulart.service;


import ca.ghostteam.springulart.dto.ReservationDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-27
 */
public interface ReservationService {
    List<ReservationDTO> findAll();
    Optional<ReservationDTO> findById(String id);
    Optional<ReservationDTO> save(ReservationDTO reservation);
    Optional<ReservationDTO> update(String id, ReservationDTO reservation);
    void deleteById(String id);
}
