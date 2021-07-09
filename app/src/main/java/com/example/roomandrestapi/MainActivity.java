package com.example.roomandrestapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.roomandrestapi.adapter.CountryAdapter;
import com.example.roomandrestapi.api.CountryAPI;
import com.example.roomandrestapi.database.Database;
import com.example.roomandrestapi.model.Country;
import com.example.roomandrestapi.model.GetCountry;
import com.example.roomandrestapi.model.Language;
import com.example.roomandrestapi.repository.Repository;
import com.example.roomandrestapi.viewModel.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ViewModel viewModel;
    private List<Country> countryList;
    private Repository repository;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_circular);

        CountryAdapter countryAdapter = new CountryAdapter(this, countryList);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        repository = new Repository(getApplication());
        countryList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        request();
        viewModel.getAllCountries().observe(this, countries -> {
                    recyclerView.setAdapter(countryAdapter);
                    countryAdapter.fillData(countries);
                }
        );
    }

    private void request() {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/region/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountryAPI countryAPI = retrofit.create(CountryAPI.class);
        Call<List<GetCountry>> getCountries = countryAPI.getCountries();
        getCountries.enqueue(new Callback<List<GetCountry>>() {
            @Override
            public void onResponse(@NonNull Call<List<GetCountry>> call, @NonNull Response<List<GetCountry>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
//                    repository.insert(response.body());
                    assert response.body() != null;
                    List<Country> countryList1 = new ArrayList<>();
                    for (GetCountry getCountry: response.body()) {

                        StringBuilder stringBuilder = new StringBuilder();
                        for (String border: getCountry.getBorders()) {
                            stringBuilder.append(border).append(";;;");
                        }

                        StringBuilder stringBuilder1 = new StringBuilder();
                        for (Language language: getCountry.getLanguages()) {
                            stringBuilder1.append(language.getName()).append(";;;");
                        }

                        countryList1.add(new Country(
                                getCountry.getName(),
                                getCountry.getCapital(),
                                getCountry.getFlag(),
                                getCountry.getRegion(),
                                getCountry.getSubregion(),
                                getCountry.getPopulation(),
                                stringBuilder.toString(),
                                stringBuilder1.toString()
                        ));

                    }
                    repository.insert(countryList1);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GetCountry>> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            repository.delete();
            Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.refresh) {
            request();
        }

        return super.onOptionsItemSelected(item);
    }
}