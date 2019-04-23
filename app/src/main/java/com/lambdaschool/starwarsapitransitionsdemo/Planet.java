package com.lambdaschool.starwarsapitransitionsdemo;

public class Planet extends SwApiObject {

    private String rotationPeriod, orbitalPeriod, diamet, climate, gravity, terrain;

    public Planet(int id, String name, String rotationPeriod, String orbitalPeriod, String diamet, String climate, String gravity, String terrain) {
        super(id, name);
        this.rotationPeriod = rotationPeriod;
        this.orbitalPeriod = orbitalPeriod;
        this.diamet = diamet;
        this.climate = climate;
        this.gravity = gravity;
        this.terrain = terrain;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getDiamet() {
        return diamet;
    }

    public String getClimate() {
        return climate;
    }

    public String getGravity() {
        return gravity;
    }

    public String getTerrain() {
        return terrain;
    }


    @Override
    public String toString() {
        return null;
    }
}
