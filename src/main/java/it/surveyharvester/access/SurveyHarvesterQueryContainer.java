package it.surveyharvester.access;

import static lombok.AccessLevel.PUBLIC;

import java.util.StringJoiner;

import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import lombok.experimental.FieldDefaults;


@FieldDefaults(level = PUBLIC, makeFinal = true)
public class SurveyHarvesterQueryContainer {

    static StringJoiner joiner = new StringJoiner(",", "(", ")");

    static String surveyNs = "http://surveyharvester.net/ontology#";
    static String harvesterRastello = "http://surveyharvester.net/rastello/";

    static String prefixes =
            "prefix rdf:<" + RDF.getURI() + ">\n" +
                    "prefix rdfs:<" + RDFS.getURI() + ">\n" +
                    "prefix survey:<" + surveyNs + ">\n";

    static String getSchedeTemplate = prefixes +
            "select distinct ?schedaNumber where {?scheda a <type>. ?scheda survey:numeroScheda ?schedaNumber} order by ?schedaNumber";


}
