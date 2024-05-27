package miaw.helloworld1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private int nombrePages;

    public Livre() {
    }

    public Livre(String titre, int nombrePages) {
        this.titre = titre;
        this.nombrePages = nombrePages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNombrePages() {
        return nombrePages;
    }

    public void setNombrePages(int nombrePages) {
        this.nombrePages = nombrePages;
    }

    @Override
    public String toString() {
        return "Livre{" +
               "id=" + id +
               ", titre='" + titre + '\'' +
               ", nombrePages=" + nombrePages +
               '}';
    }
}
