package it.surveyharvester.access.us;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateAction;

import it.surveyharvester.model.SchedaUS;
import it.surveyharvester.viewmodel.SchedaUSAggregate;


public class SchedaUSRepository {

    private Model rdfModel;

    public SchedaUSRepository(Model rdfModel) {
        this.rdfModel = rdfModel;
    }

    public void deleteSchedaQuadrato(SchedaUSAggregate schedaUS) {
        UpdateAction.parseExecute(SchedaUSQueryContainer.deleteSchedaUS.replace("<numeroScheda>", schedaUS.getNumeroScheda().toString()), rdfModel);
    }

    public void insertSchedaUS(SchedaUS schedaUS) {
        rdfModel.add(schedaUS.getTriples());
    }

}
