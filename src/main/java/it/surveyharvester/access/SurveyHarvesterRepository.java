package it.surveyharvester.access;

import static it.surveyharvester.access.SurveyHarvesterQueryContainer.getSchedeTemplate;
import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import it.surveyharvester.access.quadrato.SchedaQuadratoRepository;
import it.surveyharvester.access.shovel.SchedaShovelRepository;
import it.surveyharvester.access.us.SchedaUSRepository;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

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
        ResultSet rs = QueryExecutionFactory.create(queryString, rdfModel).execSelect();
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
