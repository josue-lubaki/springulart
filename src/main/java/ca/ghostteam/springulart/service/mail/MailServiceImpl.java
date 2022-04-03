package ca.ghostteam.springulart.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;

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

    public MailServiceImpl(
           @Qualifier("myMailSender") JavaMailSender javaMailSender,
                           SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    @Override
    public void resetPassword(String email, String temporaryPassword) {
        Context context = getContext();
        context.setVariable("password", temporaryPassword);
        context.setVariable("fullname", String.format("%s", email));

        // set content of message
        String content = templateEngine.process("emails/reset-password", context);

        // send message
        sendPlainTextMessage(email, "Réinitialisation de mot de passe", content);
    }

    private void sendPlainTextMessage(String to, String subject, String content) {
        try{
            log.info("SenderEmail {} would send message to {}", senderEmail, to);
            // create SimpleMailSender with HTML content
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            javaMailSender.send(message);

//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//            messageHelper.setFrom(senderEmail);
//            messageHelper.setTo(to);
//            messageHelper.setSubject(subject);
//            messageHelper.setText(content, true);
//
//            // sending mimeMessage
//            mailSender.send(mimeMessage);
            log.info("Mail sent to {}", to);
        } catch (Exception e) {
            log.warn("Email could not be sent to user '{}' : '{}'", to, e.getMessage());
        }
    }

    private Context getContext() {
        return new Context(Locale.CANADA_FRENCH);
    }
}
