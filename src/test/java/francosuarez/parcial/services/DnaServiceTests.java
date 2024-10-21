package francosuarez.parcial.services;

import francosuarez.parcial.entities.DNA;
import francosuarez.parcial.repositories.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class DnaServiceTests {

    @Mock
    private DnaRepository dnaRepository;
    @InjectMocks
    private DnaServiceImpl dnaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsMutant_Human() {
        String[] adn = {
                "ATGCGA",
                "TAGTAG",
                "GCTAGT",
                "AGACGG",
                "TCGTTA",
                "TCATTG"
        };
        boolean result = dnaService.isMutant(adn);
        assertEquals(false, result, "El resultado deberia ser false");
    }


    @Test
    public void testIsMutant_Mutant() {
        String[] adn = {
                "ATGCGA",
                "TAGTAG",
                "GCTAGT",
                "AGACGG",
                "TCGTTA",
                "TCAGTG"
        };
        boolean result = dnaService.isMutant(adn);
        assertTrue(result, "El resultado deberia ser true");
    }

    @Test
    public void testIsMutant_Horizontal() {
        String[] adn = {
                "ATGGGG",
                "TAGTGG",
                "GCTAGT",
                "AGACGG",
                "TTTTTA",
                "TCATTG"
        };
        boolean result = dnaService.isMutant(adn);
        assertTrue(result, "El resultado deberia ser true");
        }

    @Test
    public void testIsMutant_Vertical() {
        String[] adn = {
                "ATGCGA",
                "AAGTGA",
                "ATTTGA",
                "AGAATA",
                "ACCTCA",
                "ATCACT"
        };
        boolean result = dnaService.isMutant(adn);
        assertTrue(result, "El resultado deberia ser true");
        }
    @Test
    public void testIsMutant_Diagonal() {
        String[] adn = {
                "ATGCGA",
                "AATTGT",
                "GTATGA",
                "AGAATA",
                "ACCTCA",
                "ATCACT"
        };
        boolean result = dnaService.isMutant(adn);
        assertTrue(result, "El resultado deberia ser true");
        }
    @Test
    public void testIsMutant_AntiDiagonal() {
        String[] adn = {
                "ATGCGA",
                "AATTGT",
                "GCTATA",
                "AGATCG",
                "AATCCA",
                "ATCATC"
        };
        boolean result = dnaService.isMutant(adn);
        assertTrue(result, "El resultado deberia ser true");
        }

    @Test
    public void testSaveDna() {
        String[] adn = {
                "ATGCGA",
                "AATTGT",
                "GCTATA",
                "AGATCG",
                "AATCCA",
                "ATCATC"
        };
        boolean result = dnaService.isMutant(adn);
        dnaService.saveDna(adn, result);
        verify(dnaRepository).save(new DNA(adn, result));
        }
    }

