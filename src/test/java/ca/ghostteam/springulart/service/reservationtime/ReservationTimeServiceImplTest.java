package ca.ghostteam.springulart.service.reservationtime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.ReservationTimeDTO;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.repository.ReservationTimeRepository;

import java.util.ArrayList;
import java.util.Optional;

import ca.ghostteam.springulart.service.reservationtime.impl.ReservationTimeServiceImpl;
import ca.ghostteam.springulart.tools.UtilsReservationTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReservationTimeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ReservationTimeServiceImplTest {
    @MockBean
    private ReservationTimeRepository reservationTimeRepository;

    @Autowired
    private ReservationTimeServiceImpl reservationTimeServiceImpl;

    @MockBean
    private UtilsReservationTime utilsReservationTime;

    @Test
    void testFindById() {
        ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
        reservationTimeDTO.setHours(1);
        reservationTimeDTO.setId(123L);
        reservationTimeDTO.setMinutes(1);
        when(this.utilsReservationTime.converterModelToDTO(any())).thenReturn(reservationTimeDTO);

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new ArrayList<>());
        Optional<ReservationTimeModel> ofResult = Optional.of(reservationTimeModel);
        when(this.reservationTimeRepository.findById(any())).thenReturn(ofResult);
        assertTrue(this.reservationTimeServiceImpl.findById(123L).isPresent());
        verify(this.utilsReservationTime).converterModelToDTO(any());
        verify(this.reservationTimeRepository).findById(any());
    }

    @Test
    void testSave() {
        ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
        reservationTimeDTO.setHours(1);
        reservationTimeDTO.setId(123L);
        reservationTimeDTO.setMinutes(1);

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new ArrayList<>());
        when(this.utilsReservationTime.converterModelToDTO(any())).thenReturn(reservationTimeDTO);
        when(this.utilsReservationTime.converterDtoToModel(any())).thenReturn(reservationTimeModel);

        ReservationTimeModel reservationTimeModel1 = new ReservationTimeModel();
        reservationTimeModel1.setHours(1);
        reservationTimeModel1.setId(123L);
        reservationTimeModel1.setMinutes(1);
        reservationTimeModel1.setReservationModel(new ArrayList<>());
        when(this.reservationTimeRepository.save(any())).thenReturn(reservationTimeModel1);

        ReservationTimeDTO reservationTimeDTO1 = new ReservationTimeDTO();
        reservationTimeDTO1.setHours(1);
        reservationTimeDTO1.setId(123L);
        reservationTimeDTO1.setMinutes(1);
        assertTrue(this.reservationTimeServiceImpl.save(reservationTimeDTO1).isPresent());
        verify(this.utilsReservationTime).converterModelToDTO(any());
        verify(this.utilsReservationTime).converterDtoToModel(any());
        verify(this.reservationTimeRepository).save(any());
    }

    @Test
    void testUpdate() {
        ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
        reservationTimeDTO.setHours(1);
        reservationTimeDTO.setId(123L);
        reservationTimeDTO.setMinutes(1);
        when(this.utilsReservationTime.converterModelToDTO(any())).thenReturn(reservationTimeDTO);

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new ArrayList<>());
        Optional<ReservationTimeModel> ofResult = Optional.of(reservationTimeModel);
        when(this.reservationTimeRepository.findById(any())).thenReturn(ofResult);
        doNothing().when(this.reservationTimeRepository)
                .updateReservationTimeById(any(), any(), any());

        ReservationTimeDTO reservationTimeDTO1 = new ReservationTimeDTO();
        reservationTimeDTO1.setHours(1);
        reservationTimeDTO1.setId(123L);
        reservationTimeDTO1.setMinutes(1);
        assertTrue(this.reservationTimeServiceImpl.update(123L, reservationTimeDTO1).isPresent());
        verify(this.utilsReservationTime).converterModelToDTO(any());
        verify(this.reservationTimeRepository).findById(any());
        verify(this.reservationTimeRepository).updateReservationTimeById(any(), any(), any());
    }

}

