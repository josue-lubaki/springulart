package ca.ghostteam.springulart.security.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ghostteam.springulart.config.bean.JwtConfig;
import org.junit.jupiter.api.Test;

class JwtConfigTest {
    @Test
    void testConstructor() {
        assertEquals("Authorization", (new JwtConfig()).getAuthorizationHeader());
    }
}

