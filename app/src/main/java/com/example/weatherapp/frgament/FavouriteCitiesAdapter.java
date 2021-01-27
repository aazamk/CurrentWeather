package com.example.weatherapp.frgament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.BR;
import com.example.weatherapp.R;
import com.example.weatherapp.data.source.local.entity.CurrentWeather;
import com.example.weatherapp.databinding.FavouriteCitiesRowsBinding;

import java.util.List;

public class FavouriteCitiesAdapter  extends RecyclerView.Adapter<FavouriteCitiesAdapter.ViewHolder> {

    private List<CurrentWeather> dataModelList;
    private Context context;

    public FavouriteCitiesAdapter(List<CurrentWeather> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    @Override
    public FavouriteCitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        FavouriteCitiesRowsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.favourite_cities_rows, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CurrentWeather dataModel = dataModelList.get(position);
        holder.bind(dataModel);
//        holder.itemRowBinding.setItemClickListener(this);
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public FavouriteCitiesRowsBinding itemRowBinding;

        public ViewHolder(FavouriteCitiesRowsBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.weatherCondition, obj);
            itemRowBinding.executePendingBindings();
        }
    }

    public void cardClicked(CurrentWeather f) {
        Toast.makeText(context, "You clicked " + f.getCityName(),
                Toast.LENGTH_LONG).show();
    }
}
