package it.surveyharvester.access;

import static com.hp.hpl.jena.query.QueryExecutionFactory.create;
import static it.surveyharvester.access.SurveyHarvesterQueryContainer.getSchedeTemplate;
import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;
import it.surveyharvester.access.quadrato.SchedaQuadratoRepository;
import it.surveyharvester.access.shovel.SchedaShovelRepository;
import it.surveyharvester.access.us.SchedaUSRepository;

import java.util.List;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SurveyHarvesterRepository {

    @Getter
    Model rdfModel;
    @Getter
    SchedaQuadratoRepository schedaQuadratoRepo;
    @Getter
    SchedaUSRepository schedaUSRepo;
    @Getter
    SchedaShovelRepository schedaShovelRepo;

    public SurveyHarvesterRepository(String ontologyFile, String rdfFormat) {
        rdfModel = ModelFactory.createDefaultModel();
        rdfModel.read(ontologyFile, rdfFormat);
        schedaQuadratoRepo = new SchedaQuadratoRepository(rdfModel);
        schedaUSRepo = new SchedaUSRepository(rdfModel);
        schedaShovelRepo = new SchedaShovelRepository(rdfModel);
    }

    private List<Integer> getSchedaNumber(String queryString) {
        List<Integer> numbersList = asList();
        ResultSet rs = create(queryString, rdfModel).execSelect();
        rs.forEachRemaining(solution -> numbersList.add(solution.getLiteral("schedaNumber").getInt()));
        return numbersList;
    }


    public List<Integer> getSchedeQuadrato() {
        return getSchedaNumber(getSchedeTemplate.replace("<type>", "survey:SchedaQuadrato"));
    }

    public List<Integer> getSchedeUS() {
        return getSchedaNumber(getSchedeTemplate.replace("<type>", "survey:SchedaUS"));
    }

    public List<Integer> getSchedeShovel() {
        return getSchedaNumber(getSchedeTemplate.replace("<type>", "survey:SchedaShovel"));
    }

}
