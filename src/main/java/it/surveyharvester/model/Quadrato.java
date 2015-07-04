package it.surveyharvester.model;

import static com.hp.hpl.jena.rdf.model.ModelFactory.createDefaultModel;
import static it.surveyharvester.access.SurveyHarvesterQueryContainer.harvesterRastello;
import static it.surveyharvester.access.SurveyHarvesterQueryContainer.surveyNs;
import static lombok.AccessLevel.PRIVATE;
import it.surveyharvester.model.interfaces.SurveyHarvesterObject;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

@Accessors(chain = true)
@Getter
@Setter(value = PRIVATE)
@FieldDefaults(level = PRIVATE)
public class Quadrato implements SurveyHarvesterObject {

    public Quadrato(String numeroQuadrato) {
        this.numeroQuadrato = numeroQuadrato;
    }

    String numeroQuadrato; // numero romano
    Integer numeroScheda;
    Integer area;
    String fisiografia,
            vegetazione,
            visibilita,
            umidita;
    List<String> tessituraTerreno;
    List<PuntoBattuto> haPuntoBattuto;

    public Quadrato build(
            Integer area, Integer numeroScheda, String fisiografia,
            String vegetazione, String visibilita, List<String> tessituraTerreno,
            String umidita, List<PuntoBattuto> puntiBattuti
            ) {
        return this
                .setArea(area)
                .setNumeroScheda(numeroScheda)
                .setFisiografia(fisiografia)
                .setVegetazione(vegetazione)
                .setVisibilita(visibilita)
                .setTessituraTerreno(tessituraTerreno)
                .setUmidita(umidita)
                .setHaPuntoBattuto(puntiBattuti);
    }

    @Override
    public Model getTriples() {

        Model m = createDefaultModel();
        Resource res = m.createResource(
                harvesterRastello + "quadrati/" + numeroQuadrato,
                m.createResource(surveyNs + "Quadrato")
                );
        res
                .addLiteral(m.createProperty(surveyNs + "numeroScheda"), numeroScheda)
                .addLiteral(m.createProperty(surveyNs + "numeroQuadrato"), numeroQuadrato)
                .addLiteral(m.createProperty(surveyNs + "area"), area)
                .addLiteral(m.createProperty(surveyNs + "fisiografia"), fisiografia)
                .addLiteral(m.createProperty(surveyNs + "vegetazione"), vegetazione)
                .addLiteral(m.createProperty(surveyNs + "visibilita"), visibilita)
                .addLiteral(m.createProperty(surveyNs + "umidita"), umidita);

        tessituraTerreno.stream()
                .forEach(t -> m.add(res, m.createProperty(surveyNs + "tessituraTerreno"), t));

        haPuntoBattuto.stream()
                .forEach(p -> {
                    m.add(
                            res,
                            m.createProperty(surveyNs + "haPuntoBattuto"),
                            m.createResource(harvesterRastello + "punti/" + p.getIdentificatorePicchetto())
                            );
                    m.add(p.getTriples());
                });

        return m;
    }
}
