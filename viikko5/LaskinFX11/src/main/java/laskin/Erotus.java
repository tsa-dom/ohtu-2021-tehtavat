
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {

    int arvo = 0;
    
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }
    
    @Override
    public void suorita() {
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        sovellus.miinus(arvo);
        int laskunTulos = sovellus.tulos();
        syotekentta.clear();
        tuloskentta.setText("" + laskunTulos);
        nollaa.disableProperty().set(false);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.plus(arvo);
        tuloskentta.setText("" + sovellus.tulos());
        undo.disableProperty().set(true);
    }
    
}
