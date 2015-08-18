package it.surveyharvester.model;

import java.util.List;

import org.apache.jena.rdf.model.Model;

import it.surveyharvester.model.interfaces.SurveyHarvesterObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Accessors(chain = true)
@Getter
@Setter(value = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class US implements SurveyHarvesterObject {

    @Accessors(chain = true)
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class DimensioniComponentiLitiche {
        Float lunghezzaMin, lunghezzaMax, larghezzaMin, larghezzaMax;

        public DimensioniComponentiLitiche(
                Float lunghezzaMin, Float lunghezzaMax, Float larghezzaMin, Float larghezzaMax) {
            this.lunghezzaMin = lunghezzaMin;
            this.lunghezzaMax = lunghezzaMax;
            this.larghezzaMin = larghezzaMin;
            this.larghezzaMax = larghezzaMax;
        }
    }

    Integer numeroUS;
    List<String> quadrati;
    String tipo;
    Float lunghezza;
    Float altezza;
    Float spessore;
    String superficie;
    String materiale;
    String forma;
    String tipologia;
    DimensioniComponentiLitiche dimComponentiLitiche;
    List<String> tipoCorso;
    List<String> altre;
    String gradoDiConservazione;
    String tessituraAcciottolato;
    String superficie2;
    boolean rocce;

    List<String> ugualeA,
            siLegaA,
            siAppoggiaA,
            gliSiAppoggia,
            copre,
            copertoDa,
            taglia,
            tagliatoDa,
            riempie,
            riempitoDa;


    public US(Integer numeroUS) {
        this.numeroUS = numeroUS;
    }

    public US build(
            Integer numeroUS,
            List<String> quadrati,
            String tipo,
            Float lunghezza,
            Float altezza,
            Float spessore,
            String superficie,
            String materiale,
            String forma,
            String tipologia,
            DimensioniComponentiLitiche dimComponentiLitiche,
            List<String> tipoCorso,
            List<String> altre,
            String gradoDiConservazione,
            String tessituraAcciottolato,
            String superficie2,
            boolean rocce,
            List<String> ugualeA,
            List<String> siLegaA,
            List<String> siAppoggiaA,
            List<String> gliSiAppoggia,
            List<String> copre,
            List<String> copertoDa,
            List<String> taglia,
            List<String> tagliatoDa,
            List<String> riempie,
            List<String> riempitoDa) {
        return (US) this
                .setAltezza(altezza)
                .setAltre(altre)
                .setCopertoDa(copertoDa)
                .setCopre(copre)
                .setForma(forma)
                .setGliSiAppoggia(gliSiAppoggia)
                .setGradoDiConservazione(gradoDiConservazione)
                .setDimComponentiLitiche(dimComponentiLitiche)
                .setLunghezza(lunghezza)
                .setMateriale(materiale)
                .setQuadrati(quadrati)
                .setRiempie(riempie)
                .setRiempitoDa(riempitoDa)
                .setRocce(rocce)
                .setSiAppoggiaA(siAppoggiaA)
                .setSiLegaA(siLegaA)
                .setSpessore(spessore)
                .setSuperficie(superficie2)
                .setSuperficie2(superficie2)
                .setTaglia(tagliatoDa)
                .setTagliatoDa(tagliatoDa)
                .setTessituraAcciottolato(tessituraAcciottolato)
                .setTipo(tipo)
                .setTipoCorso(tipoCorso)
                .setTipologia(tipologia)
                .setUgualeA(ugualeA);
    }

    @Override
    public Model getTriples() {
        return null;
    }

}
