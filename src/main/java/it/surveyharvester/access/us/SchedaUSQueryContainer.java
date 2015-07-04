package it.surveyharvester.access.us;

import it.surveyharvester.access.SurveyHarvesterQueryContainer;

public class SchedaUSQueryContainer extends SurveyHarvesterQueryContainer {

    public static final String deleteSchedaUS = prefixes +
            "DELETE {?scheda ?pScheda ?oScheda.}\n"
            + "WHERE\n"
            + "{"
            + "?scheda survey:numeroScheda <numeroScheda>; a survey:SchedaUS; ?pScheda ?oScheda."
            + "}";

}
