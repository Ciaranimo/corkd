package com.ciaranbyrne.corkd.activity;

/**
 * Created by ciaranbyrne on 17/12/2016.
 */

public class Wine {
    int id;
    String name;
    String type;

    public Wine(){

    }

    public Wine(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

