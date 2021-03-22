package ohtu.kivipaperisakset;

public class PeliTehdas {
    
    public static KiviPaperiSakset helppoYksinpeli() {
        return new HelppoYksinpeli();
    }
    
    public static KiviPaperiSakset vaikeaYksinpeli() {
        return new VaikeaYksinpeli();
    }
    
    public static KiviPaperiSakset kaksinpeli() {
        return new Kaksinpeli();
    }
    
}
