package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.ReservationTimeModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */
@Repository
public interface ReservationTimeRepository extends CrudRepository<ReservationTimeModel, Long> {
    @Override
    @NonNull
    Optional<ReservationTimeModel> findById(@NonNull Long aLong);

    @Override
    @NonNull
    ReservationTimeModel save(@NonNull ReservationTimeModel reservationTimeModel);

    @Override
    void deleteById(@NonNull Long aLong);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE ReservationTimeModel r SET r.hours = :hours, r.minutes = :minutes WHERE r.id = :id")
    void updateReservationTimeById(@Param("id") Long id, @Param("hours") Integer hours, @Param("minutes") Integer minutes);
}
