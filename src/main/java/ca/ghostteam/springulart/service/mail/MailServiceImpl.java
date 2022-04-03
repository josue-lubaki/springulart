package ca.ghostteam.springulart.service.mail;

import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final UserService userService;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public MailServiceImpl(JavaMailSender javaMailSender,
                           SpringTemplateEngine templateEngine,
                           UserService userService) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    @Async
    @Override
    public void resetPassword(String email, String temporaryPassword) {
        // get the full name of the user
        String fullname = getFullNameUserByEmail(email);

        // create the context for the template
        Context context = getContext();
        context.setVariable("password", temporaryPassword);
        context.setVariable("fullname", String.format("%s", fullname));

        // set content of message
        String content = templateEngine.process("emails/reset-password", context);

        // send message
        sendPlainTextMessage(email, "Réinitialisation de mot de passe", content);
    }

    /**
     * Method to get the full name of the user by email
     * @param email email of the user to get the full name
     *              if the user does not exist, return the email
     * @return the full name of the user
     * */
    private String getFullNameUserByEmail(String email) {
        Optional<UserDTO> userByEmail = userService.findUserByEmail(email);
        return userByEmail.map(UserDTO::getFullName).orElse(email);
    }

    private void sendPlainTextMessage(String to, String subject, String content) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(senderEmail);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            // sending mimeMessage
            javaMailSender.send(mimeMessage);
            log.info("Mail sent to {}", to);
        } catch (MessagingException e) {
            log.warn("Email could not be sent to user '{}' : '{}'", to, e.getMessage());
        }
    }

    private Context getContext() {
        return new Context(Locale.CANADA_FRENCH);
    }
}
