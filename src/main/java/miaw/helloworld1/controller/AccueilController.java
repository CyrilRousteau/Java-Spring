package miaw.helloworld1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @GetMapping("/accueil")
    public String afficherAccueil() {
        return "helloworld1/accueil";
    }
}
