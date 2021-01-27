package com.example.weatherapp.frgament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.FavouriteCitiesFragmentBinding;
import com.example.weatherapp.onBackPressedListener;
import com.example.weatherapp.viewModel.FavouriteCitiesViewModel;

public class FavouriteCitiesFragment extends Fragment implements onBackPressedListener {

    private FavouriteCitiesViewModel mViewModel;

    public static FavouriteCitiesFragment newInstance() {
        return new FavouriteCitiesFragment();
    }

    private String TAG = getClass().getSimpleName();

    private FavouriteCitiesFragmentBinding binding;

    public FavouriteCitiesFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.favourite_cities_fragment, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recylerView.setLayoutManager(linearLayoutManager);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FavouriteCitiesViewModel.class);
        mViewModel.getWeatherLocally();
        mViewModel.getCurrentWeatherFromDB.observe(getActivity(), currentWeathers -> {
            binding.tvEmpty.setVisibility(currentWeathers.isEmpty() ? View.VISIBLE : View.GONE);
            binding.recylerView.setVisibility(currentWeathers.isEmpty() ? View.GONE : View.VISIBLE);

            FavouriteCitiesAdapter myRecyclerViewAdapter = new FavouriteCitiesAdapter(currentWeathers, getActivity());
            binding.recylerView.setAdapter(myRecyclerViewAdapter);

        });

        binding.backButton.setOnClickListener(v -> {
            closeFragment();
        });
    }

    @Override
    public void onBackPressed() {
        closeFragment();
    }

    private void closeFragment() {
        NavHostFragment.findNavController(FavouriteCitiesFragment.this).navigateUp();
    }
}