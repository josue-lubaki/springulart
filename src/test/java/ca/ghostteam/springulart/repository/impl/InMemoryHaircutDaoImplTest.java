package ca.ghostteam.springulart.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ghostteam.springulart.model.HaircutModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InMemoryHaircutDaoImpl.class})
@ExtendWith(SpringExtension.class)
class InMemoryHaircutDaoImplTest {
    @Autowired
    private InMemoryHaircutDaoImpl inMemoryHaircutDaoImpl;

    @Test
    void testConstructor() {
        assertEquals(8, (new InMemoryHaircutDaoImpl()).findAll().size());
    }

    @Test
    void testSave() {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");
        assertTrue(this.inMemoryHaircutDaoImpl.save(haircutModel).isPresent());
    }

    @Test
    void testSave2() {
        HaircutModel haircutModel = mock(HaircutModel.class);
        doNothing().when(haircutModel).setDescription((String) any());
        doNothing().when(haircutModel).setEstimatedTime((String) any());
        doNothing().when(haircutModel).setId((String) any());
        doNothing().when(haircutModel).setImageURL((String) any());
        doNothing().when(haircutModel).setPrice((Integer) any());
        doNothing().when(haircutModel).setTitle((String) any());
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");
        assertTrue(this.inMemoryHaircutDaoImpl.save(haircutModel).isPresent());
        verify(haircutModel).setDescription((String) any());
        verify(haircutModel).setEstimatedTime((String) any());
        verify(haircutModel, atLeast(1)).setId((String) any());
        verify(haircutModel).setImageURL((String) any());
        verify(haircutModel).setPrice((Integer) any());
        verify(haircutModel).setTitle((String) any());
    }
}

