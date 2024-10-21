package francosuarez.parcial.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsDto {
    private long count_mutant_dna;
    private long count_human_dna;
    private double ratio;

    public StatsDto(long count_mutant_dna, long count_human_dna, double ratio) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio;
    }
}
