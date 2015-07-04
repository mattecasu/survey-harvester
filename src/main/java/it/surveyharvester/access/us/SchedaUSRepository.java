package it.surveyharvester.access.us;

import static com.hp.hpl.jena.update.UpdateAction.parseExecute;
import it.surveyharvester.model.SchedaUS;
import it.surveyharvester.viewmodel.SchedaUSAggregate;

import com.hp.hpl.jena.rdf.model.Model;

public class SchedaUSRepository {

    private Model rdfModel;

    public SchedaUSRepository(Model rdfModel) {
        this.rdfModel = rdfModel;
    }

    public void deleteSchedaQuadrato(SchedaUSAggregate schedaUS) {
        parseExecute(SchedaUSQueryContainer.deleteSchedaUS.replace("<numeroScheda>", schedaUS.getNumeroScheda().toString()), rdfModel);
    }

    public void insertSchedaUS(SchedaUS schedaUS) {
        rdfModel.add(schedaUS.getTriples());
    }

}
