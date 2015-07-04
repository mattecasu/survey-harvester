package it.surveyharvester.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import it.surveyharvester.config.SurveyHarvesterConfig;
import it.surveyharvester.viewmodel.SchedaUSAggregate;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SchedaUSController {

    @Autowired
    SurveyHarvesterConfig config;

    public SchedaUSController() {}

    @RequestMapping(value = "/us", method = GET)
    public ModelAndView quadrati(@RequestParam(required = false) String number) {
        if (number == null) {
            return new ModelAndView("scheda_US");
        }
        else {
            return new ModelAndView("scheda_US")
             .addObject("scheda", config.schedaUSRepo().getSchedaUSAggregate(number))
            ;
        }
    }

    @RequestMapping(value = "/inserisciUS", method = POST)
    public ModelAndView inserisciUS(SchedaUSAggregate schedaAggregate) throws IOException {
        // SchedaUS schedaUS = schedaAggregate.populate().getSchedaUS();
        // config.schedaUSRepo().deleteSchedaUS(schedaUS);
        // config.schedaUSRepo().insertSchedaUS(schedaUS);
        ModelAndView mv = new ModelAndView("scheda_US");
        // RDFDataMgr
        // .write(Files.newOutputStream(Paths.get(config.dumpFilePath)),
        // config.homeRepo().getRdfModel(), Lang.TURTLE);
        return mv;
    }


}
