package ca.ghostteam.springulart.security.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JwtConfigTest {
    @Test
    void testConstructor() {
        assertEquals("Authorization", (new JwtConfig()).getAuthorizationHeader());
    }
}

