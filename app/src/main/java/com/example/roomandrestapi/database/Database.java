package com.example.roomandrestapi.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomandrestapi.model.Country;

@androidx.room.Database(entities = {Country.class}, version = 3)
public abstract class Database extends RoomDatabase {
    private static final String DATABASE_NAME = "COUNTRIES";
    public abstract CountryDao countryDao();
    public static volatile Database INSTANCE = null;

    public static Database getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE);
        }
    };

    static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {
        private CountryDao countryDao;
        public PopulateDBAsync(Database database) {
            countryDao = database.countryDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAll();
            return null;
        }
    }
}
