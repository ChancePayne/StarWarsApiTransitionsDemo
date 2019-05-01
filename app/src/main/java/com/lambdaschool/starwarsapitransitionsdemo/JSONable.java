package com.lambdaschool.starwarsapitransitionsdemo;

public interface JSONable {
    String toJsonString();
    void fromJsonString(String jsonString);
}
