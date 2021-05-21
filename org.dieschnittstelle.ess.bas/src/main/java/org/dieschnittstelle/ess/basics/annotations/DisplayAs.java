package org.dieschnittstelle.ess.basics.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DisplayAs {
    //    Deklarieren Sie einen Annotationstyp DisplayAs mit Attribut value, der für Attribute von Klassen
//    gesetzt und zur Laufzeit via Reflection ausgelesen werden kann.
     String value();
}
