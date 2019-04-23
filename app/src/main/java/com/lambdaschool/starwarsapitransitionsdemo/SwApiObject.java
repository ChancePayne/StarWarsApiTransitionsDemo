package com.lambdaschool.starwarsapitransitionsdemo;

import java.io.Serializable;

// S04M03-3 Add Model Object
public abstract class SwApiObject implements Serializable {
    protected int    id;
    protected String category, name;

    public SwApiObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    abstract public String toString();
    /*public String toString() {
        return name;
    }*/
}
