package ca.ghostteam.springulart.service.reservationtime.impl;

import ca.ghostteam.springulart.dto.ReservationTimeDTO;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import org.springframework.stereotype.Component;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-04
 */
@Component
public class UtilsReservationTime {

    /**
     * Method to convert a ReservationTimeModel to a ReservationTimeDTO
     * @param reservationModel ReservationTimeModel to be converted to ReservationTimeDTO
     * @return ReservationTimeDTO
     * */
    public ReservationTimeDTO converterModelToDTO(ReservationTimeModel reservationModel){
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
    public ReservationTimeModel converterDtoToModel(ReservationTimeDTO reservationTimeDTO){
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setId(null);
        reservationTimeModel.setHours(reservationTimeDTO.getHours());
        reservationTimeModel.setMinutes(reservationTimeDTO.getMinutes());

        return reservationTimeModel;
    }
}
