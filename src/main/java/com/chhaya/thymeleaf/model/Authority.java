package com.chhaya.thymeleaf.model;

import java.io.Serializable;

public class Authority implements Serializable {

    private int id;
    private String name;

    public Authority() {}

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

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
