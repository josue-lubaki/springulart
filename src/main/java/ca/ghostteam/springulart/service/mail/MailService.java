package ca.ghostteam.springulart.service.mail;

import ca.ghostteam.springulart.dto.UserDTO;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
public interface MailService {

    @Async
    void resetPassword(UserDTO user, String temporaryPassword);

    @Async
    void welcomeMessage(String email, String fullname);

    @Async
    void notificationModificationReservation(String emailBarber, String fullNameBarber, String fullNameClient, String newDateReservation, String newTimeReservation);

    @Async
    void notificationDeletedReservation(String emailBarber, String fullNameBarber, String fullNameClient, String newDateReservation, String newTimeReservation);
}
