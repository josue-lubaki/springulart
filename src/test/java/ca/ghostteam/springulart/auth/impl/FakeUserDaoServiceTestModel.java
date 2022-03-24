package ca.ghostteam.springulart.auth.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.service.impl.FakeUserDaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FakeUserDaoService.class})
@ExtendWith(SpringExtension.class)
class FakeUserDaoServiceTestModel {
    @Autowired
    private FakeUserDaoService fakeUserDaoService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testSelectUserByUsername() {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertFalse(this.fakeUserDaoService.selectUserByUsername("janedoe").isPresent());
        verify(this.passwordEncoder, atLeast(1)).encode((CharSequence) any());
    }
}

