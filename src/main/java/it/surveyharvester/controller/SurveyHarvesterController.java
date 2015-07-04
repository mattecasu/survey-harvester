package it.surveyharvester.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import it.surveyharvester.config.SurveyHarvesterConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SurveyHarvesterController {

    @Autowired
    SurveyHarvesterConfig config;

    public SurveyHarvesterController() {}

    @RequestMapping(value = "/", method = GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("schedeQuadrato", config.homeRepo().getSchedeQuadrato());
        mv.addObject("schedeUS", config.homeRepo().getSchedeUS());
        mv.addObject("schedeShovel", config.homeRepo().getSchedeShovel());
        return mv;
    }

}
