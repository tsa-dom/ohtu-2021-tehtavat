package ohtu.kivipaperisakset;

public class VaikeaYksinpeli extends KiviPaperiSakset {

    private final TekoalyParannettu tekoaly;
    
    public VaikeaYksinpeli() {
        this.tekoaly = new TekoalyParannettu(20);
    }
    
    @Override
    protected String toisenSiirto() {
        String toisenSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + toisenSiirto);
        return toisenSiirto;
    }

    @Override
    protected void asetaSiirto(String siirto) {
        tekoaly.asetaSiirto(siirto);
    }
    
}