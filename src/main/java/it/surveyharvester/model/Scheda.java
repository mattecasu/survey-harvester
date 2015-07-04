package it.surveyharvester.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Accessors(chain = true)
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Scheda {

    String sep = " ,";

    Integer numeroScheda;
    String ut;
    String data;
    Integer area;

    String descrizione,
            osservazioni,
            autore1,
            autore2;

}
