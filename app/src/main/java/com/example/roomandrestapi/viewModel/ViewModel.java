package com.example.roomandrestapi.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomandrestapi.model.Country;
import com.example.roomandrestapi.repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<List<Country>> getAllCountries;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        getAllCountries = repository.getAllCountry();
    }

    public void insert(List<Country> countryList) {
        repository.insert(countryList);
    }

    public LiveData<List<Country>> getAllCountries() {
        return getAllCountries;
    }
}
