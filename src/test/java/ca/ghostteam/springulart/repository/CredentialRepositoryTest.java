package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.CredentialModel;
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
class CredentialRepositoryTest {

    @Autowired
    CredentialRepository credentialRepository;

    @Test
    void save() {
        // given
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setUsername("josue");
        credentialModel.setPassword("password");
        credentialModel.setGrantedAuthority("ROLE_CLIENT");

        this.credentialRepository.save(credentialModel);

        // when
        CredentialModel credentialModel1 = this.credentialRepository.findCredentialByUsername("josue");

        // then
        assertEquals(credentialModel.getUsername(), credentialModel1.getUsername());
    }

    @Test
    void updatePassword() {
        // given
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setUsername("josue");
        credentialModel.setPassword("password");
        credentialModel.setGrantedAuthority("ROLE_CLIENT");

        this.credentialRepository.save(credentialModel);

        // when
        CredentialModel credentialModel1 = this.credentialRepository.findCredentialByUsername("josue");
        credentialModel1.setPassword("newPassword");
        this.credentialRepository.save(credentialModel1);

        // then
        assertEquals("newPassword", this.credentialRepository.findCredentialByUsername("josue").getPassword());
    }

    @Test
    void findCredentialByUsername() {
        // given
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setUsername("josue");
        credentialModel.setPassword("password");
        credentialModel.setGrantedAuthority("ROLE_CLIENT");

        this.credentialRepository.save(credentialModel);

        // when
        CredentialModel credentialModel1 = this.credentialRepository.findCredentialByUsername("josue");

        // then
        assertEquals(credentialModel.getUsername(), credentialModel1.getUsername());
    }
}