package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Varasto varasto;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        
        varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 4));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "pulla", 7));
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei v‰litetty kutsussa k‰ytetyist‰ parametreista
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoOnKutsuttuOikeillaParametreillaKunEriOstoksiaOnUseita() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi())
                .thenReturn(42)
                .thenReturn(313);

        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("anni", "54321");
        
        verify(pankki).tilisiirto(eq("anni"), eq(42), eq("54321"), eq("33333-44455"), eq(5));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("anni", "54321");
        
        verify(pankki).tilisiirto(eq("anni"), eq(313), eq("54321"), eq("33333-44455"), eq(4));
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoOnKutsuttuOikeinKunSamanTuotteenOstoksiaOnUseita() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi())
                .thenReturn(42)
                .thenReturn(313);

        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("anni", "54321");
        
        verify(pankki).tilisiirto(eq("anni"), eq(42), eq("54321"), eq("33333-44455"), eq(5));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("anni", "54321");
        
        verify(pankki).tilisiirto(eq("anni"), eq(42), eq("54321"), eq("33333-44455"), eq(5));
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoOnKutsuttuOikeinKunToinenOstetuistaTuotteistaOnLoppu() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi())
                .thenReturn(42)
                .thenReturn(313);

        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("anni", "54321");
        verify(pankki).tilisiirto(eq("anni"), eq(42), eq("54321"), eq("33333-44455"), eq(4));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("anni", "54321");
        verify(pankki).tilisiirto(eq("anni"), eq(313), eq("54321"), eq("33333-44455"), eq(0));
    }
    
    @Test
    public void ostoskoristaPoistettuTuoteLisataanTakaisinVarastoon() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(2);
        verify(varasto, times(2)).otaVarastosta(varasto.haeTuote(2));
        
        kauppa.poistaKorista(2);
        verify(varasto, times(3)).palautaVarastoon(varasto.haeTuote(2));
    }
    
}