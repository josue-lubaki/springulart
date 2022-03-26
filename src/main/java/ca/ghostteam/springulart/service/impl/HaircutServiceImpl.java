package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.repository.HaircutDao;
import ca.ghostteam.springulart.service.HaircutService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-26
 */
@Service("haircuts-service-fake")
public class HaircutServiceImpl implements HaircutService {

    private final HaircutDao haircutDao;

    public HaircutServiceImpl(
            @Qualifier("fake-repository-haircuts") HaircutDao haircutDao) {
        this.haircutDao = haircutDao;
    }

    @Override
    public List<HaircutDTO> findAllHaircuts() {
        return haircutDao
                .findAll()
                .stream()
                .map(this::converterHaircutModelToHaircutDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HaircutDTO> findHaircutById(String id) {
        return haircutDao
                .findById(id)
                .map(this::converterHaircutModelToHaircutDto);
    }

    @Override
    public Optional<HaircutDTO> saveHaircut(HaircutDTO haircutDTO) {
        return haircutDao
                .save(converterHaircutDtoToHaircutModel(haircutDTO))
                .map(this::converterHaircutModelToHaircutDto);
    }

    /**
     * Method to convert a haircutDTO to a haircutModel
     * @param haircutDTO the haircutDTO to convert
     * @return the haircutModel
     */
    private HaircutModel converterHaircutDtoToHaircutModel(HaircutDTO haircutDTO) {
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
    private HaircutDTO converterHaircutModelToHaircutDto(HaircutModel haircutModel) {
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
