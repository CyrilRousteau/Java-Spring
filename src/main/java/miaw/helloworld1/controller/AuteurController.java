package miaw.helloworld1.controller;

import miaw.helloworld1.model.Auteur;
import miaw.helloworld1.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auteurs")
public class AuteurController {

    @Autowired
    private AuteurRepository auteurRepository;

    @GetMapping("/ajouter")
    public String afficherFormulaire() {
        return "helloworld1/ajouterAuteur";
    }

    @PostMapping("/ajouter")
    public String ajouterAuteur(@RequestParam String nom, @RequestParam String prenom, ModelMap map) {
        Auteur auteur = new Auteur(nom, prenom);
        auteurRepository.save(auteur);
        map.put("message", "Auteur ajouté avec succès !");
        return "helloworld1/ajouterAuteur";
    }

    @GetMapping("/liste")
    public String liste(ModelMap map) {
        List<Auteur> auteurs = auteurRepository.findAll();
        map.put("auteurs", auteurs);
        return "helloworld1/listeAuteurs";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String afficherAuteur(@PathVariable Long id) {
        Auteur auteur = auteurRepository.findById(id).orElse(null);
        if (auteur == null) {
            return "Auteur non trouvé.";
        }
        return "Auteur id=" + auteur.getId() + ", nom=" + auteur.getNom() + ", prenom=" + auteur.getPrenom();
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerAuteur(@PathVariable Long id, ModelMap map) {
        if (auteurRepository.existsById(id)) {
            auteurRepository.deleteById(id);
        }
        return "redirect:/auteurs/liste";
    }


    @GetMapping("/modifier")
    public String afficherFormulaireModification(@RequestParam Long id, ModelMap map) {
        Auteur auteur = auteurRepository.findById(id).orElse(null);
        if (auteur == null) {
            map.put("message", "Auteur non trouvé.");
            return "helloworld1/modifierAuteur";
        }
        map.put("auteur", auteur);
        return "helloworld1/modifierAuteur";
    }

    @PostMapping("/modifier")
    public String modifierAuteur(@RequestParam Long id, @RequestParam String nom, @RequestParam String prenom, ModelMap map) {
        Auteur auteur = auteurRepository.findById(id).orElse(null);
        if (auteur == null) {
            map.put("message", "Auteur non trouvé.");
            return "helloworld1/modifierAuteur";
        }
        auteur.setNom(nom);
        auteur.setPrenom(prenom);
        auteurRepository.save(auteur);
        map.put("message", "Auteur modifié avec succès.");
        map.put("auteur", auteur);
        return "helloworld1/modifierAuteur";
    }
}
