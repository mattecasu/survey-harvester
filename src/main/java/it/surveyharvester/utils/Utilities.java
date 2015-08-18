package it.surveyharvester.utils;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.split;

import java.util.List;
import java.util.Optional;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;


public class Utilities {

    public static List<String> extractListFromString(String s, String separators) {
        return newArrayList(split(s, separators)).stream()
                .map(st -> st.trim())
                .collect(toList());
    }

    public static List<Float> castToFloats(List<String> l) {
        return l.stream()
                .map(x -> Float.valueOf(x))
                .collect(toList());
    }

    public static List<String> getStrings(Resource res, String propertyIri) {
        return res.listProperties(Utilities.prop(res.getModel(), propertyIri)).toList().stream()
                .map(x -> x.getObject().asLiteral().getString())
                .collect(toList());
    }

    public static Optional<String> getString(Resource res, String propertyIri) {
        Optional<Statement> p = Optional.<Statement>ofNullable(res.getProperty(prop(res.getModel(), propertyIri)));
        if (p.isPresent())
            return Optional.of(p.get().getObject().asLiteral().getString());
        else
            return empty();
    }

    public static Optional<Integer> getInt(Resource res, String propertyIri) {
        Optional<Statement> p = Optional.<Statement>ofNullable(res.getProperty(prop(res.getModel(), propertyIri)));
        if (p.isPresent())
            return Optional.of(p.get().getObject().asLiteral().getInt());
        else
            return empty();
    }

    public static Optional<Float> getFloat(Resource res, String propertyIri) {
        Optional<Statement> p = Optional.<Statement>ofNullable(res.getProperty(prop(res.getModel(), propertyIri)));
        if (p.isPresent())
            return Optional.of(p.get().getObject().asLiteral().getFloat());
        else
            return empty();
    }

    public static Resource res(Model model, String iri) {
        return model.createResource(iri);
    }

    public static Property prop(Model model, String iri) {
        return model.createProperty(iri);
    }

}
