package es.progcipfpbatoi.dto;

import java.util.ArrayList;

public enum NivelEducativo {
    INFANTIL, PRIMARIA, ESO, BACHILLERATO, FP;

    public static NivelEducativo parse(String text) {
        for ( NivelEducativo nivelEducativoItem :
                values() ) {
            if ( nivelEducativoItem.toString().equalsIgnoreCase( text ) ) {
                return nivelEducativoItem;
            }
        }
        return null;
    }

    public static ArrayList<String> getAllValuesInString() {
        ArrayList<String> valuesArrayList = new ArrayList<>();
        for ( NivelEducativo nivelEducativoItem :
                values() ) {
            valuesArrayList.add( nivelEducativoItem.toString() );
        }
        return valuesArrayList;
    }
}
