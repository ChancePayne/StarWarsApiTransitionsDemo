package com.lambdaschool.starwarsapitransitionsdemo;

import org.json.JSONException;
import org.json.JSONObject;

// S04M03-4 start dao
public class SwApiDao {
    private static final String BASE_URL = "https://swapi.co/api/";
    private static final String PERSON_URL = BASE_URL + "people/";
    private static final String STARSHIP_URL = BASE_URL + "starships/";
    private static final String PLANET_URL = BASE_URL + "planets/";

    public interface SwApiCallback {
        void processObject(SwApiObject object);
    }

    // S04M03-5 write method to get person
    // S06M01-2 refactor method to retrieve person object
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

            object = new Person(id, name, height, mass, hairColor, skinColor, eyeColor);
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

            final String name = json.getString("name");
            final String model = json.getString("model");
            final String mfg = json.getString("manufacturer");
            final String cost = json.getString("cost_in_credits");
            final String length = json.getString("length");

            object = new Starship(id, name, model, mfg, cost, length);
            object.setCategory(DrawableResolver.STARSHIP);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    public static void getPlanet(final int id, final SwApiCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String result = NetworkAdapter.httpRequest(PLANET_URL + id);

                Planet object = null;
                try {
                    JSONObject json = new JSONObject(result);

                    final String name = json.getString("name");
                    final String rotationPeriod = json.getString("rotation_period");
                    final String orbitalPeriod = json.getString("orbital_period");
                    final String diameter = json.getString("diameter");
                    final String climate = json.getString("climate");
                    final String gravity = json.getString("gravity");
                    final String terrain = json.getString("terrain");

                    object = new Planet(id, name, rotationPeriod, orbitalPeriod, diameter, climate, gravity, terrain);
                    object.setCategory(DrawableResolver.PLANET);

                    callback.processObject(object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
