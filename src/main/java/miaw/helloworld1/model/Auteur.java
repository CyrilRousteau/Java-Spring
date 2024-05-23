package miaw.helloworld1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;

    public Auteur() {
    }

    public Auteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Auteur{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", prenom='" + prenom + '\'' +
               '}';
    }
}
