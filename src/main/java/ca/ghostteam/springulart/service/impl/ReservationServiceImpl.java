package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.ReservationDTO;
import ca.ghostteam.springulart.model.ReservationModel;
import ca.ghostteam.springulart.repository.ReservationDao;
import ca.ghostteam.springulart.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-27
 */
@Service("reservations-service-fake")
public class ReservationServiceImpl implements ReservationService {

    private final ReservationDao reservationDao;

    public ReservationServiceImpl(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Override
    public List<ReservationDTO> findAll() {
        return reservationDao
                .findAll()
                .stream()
                .map(this::converterReservationModelToReservationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservationDTO> findById(String id) {
        return reservationDao
                .findById(id)
                .map(this::converterReservationModelToReservationDTO);
    }

    @Override
    public Optional<ReservationDTO> save(ReservationDTO reservation) {
        return reservationDao
                .save(converterReservationDtoToReservationModel(reservation))
                .map(this::converterReservationModelToReservationDTO);
    }

    @Override
    public Optional<ReservationDTO> update(String id, ReservationDTO reservation) {
        return reservationDao
                .update(id, converterReservationDtoToReservationModel(reservation))
                .map(this::converterReservationModelToReservationDTO);
    }

    @Override
    public void deleteById(String id) {
        reservationDao.deleteById(id);
    }

    /**
     * Method to convert a reservationModel to a reservationDTO
     * @param reservationModel the reservationModel to convert
     * @return ReservationDTO
     */
    private ReservationDTO converterReservationModelToReservationDTO(ReservationModel reservationModel) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservationModel.getId());
        reservationDTO.setReservationDate(reservationModel.getReservationDate());
        reservationDTO.setReservationTimeModel(reservationModel.getReservationTimeModel());
        reservationDTO.setHaircutModel(reservationModel.getHaircutModel());
        reservationDTO.setStatus(reservationModel.getStatus());
        reservationDTO.setClient(reservationModel.getClient());
        reservationDTO.setLocationModel(reservationModel.getLocationModel());

        return reservationDTO;
    }

    /**
     * Method to convert a ReservationDTO to a ReservationModel
     * @param reservationDTO the reservationDTO to convert
     * @return ReservationModel
     */
    private ReservationModel converterReservationDtoToReservationModel(ReservationDTO reservationDTO) {
        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setReservationDate(reservationDTO.getReservationDate());
        reservationModel.setReservationTimeModel(reservationDTO.getReservationTimeModel());
        reservationModel.setHaircutModel(reservationDTO.getHaircutModel());
        reservationModel.setStatus(reservationDTO.getStatus());
        reservationModel.setClient(reservationDTO.getClient());
        reservationModel.setLocationModel(reservationDTO.getLocationModel());

        return reservationModel;
    }
}