package ca.ghostteam.springulart.service.haircut.impl;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.model.HaircutModel;
import org.springframework.stereotype.Component;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-04
 */
@Component
public class UtilsHaircutService {

    /**
     * Method to convert a haircutDTO to a haircutModel
     * @param haircutDTO the haircutDTO to convert
     * @return the haircutModel
     */
    public HaircutModel converterHaircutDtoToHaircutModel(HaircutDTO haircutDTO) {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setId(haircutDTO.getId());
        haircutModel.setTitle(haircutDTO.getTitle());
        haircutModel.setDescription(haircutDTO.getDescription());
        haircutModel.setPrice(haircutDTO.getPrice());
        haircutModel.setImageURL(haircutDTO.getImageURL());
        haircutModel.setEstimatedTime(haircutDTO.getEstimatedTime());
        return haircutModel;
    }

    /**
     * Method to convert a haircutModel to a haircutDTO
     * @param haircutModel the haircutModel to convert
     * @return the haircutDTO
     */
    public HaircutDTO converterHaircutModelToHaircutDto(HaircutModel haircutModel) {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setId(haircutModel.getId());
        haircutDTO.setTitle(haircutModel.getTitle());
        haircutDTO.setDescription(haircutModel.getDescription());
        haircutDTO.setPrice(haircutModel.getPrice());
        haircutDTO.setImageURL(haircutModel.getImageURL());
        haircutDTO.setEstimatedTime(haircutModel.getEstimatedTime());
        return haircutDTO;
    }
}
