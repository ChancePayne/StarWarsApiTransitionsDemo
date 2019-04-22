package com.lambdaschool.starwarsapitransitionsdemo;

public class Starship extends SwApiObject {
    /*"model": "Executor-class star dreadnought",
			"manufacturer": "Kuat Drive Yards, Fondor Shipyards",
			"cost_in_credits": "1143350000",
			"length": "19000"*/

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
        return String.format("The %s, model %s is manufactured by %s is %s meters in length, built at a cost of %s credits",
                name, model, manufacturer, length, costInCredits);
    }
}
