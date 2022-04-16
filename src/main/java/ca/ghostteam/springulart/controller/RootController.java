package ca.ghostteam.springulart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-16
 */
@Controller
public class RootController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
