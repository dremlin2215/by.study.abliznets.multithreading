package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BusstopNameEnum {
    INSTANCE;

    List<String> busstopNames = new ArrayList<>(Arrays.asList("DS_SLAVINSKOGO",
            "SEVASTOPOLSKIY_PARK",
            "BOLSHAIA_SLEPNIA",
            "KEDISHKO",
            "METRO_MOSKOVSKAIA",
            "DZD",
            "METRO_PARK_CHELIUSKINCEV",
            "PLOSCHAD_KALININA",
            "DOM_PECHATI",
            "BROVKI",
            "PLOSCHAD_Y_KOLASA",
            "KOZLOVA",
            "PLOSCHAD_POBEDI",
            "Y_KUPALI",
            "METRO_OKTYABRSKAIA",
            "LENINA",
            "PLOSCHAD_NEZAVISIMOSTI",
            "AKADIMIA_UPRAVLENIA",
            "HOTEL_SPUTNIK",
            "CHKALOVA",
            "AEROPORT_MINSK_1"));


    public List<String> getNames() {
        return busstopNames;
    }

}
