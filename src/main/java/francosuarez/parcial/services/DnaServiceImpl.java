package francosuarez.parcial.services;

import francosuarez.parcial.dtos.StatsDto;
import francosuarez.parcial.entities.DNA;
import francosuarez.parcial.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnaServiceImpl implements DnaService {

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public boolean isMutant(String[] dnaSec) {
        if (dnaSec == null || dnaSec.length < 4) {
            return false;
        }


        int n = dnaSec.length;
        if (n != dnaSec[0].length()) {
            throw new IllegalArgumentException("La matriz debe ser cuadrada");
        }

        int sequencesFound = 0;

        // Validamos las Filas, columnas y diagonales
        sequencesFound += checkRows(dnaSec);
        sequencesFound += checkColumns(dnaSec);
        sequencesFound += checkDiagonals(dnaSec);
        sequencesFound += checkAntiDiagonals(dnaSec);

        boolean isMutant = sequencesFound > 1;
        saveDna(dnaSec, isMutant);

        return isMutant;
    }

    public void saveDna(String[] dnaSec, boolean isMutant){
        DNA dna = new DNA();
        dna.setDna(dnaSec);
        dna.setMutant(isMutant);
        dnaRepository.save(dna);
    }

    private int checkRows(String[] dna) {
        int sequences = 0;
        for (String row : dna) {
            sequences += checkSequence(row);
        }
        return sequences;
    }

    private int checkColumns(String[] dna) {
        int sequences = 0;
        for (int col = 0; col < dna.length; col++) {
            StringBuilder column = new StringBuilder();
            for (int row = 0; row < dna.length; row++) {
                column.append(dna[row].charAt(col));
            }
            sequences += checkSequence(column.toString());
        }
        return sequences;
    }

    private int checkDiagonals(String[] dna) {
        int sequences = 0;
        for (int i = 0; i <= dna.length - 4; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = i, col = 0; row < dna.length && col < dna.length; row++, col++) {
                diagonal.append(dna[row].charAt(col));
            }
            sequences += checkSequence(diagonal.toString());
        }
        for (int i = 1; i <= dna.length - 4; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = 0, col = i; row < dna.length && col < dna.length; row++, col++) {
                diagonal.append(dna[row].charAt(col));
            }
            sequences += checkSequence(diagonal.toString());
        }
        return sequences;
    }

    private int checkAntiDiagonals(String[] dna) {
        int sequences = 0;
        for (int i = 3; i < dna.length; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = i, col = 0; row >= 0 && col < dna.length; row--, col++) {
                diagonal.append(dna[row].charAt(col));
            }
            sequences += checkSequence(diagonal.toString());
        }
        for (int i = 1; i <= dna.length - 4; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = dna.length - 1, col = i; row >= 0 && col < dna.length; row--, col++) {
                diagonal.append(dna[row].charAt(col));
            }
            sequences += checkSequence(diagonal.toString());
        }
        return sequences;
    }

    private int checkSequence(String sequence) {
        for (int i = 0; i < sequence.length() - 3; i++) {
            if (sequence.charAt(i) == sequence.charAt(i + 1) &&
                    sequence.charAt(i) == sequence.charAt(i + 2) &&
                    sequence.charAt(i) == sequence.charAt(i + 3)) {
                return 1;
            }
        }
        return 0;
    }



    @Override
    public StatsDto getStatsDto() {
        long countMutantDna = dnaRepository.countByIsMutant(true);
        long countHumanDna = dnaRepository.countByIsMutant(false);
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;
        return new StatsDto(countMutantDna, countHumanDna, ratio);
    }
}
