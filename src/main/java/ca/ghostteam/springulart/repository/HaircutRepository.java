package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.HaircutModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface HaircutRepository extends CrudRepository<HaircutModel, String> {
    @Override
    List<HaircutModel> findAll();

    Optional<HaircutModel> findById(String id);

    @Modifying
    @Query("DELETE FROM HaircutModel h WHERE h.id = ?1")
    void deleteById(@NonNull String aLong);

    @Override
    @NonNull HaircutModel save(@NonNull HaircutModel entity);
}
