package ca.ghostteam.springulart.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AuthDTOTest {
    @Test
    void testConstructor() {
        AuthDTO actualAuthDTO = new AuthDTO();
        actualAuthDTO.setPassword("iloveyou");
        actualAuthDTO.setUsername("janedoe");
        assertEquals("iloveyou", actualAuthDTO.getPassword());
        assertEquals("janedoe", actualAuthDTO.getUsername());
    }
}

