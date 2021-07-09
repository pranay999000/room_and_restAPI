package com.example.roomandrestapi.model;

import java.util.List;

public class GetCountry {
    String name;
    String capital;
    String region;
    String subregion;
    String flag;
    long population;

    List<String> borders;
    List<Language> languages;

    public GetCountry(String name, String capital, String region, String subregion, String flag, long population, List<String> borders, List<Language> languages) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.flag = flag;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
