package it.surveyharvester.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import it.surveyharvester.config.SurveyHarvesterConfig;
import it.surveyharvester.model.SchedaQuadrato;
import it.surveyharvester.viewmodel.SchedaQuadratoAggregate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SchedaQuadratoController {

    @Autowired
    SurveyHarvesterConfig config;

    public SchedaQuadratoController() {}

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(LocalDate.class, "data", new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/quadrati", method = GET)
    public ModelAndView quadrati(@RequestParam(required = false) String number) {
        if (number == null) {
            return new ModelAndView("scheda_quadrato");
        }
        else {
            return new ModelAndView("scheda_quadrato")
                    .addObject("scheda", config.schedaQuadratoRepo().getSchedaQuadratoAggregate(number));
        }
    }


    @RequestMapping(value = "/inserisciQuadrato", method = POST)
    public ModelAndView inserisciQuadrato(SchedaQuadratoAggregate schedaAggregate) throws IOException {
        SchedaQuadrato schedaQuadrato = schedaAggregate.populate().getSchedaQuadrato();
        config.schedaQuadratoRepo().deleteSchedaQuadrato(schedaQuadrato);
        config.schedaQuadratoRepo().insertSchedaQuadrato(schedaQuadrato);
        ModelAndView mv = new ModelAndView("scheda_quadrato");
        RDFDataMgr
                .write(Files.newOutputStream(Paths.get(config.dumpFilePath)), config.homeRepo().getRdfModel(), Lang.TURTLE);
        return mv;
    }


}
