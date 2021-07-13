package model;

import model.enums.CardEnum;

/**
 * Created by pabloestradaleon on 12/12/2020.
 */
public class Card {


    // Types
    public static final int TYPE_ETHER = 1;
    public static final int TYPE_ELEMENTAL_GOD = 2;
    public static final int TYPE_ELEMENTAL = 3;
    public static final int TYPE_ACTION_GOD = 4;
    public static final int TYPE_FORWARD = 5;
    public static final int TYPE_SPECIAL_FORWARD= 6;
    public static final int TYPE_DISASTER = 7;

    // Some subtypes
    public static final int SUB_FIRE = 1;
    public static final int SUB_WATER = 2;
    public static final int SUB_EARTH = 3;
    public static final int SUB_AIR = 4;
    public static final int SUB_ETHER = 5;
    
    // values
    public static final int DISASTER_ALUD = 1;
    public static final int DISASTER_CICLON = 2;
    public static final int DISASTER_METEORITO = 3;
    public static final int DISASTER_TORMENTA = 4;
    public static final int DISASTER_TORNADO = 5;
    public static final int DISASTER_VOLCAN = 6;
    
    public static final int ACTION_GOD_ADICIA = 1;
    public static final int ACTION_GOD_APATE = 2;
    public static final int ACTION_GOD_ARES = 3;
    public static final int ACTION_GOD_ARTEMISA = 4;
    public static final int ACTION_GOD_AUTOLICO = 5;
    public static final int ACTION_GOD_BOREAS = 6;
    public static final int ACTION_GOD_HADES = 7;
    public static final int ACTION_GOD_HARMONIA = 8;
    public static final int ACTION_GOD_HECATE = 9;
    public static final int ACTION_GOD_HERMES = 10;
    public static final int ACTION_GOD_TIQUE = 11;
    public static final int ACTION_GOD_ELPIS = 12;

    private String name;
    private int type;
    private int subtype;
    private int value;
    private String description;
    private String urlImage;

    public Card(CardEnum cardEnum){
        this.name = cardEnum.getName();
        this.type = cardEnum.getCardType();
        this.value = cardEnum.getValue();
        this.subtype = cardEnum.getCardSubtype();
        if (cardEnum.getDescription()!=null && !"".equalsIgnoreCase(cardEnum.getDescription())){
            this.description = cardEnum.getDescription();
        } else {
            this.description = cardEnum.getName();
        }
        this.urlImage=cardEnum.getUrlImage();
    }

    public Card(String name, int type, int subtype, int value, String description, String urlImage) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.subtype = subtype;
        if (description!=null && !"".equalsIgnoreCase(description)){
            this.description = description;
        } else {
            this.description = name;
        }
        this.urlImage=urlImage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (type != card.type) return false;
        if (subtype != card.subtype) return false;
        return value == card.value;
    }



    @Override
    public String toString() {
        return  name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSubtype() {
        return subtype;
    }

    public void setSubtype(int subtype) {
        this.subtype = subtype;
    }
}
