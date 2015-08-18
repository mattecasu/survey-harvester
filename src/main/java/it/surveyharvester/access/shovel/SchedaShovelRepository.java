package it.surveyharvester.access.shovel;

import org.apache.jena.rdf.model.Model;

import it.surveyharvester.model.SchedaQuadrato;
import it.surveyharvester.model.SchedaShovel;


public class SchedaShovelRepository {

    private final Model rdfModel;

    public SchedaShovelRepository(final Model rdfModel) {
        this.rdfModel = rdfModel;
    }

    public void deleteSchedaQuadrato(SchedaShovel schedaShovel) {
        // UpdateAction.parseExecute(deleteSchedaShovel.replace("<numeroScheda>",
        // schedaShovel.getNumeroScheda().toString()), rdfModel);
    }

    public void insertSchedaQuadrato(SchedaQuadrato schedaQuadrato) {
        rdfModel.add(schedaQuadrato.getTriples());
    }

}
