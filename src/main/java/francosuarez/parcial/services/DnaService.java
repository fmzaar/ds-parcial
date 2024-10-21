package francosuarez.parcial.services;

import francosuarez.parcial.dtos.StatsDto;

public interface DnaService {
    boolean isMutant(String[] dna);
    StatsDto getStatsDto();
}
