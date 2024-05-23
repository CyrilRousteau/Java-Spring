package miaw.helloworld1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String genre;
    private LocalDate datePublication;

    public Livre() {
    }

    public Livre(String titre, String genre, LocalDate datePublication) {
        this.titre = titre;
        this.genre = genre;
        this.datePublication = datePublication;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    @Override
    public String toString() {
        return "Livre{" +
               "id=" + id +
               ", titre='" + titre + '\'' +
               ", genre='" + genre + '\'' +
               ", datePublication=" + datePublication +
               '}';
    }
}
