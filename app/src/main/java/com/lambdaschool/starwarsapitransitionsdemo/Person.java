package com.lambdaschool.starwarsapitransitionsdemo;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Person extends SwApiObject implements JSONable {

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

    @Override
    public String toJsonString() {
        try {
            JSONObject json = new JSONObject();
            json.put("name", this.name);
            json.put("height", height);
            json.put("hair_color", hairColor);
            json.put("skin_color", skinColor);
            json.put("eye_color", eyeColor);
            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void fromJsonString(String jsonString) {
        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
            this.name = json.getString("name");
            this.height = json.getString("height");
            this.mass = json.getString("mass");
            this.hairColor = json.getString("hair_color");
            this.skinColor = json.getString("skin_color");
            this.eyeColor = json.getString("eye_color");
            String[] urlParts = json.getString("url").split("/");
            this.id = Integer.parseInt(urlParts[urlParts.length -2]);
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
