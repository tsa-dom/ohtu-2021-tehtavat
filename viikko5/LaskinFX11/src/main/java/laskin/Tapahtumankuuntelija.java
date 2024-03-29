package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    private final Button undo;
    private final Sovelluslogiikka sovellus;
    
    private final Map<Button, Komento> komennot;
    private Komento edellinenKomento = null;
 

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus));
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, undo, sovellus));
    }
    
    @Override
    public void handle(Event event) {
        if (event.getTarget() != undo) {
            Komento komento = this.komennot.get((Button)event.getTarget());
            komento.suorita();
            this.edellinenKomento = komento;
        } else {
            this.edellinenKomento.peru();
            this.edellinenKomento = null;
        }                  
    }

}