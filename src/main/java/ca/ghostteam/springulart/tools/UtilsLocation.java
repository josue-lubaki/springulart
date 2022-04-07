package ca.ghostteam.springulart.tools;

import ca.ghostteam.springulart.dto.LocationDTO;
import ca.ghostteam.springulart.model.LocationModel;
import org.springframework.stereotype.Component;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-04
 */
@Component
public class UtilsLocation {

    /**
     * Method to convert LocationModel to LocationDTO
     * @param locationModel LocationModel to convert
     * @return LocationDTO
     **/
    public LocationDTO converterModelToDTO(LocationModel locationModel){
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(locationModel.getId());
        locationDTO.setLatitude(locationModel.getLatitude());
        locationDTO.setLongitude(locationModel.getLongitude());

        return locationDTO;
    }

    /**
     * Method to convert LocationDTO to LocationModel
     * @param locationDTO LocationDTO to convert
     * @return LocationModel
     * */
    public LocationModel converterDtoToModel(LocationDTO locationDTO){
        LocationModel location = new LocationModel();
        location.setId(null);
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());

        return location;
    }
}
