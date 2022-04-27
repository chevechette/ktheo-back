package fr.ktheo.back.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("WebAuthController")
@RequestMapping()
public class AuthController {

    @GetMapping("/signin")
    public String signin() {
        return "login";
    }
}
