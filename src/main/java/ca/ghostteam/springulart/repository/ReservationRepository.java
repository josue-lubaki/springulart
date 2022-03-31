package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.ReservationModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-28
 */
@Transactional
@Repository
public interface ReservationRepository extends CrudRepository<ReservationModel, Long> {
    @Override
    List<ReservationModel> findAll();

    @Override
    Optional<ReservationModel> findById(Long s);

    void deleteReservationModelById(@NonNull Long reservationId);

    @Override
    @Modifying
    void deleteById(@NonNull Long id);

    @Modifying
    void delete(@NonNull ReservationModel entity);

    @Override
    boolean existsById(@NonNull Long s);

    @Override
    @Modifying
    ReservationModel save(@NonNull ReservationModel entity);

    @Override
    long count();

    // insert query
    @Query(value = "INSERT INTO reservation_model (id, reservation_date, reservation_time_id, status, client_id , haircut_id, location_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    @Modifying(flushAutomatically = true)
    void insertReservation(BigInteger reservationId,
                           LocalDate reservationDate,
                           Long reservationTime,
                           String reservationStatus,
                           Long reservationClientId,
                           String reservationHaircutId,
                           Long reservationLocationId);


    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE ReservationModel AS r SET r = ?2 WHERE r.id = ?1")
    void update(Long id, @NonNull ReservationModel entity);

    // flush query
    @Modifying(flushAutomatically = true)
    @Query(value = "SELECT * FROM reservation_model", nativeQuery = true)
    List<ReservationModel> flush();
}
