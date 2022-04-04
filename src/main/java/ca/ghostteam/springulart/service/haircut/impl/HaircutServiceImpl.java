package ca.ghostteam.springulart.service.haircut.impl;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.repository.HaircutRepository;
import ca.ghostteam.springulart.service.haircut.HaircutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-26
 */
@Service
public class HaircutServiceImpl implements HaircutService {

    private final HaircutRepository haircutRepository;
    private final UtilsHaircutService utils;

    @Autowired
    public HaircutServiceImpl(
            HaircutRepository haircutRepository,
            UtilsHaircutService utils) {
        this.haircutRepository = haircutRepository;
        this.utils = utils;
    }

    @Override
    public List<HaircutDTO> findAllHaircuts() {
        return haircutRepository
                .findAll()
                .stream()
                .map(utils::converterHaircutModelToHaircutDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HaircutDTO> findHaircutById(String id) {
        return haircutRepository
                .findById(id)
                .map(utils::converterHaircutModelToHaircutDto);
    }

    @Override
    public Optional<HaircutDTO> saveHaircut(HaircutDTO haircutDTO) {
        return Optional.of(
                utils.converterHaircutModelToHaircutDto(
                        haircutRepository
                        .save(utils.converterHaircutDtoToHaircutModel(haircutDTO))
                )
        );
    }

    @Override
    public Optional<HaircutDTO> updateHaircut(String id, HaircutDTO haircutDToUpdated) {
        // retrieve haircut to update
        HaircutModel haircutModel = haircutRepository.findById(id).get();
        haircutModel.setDescription(haircutDToUpdated.getDescription());
        haircutModel.setPrice(haircutDToUpdated.getPrice());
        haircutModel.setTitle(haircutDToUpdated.getTitle());
        haircutModel.setImageURL(haircutDToUpdated.getImageURL());
        haircutModel.setEstimatedTime(haircutDToUpdated.getEstimatedTime());

        return saveHaircut(utils.converterHaircutModelToHaircutDto(haircutModel));
    }

    @Override
    public void deleteHaircut(String id) {
        haircutRepository.deleteById(id);
    }

    @Override
    public boolean existsHaircutById(String id) {
        return haircutRepository.existsById(id);
    }
}
