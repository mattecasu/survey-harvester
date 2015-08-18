package it.surveyharvester.model;

import static it.surveyharvester.access.SurveyHarvesterQueryContainer.harvesterRastello;
import static it.surveyharvester.access.SurveyHarvesterQueryContainer.surveyNs;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalTime;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import it.surveyharvester.model.interfaces.SurveyHarvesterObject;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Accessors(chain = true)
@Getter
@Setter(value = PRIVATE)
@FieldDefaults(level = PRIVATE)
public class SchedaQuadrato extends Scheda implements SurveyHarvesterObject {

    public SchedaQuadrato(Integer numeroScheda, String ut, Quadrato haQuadrato) {
        this.numeroScheda = numeroScheda;
        this.ut = ut;
        this.haQuadrato = haQuadrato;
    }

    String meteo;
    Quadrato haQuadrato;
    LocalTime oraInizio,
            oraFine;

    public SchedaQuadrato build(
            LocalTime oraInizio,
            LocalTime oraFine,
            String meteo,
            String data,
            Integer area,
            String descrizione,
            String osservazioni,
            String autore1,
            String autore2) {
        return (SchedaQuadrato) this
                .setOraInizio(oraInizio)
                .setOraFine(oraFine)
                .setMeteo(meteo)
                .setData(data)
                .setArea(area)
                .setDescrizione(descrizione)
                .setOsservazioni(osservazioni)
                .setAutore1(autore1)
                .setAutore2(autore2);
    }

    @Override
    public Model getTriples() {
        Model m = ModelFactory.createDefaultModel();
        Resource res = m.createResource(harvesterRastello + "schedeQuadrato/" + numeroScheda, m.createResource(surveyNs + "SchedaQuadrato"));
        res
                .addProperty(m.createProperty(surveyNs + "numeroScheda"), numeroScheda.toString(), XSDDatatype.XSDinteger)
                .addLiteral(m.createProperty(surveyNs + "ut"), ut)
                .addLiteral(m.createProperty(surveyNs + "data"), data)
                .addLiteral(m.createProperty(surveyNs + "meteo"), meteo)
                .addProperty(m.createProperty(surveyNs + "haQuadrato"),
                        m.createResource(harvesterRastello + "quadrati/" + haQuadrato.getNumeroQuadrato()));

        m.add(haQuadrato.getTriples());

        res
                .addLiteral(m.createProperty(surveyNs + "area"), area)
                .addLiteral(m.createProperty(surveyNs + "oraInizio"), oraInizio)
                .addLiteral(m.createProperty(surveyNs + "oraFine"), oraFine)
                .addLiteral(m.createProperty(surveyNs + "descrizione"), descrizione)
                .addLiteral(m.createProperty(surveyNs + "osservazioni"), osservazioni)
                .addLiteral(m.createProperty(surveyNs + "autore1"), autore1)
                .addLiteral(m.createProperty(surveyNs + "autore2"), autore2);

        return m;
    }

}
