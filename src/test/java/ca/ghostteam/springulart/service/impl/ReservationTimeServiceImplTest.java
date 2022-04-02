package ca.ghostteam.springulart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.ReservationTimeDTO;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.repository.ReservationTimeRepository;

import java.util.HashSet;
import java.util.Optional;

import ca.ghostteam.springulart.service.reservationtime.ReservationTimeServiceImpl;

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

    @Test
    void testFindById() {
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new HashSet<>());
        Optional<ReservationTimeModel> ofResult = Optional.of(reservationTimeModel);
        when(this.reservationTimeRepository.findById(any())).thenReturn(ofResult);
        Optional<ReservationTimeDTO> actualFindByIdResult = this.reservationTimeServiceImpl.findById(123L);
        assertTrue(actualFindByIdResult.isPresent());
        ReservationTimeDTO getResult = actualFindByIdResult.get();
        assertEquals(1, getResult.getHours().intValue());
        assertEquals(1, getResult.getMinutes().intValue());
        assertEquals(123L, getResult.getId());
        verify(this.reservationTimeRepository).findById(any());
    }

    @Test
    void testSave() {
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new HashSet<>());
        when(this.reservationTimeRepository.save(any())).thenReturn(reservationTimeModel);

        ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
        reservationTimeDTO.setHours(1);
        reservationTimeDTO.setId(123L);
        reservationTimeDTO.setMinutes(1);
        Optional<ReservationTimeDTO> actualSaveResult = this.reservationTimeServiceImpl.save(reservationTimeDTO);
        assertTrue(actualSaveResult.isPresent());
        ReservationTimeDTO getResult = actualSaveResult.get();
        assertEquals(1, getResult.getHours().intValue());
        assertEquals(1, getResult.getMinutes().intValue());
        assertEquals(123L, getResult.getId());
        verify(this.reservationTimeRepository).save(any());
    }

    @Test
    void testUpdate() {
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new HashSet<>());
        Optional<ReservationTimeModel> ofResult = Optional.of(reservationTimeModel);
        when(this.reservationTimeRepository.findById(any())).thenReturn(ofResult);
        doNothing().when(this.reservationTimeRepository)
                .updateReservationTimeById(any(), any(), any());

        ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
        reservationTimeDTO.setHours(1);
        reservationTimeDTO.setId(123L);
        reservationTimeDTO.setMinutes(1);
        Optional<ReservationTimeDTO> actualUpdateResult = this.reservationTimeServiceImpl.update(123L, reservationTimeDTO);
        assertTrue(actualUpdateResult.isPresent());
        ReservationTimeDTO getResult = actualUpdateResult.get();
        assertEquals(1, getResult.getHours().intValue());
        assertEquals(1, getResult.getMinutes().intValue());
        assertEquals(123L, getResult.getId());
        verify(this.reservationTimeRepository).findById(any());
        verify(this.reservationTimeRepository).updateReservationTimeById(any(), any(), any());
    }
}

