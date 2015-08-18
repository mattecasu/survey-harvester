package it.surveyharvester.model;

import static it.surveyharvester.access.SurveyHarvesterQueryContainer.harvesterRastello;
import static it.surveyharvester.access.SurveyHarvesterQueryContainer.surveyNs;
import static lombok.AccessLevel.PRIVATE;

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
public class PuntoBattuto implements SurveyHarvesterObject {

    public PuntoBattuto(String identificatorePicchetto) {
        this.identificatorePicchetto = identificatorePicchetto;
    }

    String identificatorePicchetto;
    Float quota,
            longitudine,
            latitudine;
    Integer order;

    public PuntoBattuto build(Integer order, Float quota, Float latitudine, Float longitudine) {
        return this
                .setOrder(order)
                .setQuota(quota)
                .setLatitudine(latitudine)
                .setLongitudine(longitudine);
    }

    @Override
    public Model getTriples() {
        Model m = ModelFactory.createDefaultModel();
        Resource res = m.createResource(harvesterRastello + "punti/" + identificatorePicchetto, m.createResource(surveyNs + "PuntoBattuto"));
        res
                .addProperty(m.createProperty(surveyNs + "order"), order.toString(), XSDDatatype.XSDinteger)
                .addLiteral(m.createProperty(surveyNs + "identificatorePicchetto"), identificatorePicchetto)
                .addLiteral(m.createProperty(surveyNs + "quota"), quota)
                .addLiteral(m.createProperty(surveyNs + "latitudine"), latitudine)
                .addLiteral(m.createProperty(surveyNs + "longitudine"), longitudine);
        return m;
    }

}
