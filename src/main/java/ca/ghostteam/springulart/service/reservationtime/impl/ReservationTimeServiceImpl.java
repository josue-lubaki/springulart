package ca.ghostteam.springulart.service.reservationtime.impl;

import ca.ghostteam.springulart.dto.ReservationTimeDTO;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.repository.ReservationTimeRepository;
import ca.ghostteam.springulart.service.reservationtime.ReservationTimeService;
import ca.ghostteam.springulart.tools.UtilsReservationTime;
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
    private final UtilsReservationTime utils;

    @Autowired
    public ReservationTimeServiceImpl(ReservationTimeRepository reservationTimeService,
                                      UtilsReservationTime utils) {
        this.reservationTimeRepository = reservationTimeService;
        this.utils = utils;
    }

    @Override
    public Optional<ReservationTimeDTO> findById(Long id) {
        ReservationTimeModel reservationModel = reservationTimeRepository
                .findById(id)
                .get();

        return Optional.of(utils.converterModelToDTO(reservationModel));
    }

    @Override
    public Optional<ReservationTimeDTO> save(ReservationTimeDTO reservationDTO) {
        ReservationTimeModel reservationModelSaved =
                reservationTimeRepository.save(utils.converterDtoToModel(reservationDTO));

        ReservationTimeDTO reservationTimeDTOSaved = utils.converterModelToDTO(reservationModelSaved);
        return Optional.of(reservationTimeDTOSaved);
    }

    @Override
    public Optional<ReservationTimeDTO> update(Long id, ReservationTimeDTO reservationModel) {
        reservationTimeRepository.updateReservationTimeById(id, reservationModel.getHours(), reservationModel.getMinutes());
        // get the updated reservation time
        ReservationTimeModel reservationTimeModel = reservationTimeRepository.findById(id).get();
        return Optional.of(utils.converterModelToDTO(reservationTimeModel));
    }
}
