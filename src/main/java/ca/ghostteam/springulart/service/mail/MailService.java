package ca.ghostteam.springulart.service.mail;

import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDate;

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
    void modificationReservation(String emailBarber, String fullNameBarber, String fullNameClient, String newDateReservation, String newTimeReservation);
}
