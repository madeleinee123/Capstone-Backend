package com.whelmed.demo.model;

public class List {
    private Long id;
    private String name;
    private String description; // description of what the list is for

    public List(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public List(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "List{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}