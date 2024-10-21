package francosuarez.parcial.repositories;

import francosuarez.parcial.entities.DNA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<DNA, Long> {

    long countByIsMutant(boolean isMutant);
}
