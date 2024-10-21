package francosuarez.parcial.controllers;

import francosuarez.parcial.dtos.DnaDto;
import francosuarez.parcial.dtos.StatsDto;
import francosuarez.parcial.entities.DNA;
import francosuarez.parcial.services.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DnaController {

    @Autowired
    private DnaService dnaService;
    public DnaController(DnaService dnaService) {
        this.dnaService = dnaService;
    }

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DNA dna) {
        boolean isMutant = dnaService.isMutant(dna.getDna()); // Use dnaService instance
        if (isMutant) {
            return new ResponseEntity<>("El ADN Ingresado es mutante.",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("El ADN ingresado es humano.",HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/stats")
    public StatsDto statsDto() {
        return dnaService.getStatsDto();
    }

}
