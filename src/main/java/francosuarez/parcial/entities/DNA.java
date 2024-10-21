package francosuarez.parcial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "secuencia_dna")
public class DNA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT", unique = true)
    private String[] dna;
    private boolean isMutant;

    public DNA(String[] dna, boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }
}
