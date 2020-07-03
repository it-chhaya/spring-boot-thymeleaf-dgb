package com.chhaya.thymeleaf.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Category implements Serializable {

    private int id;

    @NotBlank
    private String name;

    private boolean status;

    public Category() {}

    public Category(int id, @NotBlank String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

}
