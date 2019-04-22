package com.lambdaschool.starwarsapitransitionsdemo;

import org.json.JSONException;
import org.json.JSONObject;

// S04M03-4 start dao
public class SwApiDao {
    private static final String BASE_URL = "https://swapi.co/api/";
    private static final String PERSON_URL = BASE_URL + "people/";
    private static final String STARSHIP_URL = BASE_URL + "starships/";

    // S04M03-5 write method to get person
    public static Person getPerson(int id) {
        final String result = NetworkAdapter.httpRequest(PERSON_URL + id);

        Person object = null;
        try {
            JSONObject json = new JSONObject(result);

            final String name = json.getString("name");
            final String height = json.getString("height");
            final String mass = json.getString("mass");
            final String hairColor = json.getString("hair_color");
            final String skinColor = json.getString("skin_color");
            final String eyeColor = json.getString("eye_color");

            object = new Person(id, name,height,mass,hairColor,skinColor,eyeColor);
            object.setCategory(DrawableResolver.CHARACTER);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    public static Starship getStarship(int id) {
        final String result = NetworkAdapter.httpRequest(STARSHIP_URL + id);

        Starship object = null;
        try {
            JSONObject json = new JSONObject(result);

            String name = json.getString("name");
            String model = json.getString("model");
            String manufacturer = json.getString("manufacturer");
            String cost = json.getString("cost_in_credits");
            String length = json.getString("length");
            object = new Starship(id, name,model,manufacturer,cost,length);
            object.setCategory(DrawableResolver.STARSHIP);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}
