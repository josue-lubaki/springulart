package ca.ghostteam.springulart.service.haircut;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.repository.HaircutRepository;

import java.util.ArrayList;
import java.util.Optional;

import ca.ghostteam.springulart.service.haircut.impl.HaircutServiceImpl;

import ca.ghostteam.springulart.tools.UtilsHaircut;
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

    @MockBean
    private UtilsHaircut utilsHaircut;

    @Test
    void testFindAllHaircuts() {
        when(this.haircutRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.haircutServiceImpl.findAllHaircuts().isEmpty());
        verify(this.haircutRepository).findAll();
    }

    @Test
    void testFindHaircutById() {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");
        when(this.utilsHaircut.converterHaircutModelToHaircutDto(any())).thenReturn(haircutDTO);

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new ArrayList<>());
        haircutModel.setTitle("Dr");
        Optional<HaircutModel> ofResult = Optional.of(haircutModel);
        when(this.haircutRepository.findById(any())).thenReturn(ofResult);
        assertTrue(this.haircutServiceImpl.findHaircutById("42").isPresent());
        verify(this.utilsHaircut).converterHaircutModelToHaircutDto(any());
        verify(this.haircutRepository).findById(any());
    }

    @Test
    void testSaveHaircut() {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new ArrayList<>());
        haircutModel.setTitle("Dr");
        when(this.utilsHaircut.converterHaircutModelToHaircutDto(any())).thenReturn(haircutDTO);
        when(this.utilsHaircut.converterHaircutDtoToHaircutModel(any())).thenReturn(haircutModel);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new ArrayList<>());
        haircutModel1.setTitle("Dr");
        when(this.haircutRepository.save(any())).thenReturn(haircutModel1);

        HaircutDTO haircutDTO1 = new HaircutDTO();
        haircutDTO1.setDescription("The characteristics of someone or something");
        haircutDTO1.setEstimatedTime("Estimated Time");
        haircutDTO1.setId("42");
        haircutDTO1.setImageURL("https://example.org/example");
        haircutDTO1.setPrice(1);
        haircutDTO1.setTitle("Dr");
        assertTrue(this.haircutServiceImpl.saveHaircut(haircutDTO1).isPresent());
        verify(this.utilsHaircut).converterHaircutModelToHaircutDto(any());
        verify(this.utilsHaircut).converterHaircutDtoToHaircutModel(any());
        verify(this.haircutRepository).save(any());
    }

    @Test
    void testUpdateHaircut() {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new ArrayList<>());
        haircutModel.setTitle("Dr");
        when(this.utilsHaircut.converterHaircutModelToHaircutDto(any())).thenReturn(haircutDTO);
        when(this.utilsHaircut.converterHaircutDtoToHaircutModel(any())).thenReturn(haircutModel);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new ArrayList<>());
        haircutModel1.setTitle("Dr");
        Optional<HaircutModel> ofResult = Optional.of(haircutModel1);

        HaircutModel haircutModel2 = new HaircutModel();
        haircutModel2.setDescription("The characteristics of someone or something");
        haircutModel2.setEstimatedTime("Estimated Time");
        haircutModel2.setId("42");
        haircutModel2.setImageURL("https://example.org/example");
        haircutModel2.setPrice(1);
        haircutModel2.setReservationModel(new ArrayList<>());
        haircutModel2.setTitle("Dr");
        when(this.haircutRepository.save(any())).thenReturn(haircutModel2);
        when(this.haircutRepository.findById(any())).thenReturn(ofResult);

        HaircutDTO haircutDTO1 = new HaircutDTO();
        haircutDTO1.setDescription("The characteristics of someone or something");
        haircutDTO1.setEstimatedTime("Estimated Time");
        haircutDTO1.setId("42");
        haircutDTO1.setImageURL("https://example.org/example");
        haircutDTO1.setPrice(1);
        haircutDTO1.setTitle("Dr");
        assertTrue(this.haircutServiceImpl.updateHaircut("42", haircutDTO1).isPresent());
        verify(this.utilsHaircut, atLeast(1)).converterHaircutModelToHaircutDto(any());
        verify(this.utilsHaircut).converterHaircutDtoToHaircutModel(any());
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

