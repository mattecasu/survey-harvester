package it.surveyharvester.access.quadrato;

import static lombok.AccessLevel.PUBLIC;
import it.surveyharvester.access.SurveyHarvesterQueryContainer;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = PUBLIC, makeFinal = true)
public class SchedaQuadratoQueryContainer extends SurveyHarvesterQueryContainer {

    static String puntiBattutiQuery = prefixes
            + "SELECT distinct ?p WHERE {?p a survey:PuntoBattuto; survey:order ?order.} ORDER BY ?order";

    static String deleteSchedaQuadrato = prefixes +
            "DELETE {?scheda ?pScheda ?oScheda."
            + "     ?quadrato ?pQuadrato ?oQuadrato."
            + "     ?punto ?pPunto ?oPunto.}\n"
            + "WHERE\n"
            + "{?scheda survey:numeroScheda <numeroScheda>; a survey:SchedaQuadrato; ?pScheda ?oScheda."
            + "?scheda survey:haQuadrato ?quadrato. ?quadrato ?pQuadrato ?oQuadrato."
            + "?quadrato survey:haPuntoBattuto ?punto."
            + "?punto ?pPunto ?oPunto.}";

    static String getSchedaQuadratoRepository =
            prefixes
                    +
                    "CONSTRUCT {?scheda ?pScheda ?oScheda. ?quadrato ?pQuadrato ?oQuadrato. ?puntoBattuto ?pPuntoBattuto ?oPuntoBattuto.} WHERE {"
                    + "?scheda  a survey:SchedaQuadrato;\n"
                    +
                    "         survey:numeroScheda <numero>;"
                    +
                    "         ?pScheda ?oScheda.\n"
                    +
                    "FILTER(?pScheda in (rdf:type, survey:numeroScheda, survey:ut, survey:data, survey:meteo, survey:area, survey:oraInizio, survey:oraFine, survey:descrizione, survey:osservazioni, survey:autore1, survey:autore2)).\n"
                    +
                    "?scheda survey:haQuadrato ?quadrato."
                    +
                    "?quadrato a survey:Quadrato;\n"
                    +
                    "          ?pQuadrato ?oQuadrato.\n"
                    +
                    "FILTER(?pQuadrato in (rdf:type, survey:numeroQuadrato, survey:fisiografia, survey:vegetazione, survey:visibilita, survey:tessituraTerreno, survey:umidita)).\n"
                    +
                    "?quadrato survey:haPuntoBattuto ?puntoBattuto."
                    +
                    "?puntoBattuto a survey:PuntoBattuto;\n"
                    +
                    "              ?pPuntoBattuto ?oPuntoBattuto.\n"
                    +
                    "FILTER(?pPuntoBattuto in (rdf:type, survey:order, survey:identificatorePicchetto, survey:quota, survey:latitudine, survey:longitudine))."
                    + "}";
}
