package ca.ghostteam.springulart.service.haircut;

import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.repository.HaircutRepository;
import ca.ghostteam.springulart.service.haircut.impl.HaircutServiceImpl;
import ca.ghostteam.springulart.tools.UtilsHaircut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HaircutServiceImplTest {

    @Mock
    private HaircutRepository haircutRepository;

    @Mock
    private UtilsHaircut utilsHaircut;

    private HaircutService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new HaircutServiceImpl(haircutRepository, utilsHaircut);
    }

    @Test
    void canGetAllHaircuts(){
        //when
        underTest.findAllHaircuts();

        // then
        verify(haircutRepository).findAll();
    }

    @Test
    void canGetHaircutById(){
        //given
        HaircutModel haircutModel = new HaircutModel();
        when(haircutRepository.findById(any())).thenReturn(Optional.of(haircutModel));

        //when
        underTest.findHaircutById(any());

        //then
        verify(haircutRepository).findById(any());
    }

    @Test
    void canDeleteById(){
        //when
        underTest.deleteHaircut(any());

        //then
        verify(haircutRepository).deleteById(any());
    }

    @Test
    void canExistsHaircutById(){
        //given
        when(haircutRepository.existsById(any())).thenReturn(true);

        //when
        underTest.existsHaircutById(any());

        //then
        verify(haircutRepository).existsById(any());
    }
}

