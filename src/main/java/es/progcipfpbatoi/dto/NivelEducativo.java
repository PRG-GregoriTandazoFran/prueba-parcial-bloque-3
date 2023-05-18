package es.progcipfpbatoi.dto;

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
}
