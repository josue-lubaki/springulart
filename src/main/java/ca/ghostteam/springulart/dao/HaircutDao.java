package ca.ghostteam.springulart.dao;

import ca.ghostteam.springulart.model.HaircutModel;

import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-26
 */
public interface HaircutDao {
    List<HaircutModel> findAll();
    Optional<HaircutModel> findById(String id);
    Optional<HaircutModel> save(HaircutModel haircutModel);
    void delete(String id);
    Optional<HaircutModel> update(String id, HaircutModel haircutModel);
}
