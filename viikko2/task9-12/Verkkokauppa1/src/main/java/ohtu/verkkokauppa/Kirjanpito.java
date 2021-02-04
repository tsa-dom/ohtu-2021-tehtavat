
package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Kirjanpito implements KirjanpitoIF {

    public Kirjanpito() {
        tapahtumat = new ArrayList<String>();
    }
    
    private ArrayList<String> tapahtumat;

    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }       
}
