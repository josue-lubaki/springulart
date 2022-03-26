package ca.ghostteam.springulart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.repository.HaircutDao;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HaircutServiceImpl.class})
@ExtendWith(SpringExtension.class)
class HaircutServiceImplTest {
    @MockBean(name = "fake-repository-haircuts")
    private HaircutDao haircutDao;

    @Autowired
    private HaircutServiceImpl haircutServiceImpl;

    @Test
    void testFindAllHaircuts() {
        when(this.haircutDao.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.haircutServiceImpl.findAllHaircuts().isEmpty());
        verify(this.haircutDao).findAll();
    }

    @Test
    void testFindAllHaircuts2() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");

        ArrayList<HaircutModel> haircutModelList = new ArrayList<>();
        haircutModelList.add(haircutModel);
        when(this.haircutDao.findAll()).thenReturn(haircutModelList);
        assertEquals(1, this.haircutServiceImpl.findAllHaircuts().size());
        verify(this.haircutDao).findAll();
    }

    @Test
    void testFindAllHaircuts3() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setTitle("Dr");

        ArrayList<HaircutModel> haircutModelList = new ArrayList<>();
        haircutModelList.add(haircutModel1);
        haircutModelList.add(haircutModel);
        when(this.haircutDao.findAll()).thenReturn(haircutModelList);
        assertEquals(2, this.haircutServiceImpl.findAllHaircuts().size());
        verify(this.haircutDao).findAll();
    }

    @Test
    void testFindHaircutById() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");
        Optional<HaircutModel> ofResult = Optional.of(haircutModel);
        when(this.haircutDao.findById((String) any())).thenReturn(ofResult);
        Optional<HaircutDTO> actualFindHaircutByIdResult = this.haircutServiceImpl.findHaircutById("42");
        assertTrue(actualFindHaircutByIdResult.isPresent());
        HaircutDTO getResult = actualFindHaircutByIdResult.get();
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Dr", getResult.getTitle());
        assertEquals(1, getResult.getPrice().intValue());
        assertEquals("https://example.org/example", getResult.getImageURL());
        assertEquals("42", getResult.getId());
        assertEquals("Estimated Time", getResult.getEstimatedTime());
        verify(this.haircutDao).findById((String) any());
    }

    @Test
    void testFindHaircutById2() {
        Optional<HaircutModel> emptyResult = Optional.empty();
        when(this.haircutDao.findById((String) any())).thenReturn(emptyResult);
        Optional<HaircutDTO> actualFindHaircutByIdResult = this.haircutServiceImpl.findHaircutById("42");
        assertSame(emptyResult, actualFindHaircutByIdResult);
        assertFalse(actualFindHaircutByIdResult.isPresent());
        verify(this.haircutDao).findById((String) any());
    }

    @Test
    void testSaveHaircut() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");
        Optional<HaircutModel> ofResult = Optional.of(haircutModel);
        when(this.haircutDao.save((HaircutModel) any())).thenReturn(ofResult);

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
        assertEquals(haircutModel.getDescription(), getResult.getDescription());
        assertEquals(haircutModel.getTitle(), getResult.getTitle());
        assertEquals(haircutModel.getPrice(), getResult.getPrice().intValue());
        assertEquals(haircutModel.getImageURL(), getResult.getImageURL());
        assertEquals(haircutModel.getId(), getResult.getId());
        assertEquals(haircutModel.getEstimatedTime(), getResult.getEstimatedTime());
        verify(this.haircutDao).save((HaircutModel) any());
    }

    @Test
    void testSaveHaircut2() {
        Optional<HaircutModel> emptyResult = Optional.empty();
        when(this.haircutDao.save((HaircutModel) any())).thenReturn(emptyResult);

        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");
        Optional<HaircutDTO> actualSaveHaircutResult = this.haircutServiceImpl.saveHaircut(haircutDTO);
        assertSame(emptyResult, actualSaveHaircutResult);
        assertFalse(actualSaveHaircutResult.isPresent());
        verify(this.haircutDao).save((HaircutModel) any());
    }
}

