package ca.ghostteam.springulart.controller.user;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.bean.JwtConfig;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.security.jwt.filter.JwtTokenVerifier;
import ca.ghostteam.springulart.service.UserService;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class, JwtConfig.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private HttpServletRequest httpServletRequest;

    @Autowired
    private JwtConfig jwtConfig;

    @MockBean
    private JwtTokenVerifier jwtTokenVerifier;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void testDeleteMyAccount3() throws Exception {
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(1);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUsername("janedoe");

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setCredentials(credentialModel);
        when(this.userService.loadUserByUsername((String) any())).thenReturn(userDetailsDTO);
        when(this.jwtTokenVerifier.decodeJWT((String) any(), (String) any())).thenReturn(mock(DecodedJWT.class));
        when(this.jwtTokenVerifier.extractJwtToken((HttpServletRequest) any())).thenReturn("ABC123");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/{userId}", "Uri Vars",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetUser() throws Exception {
        when(this.userService.findAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/{userId}", "Uri Vars",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

