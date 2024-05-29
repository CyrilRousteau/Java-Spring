package miaw.helloworld1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import miaw.helloworld1.model.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
}
