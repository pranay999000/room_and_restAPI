package com.example.roomandrestapi.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roomandrestapi.model.Country;

import java.util.List;

@Dao
public interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Country> countries);

    @Query("SELECT DISTINCT * FROM country")
    LiveData<List<Country>> getCountries();

    @Query("DELETE FROM country")
    void deleteAll();
}
