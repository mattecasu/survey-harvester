package it.surveyharvester.viewmodel;

import static com.google.common.collect.Lists.newArrayList;
import static it.surveyharvester.utils.Utilities.extractListFromString;
import static lombok.AccessLevel.PRIVATE;
import it.surveyharvester.model.Scheda;
import it.surveyharvester.model.SchedaUS;
import it.surveyharvester.model.US;
import it.surveyharvester.model.US.DimensioniComponentiLitiche;
import it.surveyharvester.viewmodel.interfaces.SurveyHarvesterAggregate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Accessors(chain = true)
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class SchedaUSAggregate extends Scheda implements SurveyHarvesterAggregate {

    SchedaUS schedaUS;
    US us;

    public SchedaUSAggregate() {}

    // scheda
    String definizione,
            gradoDiAffidabilita;

    Integer numeroUS;
    String quadratiString;
    String tipo;
    Float lunghezza;
    Float altezza;
    Float spessore;
    String superficie;
    String materiale;
    String forma;
    String tipologia;
    Float lunghezzaMinimaComponentiLitiche;
    Float lunghezzaMassimaComponentiLitiche;
    Float larghezzaMinimaComponentiLitiche;
    Float larghezzaMassimaComponentiLitiche;
    String[] tipoCorso;
    String[] altre;
    String gradoDiConservazione;
    String tessituraAcciottolato;
    String superficie2;
    boolean rocce;

    String ugualeAString,
            siLegaAString,
            siAppoggiaAString,
            gliSiAppoggiaString,
            copreString,
            copertoDaString,
            tagliaString,
            tagliatoDaString,
            riempieString,
            riempitoDaString;

    @Override
    public SchedaUSAggregate populate() {
        DimensioniComponentiLitiche dimComponentiLitiche =
                new DimensioniComponentiLitiche(lunghezzaMinimaComponentiLitiche, lunghezzaMassimaComponentiLitiche,
                        larghezzaMinimaComponentiLitiche, larghezzaMassimaComponentiLitiche);
        us = new US(numeroUS)
                .build(
                        numeroUS,
                        extractListFromString(quadratiString, sep),
                        tipo,
                        lunghezza, altezza, spessore,
                        superficie,
                        materiale,
                        forma,
                        tipologia,
                        dimComponentiLitiche,
                        newArrayList(tipoCorso), newArrayList(altre),
                        gradoDiConservazione, tessituraAcciottolato,
                        superficie2, rocce,
                        extractListFromString(ugualeAString, sep),
                        extractListFromString(siLegaAString, sep),
                        extractListFromString(siAppoggiaAString, sep),
                        extractListFromString(gliSiAppoggiaString, sep),
                        extractListFromString(copreString, sep),
                        extractListFromString(copertoDaString, sep),
                        extractListFromString(tagliaString, sep),
                        extractListFromString(tagliatoDaString, sep),
                        extractListFromString(riempieString, sep),
                        extractListFromString(riempitoDaString, sep)
                );
        schedaUS = new SchedaUS(numeroScheda, ut, us)
                .build(
                        definizione,
                        gradoDiAffidabilita,
                        data,
                        area,
                        descrizione,
                        osservazioni,
                        autore1,
                        autore2
                );
        return this;
    }

}
