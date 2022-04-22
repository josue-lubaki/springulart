package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.HaircutModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-21
 */
@DataJpaTest
class HaircutRepositoryTest {

    @Autowired
    private HaircutRepository haircutRepository;

    @AfterEach
    void tearDown() {
        this.haircutRepository.deleteAll();
    }

    @Test
    void findAll() {
        // given
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setTitle("Haircut");
        haircutModel.setDescription("Description");
        haircutModel.setPrice(10);
        haircutModel.setEstimatedTime("10 minutes");
        haircutModel.setImageURL("https://www.google.com");

        // when
        this.haircutRepository.save(haircutModel);

        // then
        assertEquals(1, this.haircutRepository.findAll().size());
    }

    @Test
    void findById() {
        // given
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setTitle("Haircut");
        haircutModel.setDescription("Description");
        haircutModel.setPrice(10);
        haircutModel.setEstimatedTime("10 minutes");
        haircutModel.setImageURL("https://www.google.com");

        this.haircutRepository.save(haircutModel);

        // when
        HaircutModel haircutModel1 = this.haircutRepository.findById(haircutModel.getId()).get();

        // then
        assertEquals(haircutModel, haircutModel1);
    }

    @Test
    void deleteById() {
        // given
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setTitle("Haircut");
        haircutModel.setDescription("Description");
        haircutModel.setPrice(10);
        haircutModel.setEstimatedTime("10 minutes");
        haircutModel.setImageURL("https://www.google.com");

        this.haircutRepository.save(haircutModel);

        // when
        this.haircutRepository.deleteById(haircutModel.getId());

        // then
        assertEquals(0, this.haircutRepository.findAll().size());
    }

    @Test
    void save() {
        // given
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setTitle("Haircut");
        haircutModel.setDescription("Description");
        haircutModel.setPrice(10);
        haircutModel.setEstimatedTime("10 minutes");
        haircutModel.setImageURL("https://www.google.com");

        // when
        this.haircutRepository.save(haircutModel);

        // then
        assertEquals(1, this.haircutRepository.findAll().size());
    }
}