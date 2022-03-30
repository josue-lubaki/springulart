package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.ReservationTimeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

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
}
