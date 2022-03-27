package ca.ghostteam.springulart.controller.haircut;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.service.HaircutService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {HaircutManagementController.class})
@ExtendWith(SpringExtension.class)
class HaircutManagementControllerTest {
    @Autowired
    private HaircutManagementController haircutManagementController;

    @MockBean
    private HaircutService haircutService;

    @Test
    void testCreateHaircut() throws Exception {
        when(this.haircutService.findAllHaircuts()).thenReturn(new ArrayList<>());

        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(haircutDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/management/api/v1/haircuts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.haircutManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateHaircut() throws Exception {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");
        Optional<HaircutDTO> ofResult = Optional.of(haircutDTO);
        when(this.haircutService.updateHaircut((String) any(), (HaircutDTO) any())).thenReturn(ofResult);

        HaircutDTO haircutDTO1 = new HaircutDTO();
        haircutDTO1.setDescription("The characteristics of someone or something");
        haircutDTO1.setEstimatedTime("Estimated Time");
        haircutDTO1.setId("42");
        haircutDTO1.setImageURL("https://example.org/example");
        haircutDTO1.setPrice(1);
        haircutDTO1.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(haircutDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/management/api/v1/haircuts/{haircutId}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.haircutManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"imageURL\":\"https://example.org/example\",\"price\":1,\"title\":\"Dr\",\"estimatedTime\":\"Estimated"
                                        + " Time\",\"description\":\"The characteristics of someone or something\"}"));
    }

    @Test
    void testDeleteHaircut() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/management/api/v1/haircuts/{haircutId}", "42");
        MockMvcBuilders.standaloneSetup(this.haircutManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteHaircut2() throws Exception {
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders
                .delete("/management/api/v1/haircuts/{haircutId}", "42");
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.haircutManagementController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetHaircut() throws Exception {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");
        Optional<HaircutDTO> ofResult = Optional.of(haircutDTO);
        when(this.haircutService.findHaircutById((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/management/api/v1/haircuts/{id}", "42");
        MockMvcBuilders.standaloneSetup(this.haircutManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"imageURL\":\"https://example.org/example\",\"price\":1,\"title\":\"Dr\",\"estimatedTime\":\"Estimated"
                                        + " Time\",\"description\":\"The characteristics of someone or something\"}"));
    }

    @Test
    void testGetHaircut2() throws Exception {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");
        Optional<HaircutDTO> ofResult = Optional.of(haircutDTO);
        when(this.haircutService.findHaircutById((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/management/api/v1/haircuts/{id}", "42");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.haircutManagementController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"imageURL\":\"https://example.org/example\",\"price\":1,\"title\":\"Dr\",\"estimatedTime\":\"Estimated"
                                        + " Time\",\"description\":\"The characteristics of someone or something\"}"));
    }

    @Test
    void testGetHaircuts() throws Exception {
        when(this.haircutService.findAllHaircuts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/management/api/v1/haircuts");
        MockMvcBuilders.standaloneSetup(this.haircutManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetHaircuts2() throws Exception {
        when(this.haircutService.findAllHaircuts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/management/api/v1/haircuts");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.haircutManagementController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

