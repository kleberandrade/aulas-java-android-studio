package br.edu.fatec.aula.lendingstuffapp.models;

import java.io.Serializable;

public class Item implements Serializable {

    private long id;

    private String name;

    private String description;

    private String date;
    
    public Item(){
        this("","","");
    }

    public Item(String name, String description, String date) {
        this(0, name, description, date);
    }

    public Item(long id, String name, String description, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
