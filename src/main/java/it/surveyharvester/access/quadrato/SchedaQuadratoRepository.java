package it.surveyharvester.access.quadrato;

import static it.surveyharvester.access.SurveyHarvesterQueryContainer.surveyNs;
import static it.surveyharvester.access.quadrato.SchedaQuadratoQueryContainer.deleteSchedaQuadrato;
import static it.surveyharvester.access.quadrato.SchedaQuadratoQueryContainer.getSchedaQuadratoRepository;
import static it.surveyharvester.access.quadrato.SchedaQuadratoQueryContainer.puntiBattutiQuery;
import static it.surveyharvester.utils.Utilities.getFloat;
import static it.surveyharvester.utils.Utilities.getInt;
import static it.surveyharvester.utils.Utilities.getString;
import static it.surveyharvester.utils.Utilities.getStrings;
import static it.surveyharvester.utils.Utilities.res;
import static java.util.stream.Collectors.joining;

import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.vocabulary.RDF;

import com.google.common.collect.Lists;

import it.surveyharvester.model.SchedaQuadrato;
import it.surveyharvester.viewmodel.SchedaQuadratoAggregate;


public class SchedaQuadratoRepository {

    private final Model rdfModel;

    public SchedaQuadratoRepository(final Model rdfModel) {
        this.rdfModel = rdfModel;
    }

    public void deleteSchedaQuadrato(SchedaQuadrato schedaQuadrato) {
        UpdateAction.parseExecute(deleteSchedaQuadrato.replace("<numeroScheda>", schedaQuadrato.getNumeroScheda().toString()), rdfModel);
    }

    public void insertSchedaQuadrato(SchedaQuadrato schedaQuadrato) {
        rdfModel.add(schedaQuadrato.getTriples());
    }

    public SchedaQuadratoAggregate getSchedaQuadratoAggregate(String number) {

        QueryExecution query = QueryExecutionFactory.create(getSchedaQuadratoRepository.replace("<numero>", number), rdfModel);
        Model schedaModel = query.execConstruct();

        Resource schedaQuadrato = schedaModel.listSubjectsWithProperty(RDF.type, res(schedaModel, surveyNs + "SchedaQuadrato")).nextResource();
        Resource quadrato = schedaModel.listSubjectsWithProperty(RDF.type, res(schedaModel, surveyNs + "Quadrato")).nextResource();

        List<Resource> puntiBattuti = Lists.newArrayList();
        QueryExecutionFactory.create(puntiBattutiQuery, schedaModel)
                .execSelect()
                .forEachRemaining(x -> {
                    puntiBattuti.add(x.getResource("p"));
                });

        // scheda quadrato
        SchedaQuadratoAggregate schedaAggregate = (SchedaQuadratoAggregate) new SchedaQuadratoAggregate()
                .setMeteo(getString(schedaQuadrato, surveyNs + "meteo").orElseGet(null))
                .setOraInizio(getString(schedaQuadrato, surveyNs + "oraInizio").orElseGet(null))
                .setOraFine(getString(schedaQuadrato, surveyNs + "oraFine").orElseGet(null))
                .setDescrizione(getString(schedaQuadrato, surveyNs + "descrizione").orElseGet(null))
                .setOsservazioni(getString(schedaQuadrato, surveyNs + "osservazioni").orElseGet(null))
                .setAutore1(getString(schedaQuadrato, surveyNs + "autore1").orElseGet(null))
                .setAutore2(getString(schedaQuadrato, surveyNs + "autore2").orElseGet(null))
                .setNumeroScheda(getInt(schedaQuadrato, surveyNs + "numeroScheda").orElseGet(null))
                .setArea(Integer.parseInt(getString(schedaQuadrato, surveyNs + "area").orElseGet(null)))
                .setUt(getString(schedaQuadrato, surveyNs + "ut").orElseGet(null))
                .setData(getString(schedaQuadrato, surveyNs + "data").orElseGet(null));


        // quadrato
        schedaAggregate
                .setFisiografia(getString(quadrato, surveyNs + "fisiografia").orElseGet(null))
                .setVegetazione(getString(quadrato, surveyNs + "vegetazione").orElseGet(null))
                .setVisibilita(getString(quadrato, surveyNs + "visibilita").orElseGet(null))
                .setUmidita(getString(quadrato, surveyNs + "umidita").orElseGet(null))
                .setNumeroQuadrato(getString(quadrato, surveyNs + "numeroQuadrato").orElseGet(null));

        String[] tessitura = getStrings(quadrato, surveyNs + "tessituraTerreno").toArray(new String[] {});
        schedaAggregate.setTessitura(tessitura);

        // punti battuti
        schedaAggregate.setQuota1(getFloat(puntiBattuti.get(0), surveyNs + "quota").orElseGet(null));
        schedaAggregate.setQuota2(getFloat(puntiBattuti.get(1), surveyNs + "quota").orElseGet(null));
        schedaAggregate.setQuota3(getFloat(puntiBattuti.get(2), surveyNs + "quota").orElseGet(null));
        schedaAggregate.setQuota4(getFloat(puntiBattuti.get(3), surveyNs + "quota").orElseGet(null));

        schedaAggregate.setIdentificatoriPicchetti(
                puntiBattuti
                        .stream()
                        .map(x -> getString(x, surveyNs + "identificatorePicchetto").orElseGet(null))
                        .collect(joining(", ")));
        schedaAggregate.setLatitudiniString(
                puntiBattuti
                        .stream()
                        .map(x -> getString(x, surveyNs + "latitudine").orElseGet(null))
                        .collect(joining(", ")));
        schedaAggregate.setLongitudiniString(
                puntiBattuti
                        .stream()
                        .map(x -> getString(x, surveyNs + "longitudine").orElseGet(null))
                        .collect(joining(", ")));

        return schedaAggregate;
    }

}
