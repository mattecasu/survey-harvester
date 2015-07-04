package it.surveyharvester.config;

import it.surveyharvester.access.SurveyHarvesterRepository;
import it.surveyharvester.access.quadrato.SchedaQuadratoRepository;
import it.surveyharvester.access.shovel.SchedaShovelRepository;
import it.surveyharvester.access.us.SchedaUSRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class SurveyHarvesterConfig {

    @Value(value = "/Users/epi/Desktop/materializedRastello.owl")
    public String dumpFilePath;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public SurveyHarvesterRepository homeRepo() {
        return new SurveyHarvesterRepository("rastelloOntology.ttl", "TTL");
    }

    @Bean
    public SchedaQuadratoRepository schedaQuadratoRepo() {
        return homeRepo().getSchedaQuadratoRepo();
    }

    @Bean
    public SchedaUSRepository schedaUSRepo() {
        return homeRepo().getSchedaUSRepo();
    }

    @Bean
    public SchedaShovelRepository schedaShovelRepo() {
        return homeRepo().getSchedaShovelRepo();
    }

}
