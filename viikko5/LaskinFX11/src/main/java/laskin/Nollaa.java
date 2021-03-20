
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    
    int arvo = 0;
    
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }
    
    @Override
    public void suorita() {
        arvo = sovellus.tulos();
        sovellus.nollaa();
        syotekentta.clear();
        tuloskentta.setText("" + sovellus.tulos());
        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.plus(arvo);
        tuloskentta.setText("" + arvo);
        nollaa.disableProperty().set(false);
        undo.disableProperty().set(true);
    }
    
}
