package com.springtrainning.model;


import javax.persistence.Entity;

/*
 * A tag @Entity, irá mapear a classe para uma tabela no banco de dados
 * */
@Entity
public class Person extends AbstractEntity {
    private String name;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
