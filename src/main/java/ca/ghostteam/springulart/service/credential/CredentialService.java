package ca.ghostteam.springulart.service.credential;

import ca.ghostteam.springulart.dto.CredentialDTO;
import ca.ghostteam.springulart.model.CredentialModel;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-28
 */
public interface CredentialService {
    Optional<CredentialDTO> findCredentialUserById(Long id);
    Optional<CredentialDTO> saveCredential(CredentialModel credential);
}
