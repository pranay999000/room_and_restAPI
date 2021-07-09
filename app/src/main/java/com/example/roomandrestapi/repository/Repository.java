package com.example.roomandrestapi.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomandrestapi.database.CountryDao;
import com.example.roomandrestapi.database.Database;
import com.example.roomandrestapi.model.Country;

import java.util.List;

public class Repository {
    public CountryDao countryDao;
    public LiveData<List<Country>> getAllCountry;
    private Database database;

    public Repository(Application application) {
        database = Database.getInstance(application);
        countryDao = database.countryDao();
        getAllCountry = countryDao.getCountries();
    }

    public void insert(List<Country> countryList) {
        new InsertAsyncTask(countryDao).execute(countryList);
    }

    public void delete() {
        new DeleteAsyncTask(countryDao).execute();
    }

    public LiveData<List<Country>> getAllCountry() {
        return getAllCountry;
    }

    private static class InsertAsyncTask extends AsyncTask<List<Country>, Void, Void> {
        private CountryDao countryDao;
        public InsertAsyncTask(CountryDao countryDao) {
            this.countryDao = countryDao;
        }

        @Override
        protected Void doInBackground(List<Country>... lists) {
            countryDao.insert(lists[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {
        private CountryDao countryDao;
        public DeleteAsyncTask(CountryDao countryDao) {
            this.countryDao = countryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAll();
            return null;
        }
    }
}
