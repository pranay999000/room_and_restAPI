package com.example.roomandrestapi.api;

import com.example.roomandrestapi.model.Country;
import com.example.roomandrestapi.model.GetCountry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryAPI {
    @GET("asia/")
    Call<List<GetCountry>> getCountries();
}
