package ca.ghostteam.springulart.service.mail;

import org.springframework.scheduling.annotation.Async;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
public interface MailService {

    @Async
    void resetPassword(String email, String temporaryPassword);
}
