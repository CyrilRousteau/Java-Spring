package miaw.helloworld1.controller;

import miaw.helloworld1.model.Auteur;
import miaw.helloworld1.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuteurController {

    @Autowired
    private AuteurRepository auteurRepository;

    @GetMapping("/ajouterAuteur")
    public String afficherFormulaire() {
        return "helloworld1/auteur";
    }

    @PostMapping("/ajouterAuteur")
    public String ajouterAuteur(@RequestParam String nom, @RequestParam String prenom, ModelMap map) {
        Auteur auteur = new Auteur(nom, prenom);
        auteurRepository.save(auteur);
        map.put("message", "Auteur ajouté avec succès !");
        return "helloworld1/auteur";
    }

    @GetMapping("/listAuteurs")
    @ResponseBody
    public String liste() {
        List<Auteur> auteurs = auteurRepository.findAll();
        StringBuilder result = new StringBuilder();
        for (Auteur auteur : auteurs) {
            result.append("Auteur id=").append(auteur.getId())
                  .append(", nom=").append(auteur.getNom())
                  .append(", prenom=").append(auteur.getPrenom())
                  .append("<br>");
        }
        return result.toString();
    }

    @GetMapping("/auteur/{id}")
    @ResponseBody
    public String afficherAuteur(@PathVariable Long id) {
        Auteur auteur = auteurRepository.findById(id).orElse(null);
        if (auteur == null) {
            return "Auteur non trouvé.";
        }
        return "Auteur id=" + auteur.getId() + ", nom=" + auteur.getNom() + ", prenom=" + auteur.getPrenom();
    }

    @GetMapping("/supprimerAuteur/{id}")
    @ResponseBody
    public String supprimerAuteur(@PathVariable Long id) {
        if (auteurRepository.existsById(id)) {
            auteurRepository.deleteById(id);
            return "Auteur supprimé avec succès.";
        } else {
            return "Auteur non trouvé.";
        }
    }

    @GetMapping("/modifierAuteur")
    @ResponseBody
    public String modifierAuteur(@RequestParam Long id, @RequestParam String nom, @RequestParam String prenom) {
        Auteur auteur = auteurRepository.findById(id).orElse(null);
        if (auteur == null) {
            return "Auteur non trouvé.";
        }
        auteur.setNom(nom);
        auteur.setPrenom(prenom);
        auteurRepository.save(auteur);
        return "Auteur modifié avec succès : id=" + auteur.getId() + ", nom=" + auteur.getNom() + ", prenom=" + auteur.getPrenom();
    }
}
