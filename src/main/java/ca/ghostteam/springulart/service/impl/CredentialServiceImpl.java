package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.CredentialDTO;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.repository.CredentialRepository;
import ca.ghostteam.springulart.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-28
 */
@Service
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialServiceImpl(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Optional<CredentialDTO> findCredentialUserById(Long id) {
        return credentialRepository
                .findById(id)
                .stream()
                .map(this::converterCrendentialModelToCredentialDTO)
                .findFirst();
    }

    @Override
    public Optional<CredentialDTO> saveCredential(CredentialModel credential) {
        return Optional.of(converterCrendentialModelToCredentialDTO(
                this.credentialRepository
                .save(credential)
        ));
    }

    /**
     * Method to convert a CredentialModel to a CredentialDTO
     * @param credentialModel CredentialModel to convert
     * @return CredentialDTO
     * */
    private CredentialDTO converterCrendentialModelToCredentialDTO(CredentialModel credentialModel){
        CredentialDTO credential = new CredentialDTO();
        credential.setId(credentialModel.getId());
        credential.setUsername(credentialModel.getUsername());
        credential.setPassword(credentialModel.getPassword());
        credential.setCreated(credentialModel.getCreated());
        credential.setUpdated(credentialModel.getUpdated());
        credential.setGrantedAuthority(credentialModel.getGrantedAuthority());
        return credential;
    }

    /**
     * Method to convert a CredentialDTO to a CredentialModel
     * @param credentialDTO CredentialDTO to convert
     * @return CredentialModel
     * */
    private CredentialModel converterCrendentialDtoToCredentialModel(CredentialDTO credentialDTO){
        CredentialModel credential = new CredentialModel();
        credential.setId(credentialDTO.getId());
        credential.setUsername(credentialDTO.getUsername());
        credential.setPassword(credentialDTO.getPassword());
        credential.setCreated(credentialDTO.getCreated());
        credential.setUpdated(credentialDTO.getUpdated());
        credential.setGrantedAuthority(credentialDTO.getGrantedAuthority());
        return credential;
    }
}
