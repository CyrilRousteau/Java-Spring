package miaw.helloworld1.controller;

import miaw.helloworld1.model.Livre;
import miaw.helloworld1.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping("/ajouter")
    public String afficherFormulaireLivre() {
        return "helloworld1/ajouterLivre";
    }

    @PostMapping("/ajouter")
    public String ajouterLivre(@RequestParam String titre, @RequestParam int nombrePages, ModelMap map) {
        Livre livre = new Livre(titre, nombrePages);
        livreRepository.save(livre);
        map.put("message", "Livre ajouté avec succès !");
        return "helloworld1/ajouterLivre";
    }

    @GetMapping("/liste")
    public String liste(ModelMap map) {
        List<Livre> livres = livreRepository.findAll();
        map.put("livres", livres);
        return "helloworld1/listeLivres";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String afficherLivre(@PathVariable Long id) {
        Livre livre = livreRepository.findById(id).orElse(null);
        if (livre == null) {
            return "Livre non trouvé.";
        }
        return "Livre id=" + livre.getId() + ", titre=" + livre.getTitre() + ", nombrePages=" + livre.getNombrePages();
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerLivre(@PathVariable Long id, ModelMap map) {
        if (livreRepository.existsById(id)) {
            livreRepository.deleteById(id);
        }
        return "redirect:/livres/liste";
    }

    @GetMapping("/modifier")
    public String afficherFormulaireModification(@RequestParam Long id, ModelMap map) {
        Livre livre = livreRepository.findById(id).orElse(null);
        if (livre == null) {
            map.put("message", "Livre non trouvé.");
            return "helloworld1/modifierLivre";
        }
        map.put("livre", livre);
        return "helloworld1/modifierLivre";
    }

    @PostMapping("/modifier")
    public String modifierLivre(@RequestParam Long id, @RequestParam String titre, @RequestParam int nombrePages, ModelMap map) {
        Livre livre = livreRepository.findById(id).orElse(null);
        if (livre == null) {
            map.put("message", "Livre non trouvé.");
            return "helloworld1/modifierLivre";
        }
        livre.setTitre(titre);
        livre.setNombrePages(nombrePages);
        livreRepository.save(livre);
        map.put("message", "Livre modifié avec succès.");
        map.put("livre", livre);
        return "helloworld1/modifierLivre";
    }
}
