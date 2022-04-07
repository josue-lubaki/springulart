package ca.ghostteam.springulart.service.location.impl;

import ca.ghostteam.springulart.dto.LocationDTO;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.repository.LocationRepository;
import ca.ghostteam.springulart.service.location.LocationService;
import ca.ghostteam.springulart.tools.UtilsLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final UtilsLocation utils;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository,
                               UtilsLocation utils) {
        this.locationRepository = locationRepository;
        this.utils = utils;
    }

    @Override
    public Optional<LocationDTO> findById(Long id) {
        return Optional.of(utils.converterModelToDTO(locationRepository
                        .findById(id)
                        .get()
                ));
    }

    @Override
    public Optional<LocationDTO> save(LocationDTO Location) {
        return Optional.of(
                utils.converterModelToDTO(
                        locationRepository.save(utils.converterDtoToModel(Location))
                )
        );
    }

    @Override
    public Optional<LocationDTO> update(Long id, LocationDTO Location) {
        locationRepository.updateLocationById(id, Location.getLatitude(), Location.getLongitude());
        LocationModel locationModel =
                locationRepository
                        .findById(id)
                        .get();

        return Optional.of(utils.converterModelToDTO(locationModel));
    }
}
