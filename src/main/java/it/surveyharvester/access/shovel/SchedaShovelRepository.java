package it.surveyharvester.access.shovel;

import static com.hp.hpl.jena.update.UpdateAction.parseExecute;
import it.surveyharvester.model.SchedaQuadrato;
import it.surveyharvester.model.SchedaShovel;

import com.hp.hpl.jena.rdf.model.Model;

public class SchedaShovelRepository {

    private final Model rdfModel;

    public SchedaShovelRepository(final Model rdfModel) {
        this.rdfModel = rdfModel;
    }
    
    public void deleteSchedaQuadrato(SchedaShovel schedaShovel) {
//        parseExecute(deleteSchedaShovel.replace("<numeroScheda>", schedaShovel.getNumeroScheda().toString()), rdfModel);
    }

    public void insertSchedaQuadrato(SchedaQuadrato schedaQuadrato) {
        rdfModel.add(schedaQuadrato.getTriples());
    }

}
