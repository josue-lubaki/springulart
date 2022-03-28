package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.ReservationModel;

import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-27
 */
public interface ReservationDao {
    List<ReservationModel> findAll();
    Optional<ReservationModel> findById(String id);
    Optional<ReservationModel> save(ReservationModel reservation);
    Optional<ReservationModel> update(String id, ReservationModel reservation);
    void deleteById(String id);
}
