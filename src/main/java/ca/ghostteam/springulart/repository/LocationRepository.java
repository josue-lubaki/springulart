package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.LocationModel;
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
public interface LocationRepository extends CrudRepository<LocationModel, Long> {
    @Override
    @NonNull
    Optional<LocationModel> findById(@NonNull Long aLong);

    // update location by id
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE LocationModel l SET l.latitude = :latitude, l.longitude = :longitude WHERE l.id = :id")
    void updateLocationById(@Param("id") Long id, @Param("latitude") Double latitude, @Param("longitude") Double longitude);
}
