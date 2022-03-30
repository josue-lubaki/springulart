package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.LocationDTO;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.repository.LocationRepository;
import ca.ghostteam.springulart.service.LocationService;
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

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Optional<LocationDTO> findById(Long id) {
        return Optional.of(converterModelToDTO(locationRepository
                        .findById(id)
                        .stream()
                        .findFirst()
                        .get()
                ));
    }

    @Override
    public Optional<LocationDTO> save(LocationDTO Location) {
        return Optional.of(
                converterModelToDTO(
                        locationRepository.save(converterDtoToModel(Location))
                )
        );
    }

    private LocationDTO converterModelToDTO(LocationModel locationModel){
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(locationModel.getId());
        locationDTO.setLatitude(locationModel.getLatitude());
        locationDTO.setLongitude(locationModel.getLongitude());

        return locationDTO;
    }

    private LocationModel converterDtoToModel(LocationDTO locationDTO){
        LocationModel location = new LocationModel();
        location.setId(null);
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());

        return location;
    }
}
