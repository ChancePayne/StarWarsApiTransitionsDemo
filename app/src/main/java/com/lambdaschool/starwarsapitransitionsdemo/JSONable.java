package com.lambdaschool.starwarsapitransitionsdemo;

public interface JSONable {

    public String toJsonString();
    public void fromJsonString(String string);
}
