package ca.ghostteam.springulart.service;

import ca.ghostteam.springulart.dto.LocationDTO;
import ca.ghostteam.springulart.model.LocationModel;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */
public interface LocationService {
    Optional<LocationDTO> findById(Long id);
    Optional<LocationModel> save(LocationDTO Location);
}
