package com.lambdaschool.starwarsapitransitionsdemo;

import java.io.Serializable;

// S04M03-3 Add Model Object
public abstract class SwApiObject implements Serializable, Comparable {
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

    public static int compareNames(SwApiObject o1, SwApiObject o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof SwApiObject) {
            SwApiObject object = (SwApiObject) o;

            return this.getName().compareTo(object.getName());

//            return this.getId() - object.getId();

        } else {
            return 0;
        }
    }
}
