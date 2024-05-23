package miaw.helloworld1.controller;

import miaw.helloworld1.model.Livre;
import miaw.helloworld1.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping("/ajouterLivre")
    public String afficherFormulaireLivre() {
        return "helloworld1/livre";
    }

    @PostMapping("/ajouterLivre")
    public String ajouterLivre(@RequestParam String titre, @RequestParam String genre, @RequestParam String datePublication, ModelMap map) {
        LocalDate date = LocalDate.parse(datePublication);
        Livre livre = new Livre(titre, genre, date);
        livreRepository.save(livre);
        map.put("message", "Livre ajouté avec succès !");
        return "helloworld1/livre";
    }

    @GetMapping("/listeLivres")
    @ResponseBody
    public String listeLivres() {
        List<Livre> livres = livreRepository.findAll();
        StringBuilder result = new StringBuilder();
        for (Livre livre : livres) {
            result.append("Livre id=").append(livre.getId())
                  .append(", titre=").append(livre.getTitre())
                  .append(", genre=").append(livre.getGenre())
                  .append(", datePublication=").append(livre.getDatePublication())
                  .append("<br>");
        }
        return result.toString();
    }

    @GetMapping("/livre/{id}")
    @ResponseBody
    public String afficherLivre(@PathVariable Long id) {
        Livre livre = livreRepository.findById(id).orElse(null);
        if (livre == null) {
            return "Livre non trouvé.";
        }
        return "Livre id=" + livre.getId() + ", titre=" + livre.getTitre() + ", genre=" + livre.getGenre() + ", datePublication=" + livre.getDatePublication();
    }

    @GetMapping("/supprimerLivre/{id}")
    @ResponseBody
    public String supprimerLivre(@PathVariable Long id) {
        if (livreRepository.existsById(id)) {
            livreRepository.deleteById(id);
            return "Livre supprimé avec succès.";
        } else {
            return "Livre non trouvé.";
        }
    }

    @GetMapping("/modifierLivre")
    @ResponseBody
    public String modifierLivre(@RequestParam Long id, @RequestParam String titre, @RequestParam String genre, @RequestParam String datePublication) {
        Livre livre = livreRepository.findById(id).orElse(null);
        if (livre == null) {
            return "Livre non trouvé.";
        }
        LocalDate date = LocalDate.parse(datePublication);
        livre.setTitre(titre);
        livre.setGenre(genre);
        livre.setDatePublication(date);
        livreRepository.save(livre);
        return "Livre modifié avec succès : id=" + livre.getId() + ", titre=" + livre.getTitre() + ", genre=" + livre.getGenre() + ", datePublication=" + livre.getDatePublication();
    }
}
