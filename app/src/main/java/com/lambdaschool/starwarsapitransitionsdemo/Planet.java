package com.lambdaschool.starwarsapitransitionsdemo;

public class Planet extends SwApiObject {
    /*	"name": "Yavin IV",
	"rotation_period": "24",
	"orbital_period": "4818",
	"diameter": "10200",
	"climate": "temperate, tropical",
	"gravity": "1 standard",
	"terrain": "jungle, rainforests",*/

    private String rotationPeriod, orbitalPeriod, diameter, climate, gravity, terrain;

    public Planet(int id, String name, String rotationPeriod, String orbitalPeriod, String diameter, String climate, String gravity, String terrain) {
        super(id, name);
        this.rotationPeriod = rotationPeriod;
        this.orbitalPeriod = orbitalPeriod;
        this.diameter = diameter;
        this.climate = climate;
        this.gravity = gravity;
        this.terrain = terrain;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "rotationPeriod='" + rotationPeriod + '\'' +
                ", orbitalPeriod='" + orbitalPeriod + '\'' +
                ", diameter='" + diameter + '\'' +
                ", climate='" + climate + '\'' +
                ", gravity='" + gravity + '\'' +
                ", terrain='" + terrain + '\'' +
                '}';
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getDiameter() {
        return diameter;
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
}
