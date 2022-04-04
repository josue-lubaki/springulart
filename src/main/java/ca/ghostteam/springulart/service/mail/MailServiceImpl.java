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

    @Value("${spring.mail.username}")
    private String senderEmail;

    public MailServiceImpl(JavaMailSender javaMailSender,
                           SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    @Override
    public void resetPassword(UserDTO user, String temporaryPassword) {
        // get the full name of the user
        String fullname = user.getFullName();

        // create the context for the template
        Context context = getContext();
        context.setVariable("password", temporaryPassword);
        context.setVariable("fullname", String.format("%s", fullname));

        // set content of message
        String content = templateEngine.process("emails/reset-password", context);

        // send message
        sendPlainTextMessage(user.getEmail(), "Réinitialisation de mot de passe", content);
    }

    @Override
    public void welcomeMessage(String email, String fullname) {
        // create the context for the template
        Context context = getContext();
        context.setVariable("fullname", String.format("%s", fullname));

        // set content of message
        String content = templateEngine.process("emails/welcome", context);

        // send message
        sendPlainTextMessage(email, "Bienvenue sur angulart", content);
    }

    /**
     * Method to send a plain text message
     * @param to the email address of the recipient
     *           of the message
     *           (e.g. "josuelubaki@gmail.com")
     * @param subject the subject of the message
     *                (e.g. "Réinitialisation de mot de passe")
     * @param content the content of the message (e.g. "Your temporary password is: 12345")
     * @catch MessagingException if the message cannot be sent
     * */
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
