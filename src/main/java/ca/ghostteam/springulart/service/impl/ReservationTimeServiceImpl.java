package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.ReservationTimeDTO;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.repository.ReservationTimeRepository;
import ca.ghostteam.springulart.service.ReservationTimeService;
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
        return Optional.of(
                converterModelToDTO(
                        reservationTimeRepository
                        .findById(id)
                        .stream()
                        .findFirst()
                        .get()
                )
        );
    }

    @Override
    public Optional<ReservationTimeModel> save(ReservationTimeDTO reservationDTO) {
        ReservationTimeModel reservationTimeModelSaved =
                reservationTimeRepository.save(converterDtoToModel(reservationDTO));
        return Optional.of(reservationTimeModelSaved);
    }

    private ReservationTimeDTO converterModelToDTO(ReservationTimeModel reservationModel){
        ReservationTimeDTO reservationDTO = new ReservationTimeDTO();
        reservationDTO.setId(reservationModel.getId());
        reservationDTO.setHours(reservationModel.getHours());
        reservationDTO.setMinutes(reservationModel.getMinutes());

        return reservationDTO;
    }

    private ReservationTimeModel converterDtoToModel(ReservationTimeDTO reservationTimeDTO){
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(reservationTimeDTO.getHours());
        reservationTimeModel.setMinutes(reservationTimeDTO.getMinutes());

        return reservationTimeModel;
    }
}
