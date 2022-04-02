package ca.ghostteam.springulart.service.reservationtime;

import ca.ghostteam.springulart.dto.ReservationTimeDTO;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.repository.ReservationTimeRepository;
import ca.ghostteam.springulart.service.reservationtime.ReservationTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */
@Service
public class ReservationTimeServiceImpl implements ReservationTimeService {

    private final ReservationTimeRepository reservationTimeRepository;

    @Autowired
    public ReservationTimeServiceImpl(ReservationTimeRepository reservationTimeService) {
        this.reservationTimeRepository = reservationTimeService;
    }

    @Override
    public Optional<ReservationTimeDTO> findById(Long id) {
        ReservationTimeModel reservationModel = reservationTimeRepository
                .findById(id)
                .stream()
                .findFirst()
                .get();

        return Optional.of(converterModelToDTO(reservationModel));
    }

    @Override
    public Optional<ReservationTimeDTO> save(ReservationTimeDTO reservationDTO) {
        ReservationTimeModel reservationModelSaved =
                reservationTimeRepository.save(converterDtoToModel(reservationDTO));

        ReservationTimeDTO reservationTimeDTOSaved = converterModelToDTO(reservationModelSaved);
        return Optional.of(reservationTimeDTOSaved);
    }

    @Override
    public Optional<ReservationTimeDTO> update(Long id, ReservationTimeDTO reservationModel) {
        reservationTimeRepository.updateReservationTimeById(id, reservationModel.getHours(), reservationModel.getMinutes());
        // get the updated reservation time
        ReservationTimeModel reservationTimeModel = reservationTimeRepository.findById(id).get();
        return Optional.of(converterModelToDTO(reservationTimeModel));
    }

    @Override
    public void delete(Long id) {

    }

    /**
     * Method to convert a ReservationTimeModel to a ReservationTimeDTO
     * @param reservationModel ReservationTimeModel to be converted to ReservationTimeDTO
     * @return ReservationTimeDTO
     * */
    private ReservationTimeDTO converterModelToDTO(ReservationTimeModel reservationModel){
        ReservationTimeDTO reservationDTO = new ReservationTimeDTO();
        reservationDTO.setId(reservationModel.getId());
        reservationDTO.setHours(reservationModel.getHours());
        reservationDTO.setMinutes(reservationModel.getMinutes());

        return reservationDTO;
    }

    /**
     * Method to convert a ReservationTimeDTO to a ReservationTimeModel
     * @param reservationTimeDTO ReservationTimeDTO to be converted to ReservationTimeModel
     * @return ReservationTimeModel
     * */
    private ReservationTimeModel converterDtoToModel(ReservationTimeDTO reservationTimeDTO){
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setId(null);
        reservationTimeModel.setHours(reservationTimeDTO.getHours());
        reservationTimeModel.setMinutes(reservationTimeDTO.getMinutes());

        return reservationTimeModel;
    }
}
