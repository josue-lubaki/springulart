package ca.ghostteam.springulart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.repository.HaircutRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import ca.ghostteam.springulart.service.haircut.HaircutServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HaircutServiceImpl.class})
@ExtendWith(SpringExtension.class)
class HaircutServiceImplTest {
    @MockBean
    private HaircutRepository haircutRepository;

    @Autowired
    private HaircutServiceImpl haircutServiceImpl;

    @Test
    void testFindAllHaircuts() {
        when(this.haircutRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.haircutServiceImpl.findAllHaircuts().isEmpty());
        verify(this.haircutRepository).findAll();
    }

    @Test
    void testFindAllHaircuts2() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");

        ArrayList<HaircutModel> haircutModelList = new ArrayList<>();
        haircutModelList.add(haircutModel);
        when(this.haircutRepository.findAll()).thenReturn(haircutModelList);
        assertEquals(1, this.haircutServiceImpl.findAllHaircuts().size());
        verify(this.haircutRepository).findAll();
    }

    @Test
    void testFindAllHaircuts3() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new HashSet<>());
        haircutModel1.setTitle("Dr");

        ArrayList<HaircutModel> haircutModelList = new ArrayList<>();
        haircutModelList.add(haircutModel1);
        haircutModelList.add(haircutModel);
        when(this.haircutRepository.findAll()).thenReturn(haircutModelList);
        assertEquals(2, this.haircutServiceImpl.findAllHaircuts().size());
        verify(this.haircutRepository).findAll();
    }

    @Test
    void testFindHaircutById() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");
        Optional<HaircutModel> ofResult = Optional.of(haircutModel);
        when(this.haircutRepository.findById(any())).thenReturn(ofResult);
        Optional<HaircutDTO> actualFindHaircutByIdResult = this.haircutServiceImpl.findHaircutById("42");
        assertTrue(actualFindHaircutByIdResult.isPresent());
        HaircutDTO getResult = actualFindHaircutByIdResult.get();
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Dr", getResult.getTitle());
        assertEquals(1, getResult.getPrice().intValue());
        assertEquals("https://example.org/example", getResult.getImageURL());
        assertEquals("42", getResult.getId());
        assertEquals("Estimated Time", getResult.getEstimatedTime());
        verify(this.haircutRepository).findById(any());
    }

    @Test
    void testFindHaircutById2() {
        Optional<HaircutModel> emptyResult = Optional.empty();
        when(this.haircutRepository.findById(any())).thenReturn(emptyResult);
        Optional<HaircutDTO> actualFindHaircutByIdResult = this.haircutServiceImpl.findHaircutById("42");
        assertSame(emptyResult, actualFindHaircutByIdResult);
        assertFalse(actualFindHaircutByIdResult.isPresent());
        verify(this.haircutRepository).findById(any());
    }

    @Test
    void testSaveHaircut() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");
        when(this.haircutRepository.save(any())).thenReturn(haircutModel);

        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");
        Optional<HaircutDTO> actualSaveHaircutResult = this.haircutServiceImpl.saveHaircut(haircutDTO);
        assertTrue(actualSaveHaircutResult.isPresent());
        HaircutDTO getResult = actualSaveHaircutResult.get();
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Dr", getResult.getTitle());
        assertEquals(1, getResult.getPrice().intValue());
        assertEquals("https://example.org/example", getResult.getImageURL());
        assertEquals("42", getResult.getId());
        assertEquals("Estimated Time", getResult.getEstimatedTime());
        verify(this.haircutRepository).save(any());
    }

    @Test
    void testUpdateHaircut() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");
        Optional<HaircutModel> ofResult = Optional.of(haircutModel);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new HashSet<>());
        haircutModel1.setTitle("Dr");
        when(this.haircutRepository.save(any())).thenReturn(haircutModel1);
        when(this.haircutRepository.findById(any())).thenReturn(ofResult);

        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");
        Optional<HaircutDTO> actualUpdateHaircutResult = this.haircutServiceImpl.updateHaircut("42", haircutDTO);
        assertTrue(actualUpdateHaircutResult.isPresent());
        HaircutDTO getResult = actualUpdateHaircutResult.get();
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Dr", getResult.getTitle());
        assertEquals(1, getResult.getPrice().intValue());
        assertEquals("https://example.org/example", getResult.getImageURL());
        assertEquals("42", getResult.getId());
        assertEquals("Estimated Time", getResult.getEstimatedTime());
        verify(this.haircutRepository).save(any());
        verify(this.haircutRepository).findById(any());
    }

    @Test
    void testDeleteHaircut() {
        doNothing().when(this.haircutRepository).deleteById(any());
        this.haircutServiceImpl.deleteHaircut("42");
        verify(this.haircutRepository).deleteById(any());
    }

    @Test
    void testExistsHaircutById() {
        when(this.haircutRepository.existsById(any())).thenReturn(true);
        assertTrue(this.haircutServiceImpl.existsHaircutById("42"));
        verify(this.haircutRepository).existsById(any());
    }

    @Test
    void testExistsHaircutById2() {
        when(this.haircutRepository.existsById(any())).thenReturn(false);
        assertFalse(this.haircutServiceImpl.existsHaircutById("42"));
        verify(this.haircutRepository).existsById(any());
    }
}

