package ohtu.kivipaperisakset;

public class HelppoYksinpeli extends KiviPaperiSakset {

    private final Tekoaly tekoaly;
    
    public HelppoYksinpeli() {
        this.tekoaly = new Tekoaly();
    }
    
    @Override
    protected String toisenSiirto() {
        String toisenSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + toisenSiirto);
        return toisenSiirto;
    }

    @Override
    protected void asetaSiirto(String siirto) {}
    
}