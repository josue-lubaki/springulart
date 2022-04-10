package ca.ghostteam.springulart.controller.credential;

import ca.ghostteam.springulart.dto.CredentialDTO;
import ca.ghostteam.springulart.service.credential.CredentialService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/api/v1/credential")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping("/{id}")
    public CredentialDTO getCredential(@PathVariable("id") Long id) {
        return credentialService
                .findCredentialUserById(id)
                .orElseThrow(() -> new IllegalStateException("Credential not found"));
    }
}
