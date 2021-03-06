
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private final int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLukumaara;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
        kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        lukujono = new int[kapasiteetti];
        kasvatuskoko = OLETUSKASVATUS;
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        lukujono = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        lukujono[alkioidenLukumaara] = luku;
        alkioidenLukumaara++;
        if (alkioidenLukumaara >= lukujono.length) {
            kasvataTaulukkoa();
        }
        return true;
    }
    
    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }
    
    private void kasvataTaulukkoa() {
        int[] vanha = lukujono;
        lukujono = new int[alkioidenLukumaara + kasvatuskoko];
        System.arraycopy(vanha, 0, lukujono, 0, vanha.length);
    }
    
    public boolean poista(int luku) {
        boolean poistettu = false;
        for (int i = 0; i < alkioidenLukumaara; i++) { 
            if (luku == lukujono[i]) {
                poistettu = true;
                alkioidenLukumaara--;
            }
            if (poistettu) {
                lukujono[i] = lukujono[i + 1];
            }
        }
        return poistettu;
    }

    public int mahtavuus() {
        return alkioidenLukumaara;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        if (alkioidenLukumaara > 0) {
            tuotos += lukujono[0];
            for (int i = 1; i < alkioidenLukumaara; i++) {
                tuotos += ", " + lukujono[i];
            }
        }
        tuotos += "}";
        return tuotos;
    }
    
    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLukumaara];
        System.arraycopy(lukujono, 0, taulu, 0, taulu.length);
        return taulu;
    }
   
    public static IntJoukko yhdiste(IntJoukko eka, IntJoukko toka) {
        int[] tokaTaulu = toka.toIntArray();
        for (int i = 0; i < tokaTaulu.length; i++) {
            eka.lisaa(tokaTaulu[i]);
        }
        return eka;
    }
    
    public static IntJoukko leikkaus(IntJoukko eka, IntJoukko toka) {
        IntJoukko leikkaus = new IntJoukko();
        int[] tokaTaulu = toka.toIntArray();
        for (int luku : tokaTaulu) {
            if (eka.kuuluu(luku)) {
                leikkaus.lisaa(luku);
            }
        }
        return leikkaus;
    }
    
    public static IntJoukko erotus (IntJoukko eka, IntJoukko toka) {
        int[] tokaTaulu = toka.toIntArray();
        for (int i = 0; i < tokaTaulu.length; i++) {
            eka.poista(tokaTaulu[i]);
        }
        return eka;
    }
    
}
