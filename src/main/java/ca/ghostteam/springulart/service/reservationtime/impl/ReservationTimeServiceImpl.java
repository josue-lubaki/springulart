package ca.ghostteam.springulart.service.reservationtime.impl;

import ca.ghostteam.springulart.dto.ReservationTimeDTO;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.repository.ReservationTimeRepository;
import ca.ghostteam.springulart.service.reservationtime.ReservationTimeService;
import ca.ghostteam.springulart.service.reservationtime.impl.UtilsReservationTime;
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
    private final UtilsReservationTime utilsReservationTime;

    @Autowired
    public ReservationTimeServiceImpl(ReservationTimeRepository reservationTimeService, UtilsReservationTime utilsReservationTime) {
        this.reservationTimeRepository = reservationTimeService;
        this.utilsReservationTime = utilsReservationTime;
    }

    @Override
    public Optional<ReservationTimeDTO> findById(Long id) {
        ReservationTimeModel reservationModel = reservationTimeRepository
                .findById(id)
                .stream()
                .findFirst()
                .get();

        return Optional.of(utilsReservationTime.converterModelToDTO(reservationModel));
    }

    @Override
    public Optional<ReservationTimeDTO> save(ReservationTimeDTO reservationDTO) {
        ReservationTimeModel reservationModelSaved =
                reservationTimeRepository.save(utilsReservationTime.converterDtoToModel(reservationDTO));

        ReservationTimeDTO reservationTimeDTOSaved = utilsReservationTime.converterModelToDTO(reservationModelSaved);
        return Optional.of(reservationTimeDTOSaved);
    }

    @Override
    public Optional<ReservationTimeDTO> update(Long id, ReservationTimeDTO reservationModel) {
        reservationTimeRepository.updateReservationTimeById(id, reservationModel.getHours(), reservationModel.getMinutes());
        // get the updated reservation time
        ReservationTimeModel reservationTimeModel = reservationTimeRepository.findById(id).get();
        return Optional.of(utilsReservationTime.converterModelToDTO(reservationTimeModel));
    }
}
