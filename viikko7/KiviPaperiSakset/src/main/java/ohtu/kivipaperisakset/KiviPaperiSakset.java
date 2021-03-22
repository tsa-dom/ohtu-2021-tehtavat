
package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KiviPaperiSakset {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        String ensimmaisenSiirto = ensimmaisenSiirto();
        String toisenSiirto = toisenSiirto();

        while (onkoOkSiirto(ensimmaisenSiirto) && onkoOkSiirto(toisenSiirto)) {
            tuomari.kirjaaSiirto(ensimmaisenSiirto, toisenSiirto);
            System.out.println(tuomari + "\n");
            
            ensimmaisenSiirto = ensimmaisenSiirto();
            toisenSiirto = toisenSiirto();
            asetaSiirto(ensimmaisenSiirto);
        }
        
        System.out.println("\n" + "Kiitos!");
        System.out.println(tuomari);
    }
    private String ensimmaisenSiirto() {
        System.out.println("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }
    
    protected abstract String toisenSiirto();
    
    protected abstract void asetaSiirto(String siirto);
    
    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
}
