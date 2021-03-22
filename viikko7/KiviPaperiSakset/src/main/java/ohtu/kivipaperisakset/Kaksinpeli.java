package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Kaksinpeli extends KiviPaperiSakset {
    
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    protected String toisenSiirto() {
        System.out.println("Toisen pelaajan siirto: ");
        return scanner.nextLine();
    }

    @Override
    protected void asetaSiirto(String siirto) {}
    
}