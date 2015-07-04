package it.surveyharvester.model;

import static com.hp.hpl.jena.datatypes.xsd.XSDDatatype.XSDinteger;
import static com.hp.hpl.jena.rdf.model.ModelFactory.createDefaultModel;
import static it.surveyharvester.access.SurveyHarvesterQueryContainer.harvesterRastello;
import static it.surveyharvester.access.SurveyHarvesterQueryContainer.surveyNs;
import it.surveyharvester.model.interfaces.SurveyHarvesterObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

@Accessors(chain = true)
@Getter
@Setter(value = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchedaUS extends Scheda implements SurveyHarvesterObject {

    US us;
    String definizione;
    String gradoDiAffidabilita;

    public SchedaUS(Integer numeroScheda, String ut, US us) {
        this.numeroScheda = numeroScheda;
        this.ut = ut;
        this.us = us;
    }

    public SchedaUS build(
            String definizione,
            String gradoDiAffidabilita,
            String data,
            Integer area,
            String descrizione,
            String osservazioni,
            String autore1,
            String autore2
            ) {
        return (SchedaUS) this
                .setDefinizione(definizione)
                .setGradoDiAffidabilita(gradoDiAffidabilita)
                .setData(data)
                .setArea(area)
                .setDescrizione(descrizione)
                .setOsservazioni(osservazioni)
                .setAutore1(autore1)
                .setAutore2(autore2);
    }

    public Model getTriples() {
        Model m = createDefaultModel();
        Resource res = m.createResource(harvesterRastello + "schedeUS/" + numeroScheda,
                m.createResource(surveyNs + "SchedaUS"));
        res
                .addProperty(m.createProperty(surveyNs + "numeroScheda"), numeroScheda.toString(),
                        XSDinteger)
                .addLiteral(m.createProperty(surveyNs + "ut"), ut)
                .addLiteral(m.createProperty(surveyNs + "data"), data)
                .addLiteral(m.createProperty(surveyNs + "area"), area)
                .addLiteral(m.createProperty(surveyNs + "descrizione"), descrizione)
                .addLiteral(m.createProperty(surveyNs + "osservazioni"), osservazioni)
                .addLiteral(m.createProperty(surveyNs + "autore1"), autore1)
                .addLiteral(m.createProperty(surveyNs + "autore2"), autore2);

        us.getQuadrati().stream()
                .forEach(q -> res.addProperty(
                        m.createProperty(surveyNs + "inQuadrato"),
                        m.createResource(harvesterRastello + "quadrati/" + q))
                );

        return m;
    }


}
