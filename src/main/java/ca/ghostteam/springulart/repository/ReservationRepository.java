package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.ReservationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-28
 */
@Repository
public interface ReservationRepository extends CrudRepository<ReservationModel, String> {
    @Override
    List<ReservationModel> findAll();

    @Override
    Optional<ReservationModel> findById(@NonNull String s);

    @Override
    void deleteById(@NonNull String s);

    @Override
    boolean existsById(@NonNull String s);

    @Override
    @NonNull
    ReservationModel save(@NonNull ReservationModel entity);

    @Override
    long count();
}
