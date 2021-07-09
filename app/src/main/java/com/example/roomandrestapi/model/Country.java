package com.example.roomandrestapi.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "country", indices = @Index(value = { "name" }, unique = true))
public class Country {

    @PrimaryKey@NonNull
    @SerializedName("name")
    @ColumnInfo(name = "name")
    String name;

    @SerializedName("capital")
    @ColumnInfo(name = "capital")
    String capital;

    @SerializedName("flag")
    @ColumnInfo(name = "flag")
    String flag;

    @SerializedName("region")
    @ColumnInfo(name = "region")
    String region;

    @SerializedName("subregion")
    @ColumnInfo(name = "subregion")
    String subregion;

    @SerializedName("population")
    @ColumnInfo(name = "population")
    long population;

    @SerializedName("borders")
    @ColumnInfo(name = "borders")
    String borders;

    @SerializedName("languages")
    @ColumnInfo(name = "languages")
    String languages;

    public Country(@NonNull String name, String capital, String flag, String region, String subregion, long population, String borders, String languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    @NonNull
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
