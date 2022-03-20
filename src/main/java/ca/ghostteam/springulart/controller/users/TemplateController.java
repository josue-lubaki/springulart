package ca.ghostteam.springulart.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-20
 */
@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLogin(){
        return "login";
    }
}
