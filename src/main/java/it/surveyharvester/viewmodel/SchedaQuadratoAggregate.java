package it.surveyharvester.viewmodel;

import static com.google.common.collect.Lists.newArrayList;
import static it.surveyharvester.utils.Utilities.castToFloats;
import static it.surveyharvester.utils.Utilities.extractListFromString;
import static lombok.AccessLevel.PRIVATE;
import it.surveyharvester.model.PuntoBattuto;
import it.surveyharvester.model.Quadrato;
import it.surveyharvester.model.Scheda;
import it.surveyharvester.model.SchedaQuadrato;
import it.surveyharvester.viewmodel.interfaces.SurveyHarvesterAggregate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Accessors(chain = true)
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class SchedaQuadratoAggregate extends Scheda implements SurveyHarvesterAggregate {

    SchedaQuadrato schedaQuadrato;
    Quadrato quadrato;

    public SchedaQuadratoAggregate() {}

    @Override
    public SchedaQuadratoAggregate populate() {

        List<PuntoBattuto> puntiBattuti = newArrayList();
        ArrayList<Float> quote = newArrayList(quota1, quota2, quota3, quota4);
        List<String> picchetti = extractListFromString(identificatoriPicchetti, ", ");
        List<Float> latitudini = castToFloats(extractListFromString(latitudiniString, " ,"));
        List<Float> longitudini = castToFloats(extractListFromString(longitudiniString, " ,"));

        int i = 0;
        for (String id : picchetti) {
            puntiBattuti.add(
                    new PuntoBattuto(id)
                            .build(i, quote.get(i), latitudini.get(i), longitudini.get(i))
                    );

            i += 1;
        }

        quadrato = new Quadrato(numeroQuadrato)
                .build(area, numeroScheda, fisiografia, vegetazione, visibilita, newArrayList(tessitura), umidita, puntiBattuti);

        schedaQuadrato =
                new SchedaQuadrato(numeroScheda, ut, quadrato)
                        .build(LocalTime.parse(oraInizio), LocalTime.parse(oraFine), meteo, data, area, descrizione, osservazioni, autore1.trim(),
                                autore2.trim());

        return this;
    }

    // scheda quadrato

    String numeroQuadrato,
            meteo,
            oraInizio,
            oraFine;

    // Quadrato

    String fisiografia,
            vegetazione,
            visibilita;

    String[] tessitura;

    String umidita;

    // PuntoBattuto

    String identificatoriPicchetti,
            longitudiniString,
            latitudiniString;
    Float quota1, quota2, quota3, quota4;

}
