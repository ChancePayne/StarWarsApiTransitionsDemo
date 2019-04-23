package com.lambdaschool.starwarsapitransitionsdemo;

public class Starship extends SwApiObject {

    /*
    * "model": "DS-1 Orbital Battle Station",
	"manufacturer": "Imperial Department of Military Research, Sienar Fleet Systems",
	"cost_in_credits": "1000000000000",
	"length": "120000",
    * */

    private String model, manufacturer, costInCredits, length;

    public Starship(int id, String name) {
        super(id, name);
    }

    public Starship(int id, String name, String model, String manufacturer, String costInCredits, String length) {
        super(id, name);
        this.model = model;
        this.manufacturer = manufacturer;
        this.costInCredits = costInCredits;
        this.length = length;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCostInCredits() {
        return costInCredits;
    }

    public String getLength() {
        return length;
    }

    @Override
    public String toString() {
        return String.format("The %s, model %s is manufactured by %s is %s meters in length built at a cost of %s credits.", name, model, manufacturer, length, costInCredits);
    }
}