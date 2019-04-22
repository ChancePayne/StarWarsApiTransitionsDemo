package com.lambdaschool.starwarsapitransitionsdemo;

public class Person extends SwApiObject {

    public Person(int id, String name) {
        super(id, name);
    }

    private String height, mass, harcolor, skinColor, eyeColor;

    public Person(int id, String name, String height, String mass, String harcolor, String skinColor, String eyeColor) {
        super(id, name);
        this.height = height;
        this.mass = mass;
        this.harcolor = harcolor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHarcolor() {
        return harcolor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }
    
}
