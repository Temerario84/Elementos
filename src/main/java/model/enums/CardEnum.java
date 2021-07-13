package model.enums;

import model.Card;

public enum CardEnum {

    ETHER("Ether",Card.TYPE_ETHER, 0, 0, "Ether", "ether.jpeg"),
    GOD_FIRE("Dios fuego",Card.TYPE_ELEMENTAL_GOD, Card.SUB_FIRE, 0, "Dios fuego", "godFire.jpeg"),
    GOD_WATER("Dios agua",Card.TYPE_ELEMENTAL_GOD, Card.SUB_WATER, 0, "Dios agua", "godWater.jpeg"),
    GOD_EARTH("Dios tierra",Card.TYPE_ELEMENTAL_GOD, Card.SUB_EARTH, 0, "Dios tierra", "godEarth.jpeg"),
    GOD_AIR("Dios aire",Card.TYPE_ELEMENTAL_GOD, Card.SUB_AIR, 0, "Dios aire", "godAir.jpeg"),

    ELEM_FIRE("Elemental fuego",Card.TYPE_ELEMENTAL, Card.SUB_FIRE, 0, "Elemental fuego", "elemFire.jpeg"),
    ELEM_WATER("Elemental agua",Card.TYPE_ELEMENTAL, Card.SUB_WATER, 0, "Elemental agua", "elemWater.jpeg"),
    ELEM_EARTH("Elemental tierra",Card.TYPE_ELEMENTAL, Card.SUB_EARTH, 0, "Elemental tierra", "elemEarth.jpeg"),
    ELEM_AIR("Elemental aire",Card.TYPE_ELEMENTAL, Card.SUB_AIR, 0, "Elemental aire", "elemAir.jpeg"),

    ELEM_ETHER("Elemental ether",Card.TYPE_ELEMENTAL, Card.SUB_ETHER, 0, "Elemental ether", "elemEther.jpeg"),

    AG_ADICIA("Adicia",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_ADICIA, "Intercambia el lugar de tu ficha por el de la que quieras del mismo elemento", "adicia.jpeg"),
    AG_APATE("Apate",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_APATE, "Intercambia todas tus cartas por las del jugador que quieras", "apate.jpeg"),
    AG_ARES("Ares",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_ARES, "El jugador que elijas no roba carta en el siguiente turno", "ares.jpeg"),
    AG_ARTEMISA("Artemisa",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_ARTEMISA, "Todos te entregan las cartas de Avance del elemento que elijas", "artemisa.jpeg"),
    AG_AUTOLICO("Autolico",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_AUTOLICO, "Roba una carta al azar del jugador que elijas", "autolico.jpeg"),
    AG_BOREAS("Boreas",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_BOREAS, "Las fichas del resto de jugadores no podrán avanzar durante dos turnos en el elemento que elijas", "boreas.jpeg"),
    AG_HADES("Hades",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_HADES, "La ficha del jugador que elijas retrocede cinco casillas", "hades.jpeg"),
    //AG_HARMONIA("Harmonia",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_HARMONIA, "Permite utilizar dos cartas de avance en el mismo turno", "harmonia.jpeg"),
    //AG_HECATE("Hecate",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_HECATE, "Multiplica por dos la puntuación de la carta de Avance que quieras", "hecate.jpeg"),
    AG_HERMES("Hermes",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_HERMES, "Avanza seis casillas en el elemento que quieras", "hermes.jpeg"),
    AG_TIQUE("Tique",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_TIQUE, "Avanza tres casillas en el elemento que quieras", "tique.jpeg"),
    AG_ELPIS("Elpis",Card.TYPE_ACTION_GOD, Card.SUB_ETHER, Card.ACTION_GOD_ELPIS, "Roba tres cartas del mazo de robo", "elpis.jpeg"),

    // avance
    AV_FIRE1("Avance fuego 1",Card.TYPE_FORWARD, Card.SUB_FIRE, 1, "", "fire1.jpeg"),
    AV_FIRE2("Avance fuego 2",Card.TYPE_FORWARD, Card.SUB_FIRE, 2, "", "fire2.jpeg"),
    AV_FIRE3("Avance fuego 3",Card.TYPE_FORWARD, Card.SUB_FIRE, 3, "", "fire3.jpeg"),
    AV_FIRE4("Avance fuego 4",Card.TYPE_FORWARD, Card.SUB_FIRE, 4, "", "fire4.jpeg"),
    AV_FIRE5("Avance fuego 5",Card.TYPE_FORWARD, Card.SUB_FIRE, 5, "", "fire5.jpeg"),
    AV_WATER1("Avance agua 1",Card.TYPE_FORWARD, Card.SUB_WATER, 1, "", "water1.jpeg"),
    AV_WATER2("Avance agua 2",Card.TYPE_FORWARD, Card.SUB_WATER, 2, "", "water2.jpeg"),
    AV_WATER3("Avance agua 3",Card.TYPE_FORWARD, Card.SUB_WATER, 3, "", "water3.jpeg"),
    AV_WATER4("Avance agua 4",Card.TYPE_FORWARD, Card.SUB_WATER, 4, "", "water4.jpeg"),
    AV_WATER5("Avance agua 5",Card.TYPE_FORWARD, Card.SUB_WATER, 5, "", "water5.jpeg"),
    AV_EARTH1("Avance tierra 1",Card.TYPE_FORWARD, Card.SUB_EARTH, 1, "", "earth1.jpeg"),
    AV_EARTH2("Avance tierra 2",Card.TYPE_FORWARD, Card.SUB_EARTH, 2, "", "earth2.jpeg"),
    AV_EARTH3("Avance tierra 3",Card.TYPE_FORWARD, Card.SUB_EARTH, 3, "", "earth3.jpeg"),
    AV_EARTH4("Avance tierra 4",Card.TYPE_FORWARD, Card.SUB_EARTH, 4, "", "earth4.jpeg"),
    AV_EARTH5("Avance tierra 5",Card.TYPE_FORWARD, Card.SUB_EARTH, 5, "", "earth5.jpeg"),
    AV_AIR1("Avance aire 1",Card.TYPE_FORWARD, Card.SUB_AIR, 1, "", "air1.jpeg"),
    AV_AIR2("Avance aire 2",Card.TYPE_FORWARD, Card.SUB_AIR, 2, "", "air2.jpeg"),
    AV_AIR3("Avance aire 3",Card.TYPE_FORWARD, Card.SUB_AIR, 3, "", "air3.jpeg"),
    AV_AIR4("Avance aire 4",Card.TYPE_FORWARD, Card.SUB_AIR, 4, "", "air4.jpeg"),
    AV_AIR5("Avance aire 5",Card.TYPE_FORWARD, Card.SUB_AIR, 5, "", "air5.jpeg"),
    AV_ETHER1("Avance ether 1",Card.TYPE_FORWARD, Card.SUB_ETHER, 1, "", "ether1.jpeg"),
    AV_ETHER2("Avance ether 2",Card.TYPE_FORWARD, Card.SUB_ETHER, 2, "", "ether2.jpeg"),
    AV_ETHER3("Avance ether 3",Card.TYPE_FORWARD, Card.SUB_ETHER, 3, "", "ether3.jpeg"),
    AV_ETHER4("Avance ether 4",Card.TYPE_FORWARD, Card.SUB_ETHER, 4, "", "ether4.jpeg"),
    AV_ETHER5("Avance ether 5",Card.TYPE_FORWARD, Card.SUB_ETHER, 5, "", "ether5.jpeg"),

    // avance especial
    AV_S_FIRE1("Avance fuego 1*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_FIRE, 1, "", "sfire1.jpeg"),
    AV_S_FIRE2("Avance fuego 2*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_FIRE, 2, "", "sfire2.jpeg"),
    AV_S_FIRE3("Avance fuego 3*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_FIRE, 3, "", "sfire3.jpeg"),
    AV_S_FIRE4("Avance fuego 4*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_FIRE, 4, "", "sfire4.jpeg"),
    AV_S_FIRE5("Avance fuego 5*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_FIRE, 5, "", "sfire5.jpeg"),
    AV_S_WATER1("Avance agua 1*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_WATER, 1, "", "swater1.jpeg"),
    AV_S_WATER2("Avance agua 2*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_WATER, 2, "", "swater2.jpeg"),
    AV_S_WATER3("Avance agua 3*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_WATER, 3, "", "swater3.jpeg"),
    AV_S_WATER4("Avance agua 4*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_WATER, 4, "", "swater4.jpeg"),
    AV_S_WATER5("Avance agua 5*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_WATER, 5, "", "swater5.jpeg"),
    AV_S_EARTH1("Avance tierra 1*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_EARTH, 1, "", "searth1.jpeg"),
    AV_S_EARTH2("Avance tierra 2*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_EARTH, 2, "", "searth2.jpeg"),
    AV_S_EARTH3("Avance tierra 3*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_EARTH, 3, "", "searth3.jpeg"),
    AV_S_EARTH4("Avance tierra 4*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_EARTH, 4, "", "searth4.jpeg"),
    AV_S_EARTH5("Avance tierra 5*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_EARTH, 5, "", "searth5.jpeg"),
    AV_S_AIR1("Avance aire 1*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_AIR, 1, "", "sair1.jpeg"),
    AV_S_AIR2("Avance aire 2*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_AIR, 2, "", "sair2.jpeg"),
    AV_S_AIR3("Avance aire 3*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_AIR, 3, "", "sair3.jpeg"),
    AV_S_AIR4("Avance aire 4*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_AIR, 4, "", "sair4.jpeg"),
    AV_S_AIR5("Avance aire 5*",Card.TYPE_SPECIAL_FORWARD, Card.SUB_AIR, 5, "", "sair5.jpeg"),
    ;


    private String name;
    private int cardType;
    private int cardSubtype;
    private int value;
    private String description;
    private String urlImage;


    CardEnum(String name, int cardType, int cardSubtype, int value, String description, String urlImage) {
        this.name = name;
        this.cardType = cardType;
        this.cardSubtype = cardSubtype;
        this.value = value;
        this.description = description;
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public int getCardType() {
        return cardType;
    }

    public int getCardSubtype() {
        return cardSubtype;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
