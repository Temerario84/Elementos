package model.enums;

import model.Card;

public enum DisasterEnum {
    // natural disasters
    ND_ALUD("Alud", Card.TYPE_DISASTER, Card.SUB_ETHER, Card.DISASTER_ALUD, "", "alud.jpg"),
    ND_CICLON("Ciclon",Card.TYPE_DISASTER, Card.SUB_ETHER, Card.DISASTER_CICLON, "", "ciclon.jpg"),
    ND_METEORITO("Meteorito",Card.TYPE_DISASTER, Card.SUB_ETHER, Card.DISASTER_METEORITO, "", "meteorito.jpg"),
    ND_TORMENTA("Tormenta",Card.TYPE_DISASTER, Card.SUB_ETHER, Card.DISASTER_TORMENTA, "", "tormenta.jpg"),
    ND_TORNADO("Tornado",Card.TYPE_DISASTER, Card.SUB_ETHER, Card.DISASTER_TORNADO, "", "tornado.jpg"),
    ND_VOLCAN("Volcan",Card.TYPE_DISASTER, Card.SUB_ETHER, Card.DISASTER_VOLCAN, "", "volcan.jpg"),
    ;


    private String name;
    private int cardType;
    private int cardSubtype;
    private int value;
    private String description;
    private String urlImage;


    DisasterEnum(String name, int cardType, int cardSubtype, int value, String description, String urlImage) {
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
