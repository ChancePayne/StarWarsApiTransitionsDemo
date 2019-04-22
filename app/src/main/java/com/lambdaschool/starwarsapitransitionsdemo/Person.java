package com.lambdaschool.starwarsapitransitionsdemo;

// S06M01 - 1 Add class which inherits from parent class
public class Person extends SwApiObject {

    private String height, mass, hairColor, skinColor, eyeColor;

    public Person(int id, String name) {
        super(id, name);
    }

    public Person(int id, String name, String height, String mass, String hairColor, String skinColor, String eyeColor) {
        super(id, name);
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    @Override
    public String toString() {
        return String.format("%s, is %scm tall, they have %s skin, and %s hair with %s eyes.",
                             this.name, height, skinColor, hairColor, eyeColor);
    }
}
