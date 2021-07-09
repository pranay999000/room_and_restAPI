package com.example.roomandrestapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomandrestapi.R;
import com.example.roomandrestapi.model.Country;
import com.example.roomandrestapi.util.Svg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private Context context;
    private List<Country> list;

    public CountryAdapter(Context context, List<Country> list) {
        this.context = context;
        this.list = list;
    }

    public void fillData(List<Country> countryList) {
        this.list = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = list.get(position);
        holder.country.setText(country.getName());
        holder.capital.setText(country.getCapital());
        holder.region.setText(country.getRegion());
        holder.subregion.setText(country.getSubregion());
        holder.population.setText(String.valueOf(country.getPopulation()));
        Svg.fetchSvg(context, country.getFlag(), holder.flag);

        List<String> borders = Arrays.asList(country.getBorders().split(";;;"));
        List<String> languages = Arrays.asList(country.getLanguages().split(";;;"));

        TextAdapter textAdapter = new TextAdapter(borders);
        TextAdapter textAdapter1 = new TextAdapter(languages);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager1 =
                new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);

        holder.border.setLayoutManager(gridLayoutManager);
        holder.languages.setLayoutManager(gridLayoutManager1);

        holder.border.setHasFixedSize(true);
        holder.languages.setHasFixedSize(true);

        holder.border.setAdapter(textAdapter);
        holder.languages.setAdapter(textAdapter1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView flag;
        private final TextView country;
        private final TextView capital;
        private final TextView region;
        private final TextView subregion;
        private final TextView population;

        private final RecyclerView border;
        private final RecyclerView languages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.flag);
            country = itemView.findViewById(R.id.country);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            subregion = itemView.findViewById(R.id.subregion);
            population = itemView.findViewById(R.id.population);

            border = itemView.findViewById(R.id.borders);
            languages = itemView.findViewById(R.id.languages);
        }
    }
}
