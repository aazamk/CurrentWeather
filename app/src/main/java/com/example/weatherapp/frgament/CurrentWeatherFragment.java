package com.example.weatherapp.frgament;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.Util.Connection;
import com.example.weatherapp.data.source.local.entity.CurrentWeather;
import com.example.weatherapp.databinding.CurrentWeatherFrgamentLayoutBinding;
import com.example.weatherapp.viewModel.CurrentWeaterViewModel;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public class CurrentWeatherFragment extends Fragment {

    private String TAG = getClass().getSimpleName();

    private CurrentWeaterViewModel mViewModel;
    private CurrentWeatherFrgamentLayoutBinding binding;
    private CurrentWeather currentWeather;

    public CurrentWeatherFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.current_weather_frgament_layout, container, false);
        mViewModel = ViewModelProviders.of(getActivity()).get(CurrentWeaterViewModel.class);

        showEmptyLayout();
        setupView();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        mViewModel.getWeatherLocally();
        mViewModel.getCurrentWeatherFromDB.observe(getViewLifecycleOwner(), new Observer<List<CurrentWeather>>() {
            @Override
            public void onChanged(List<CurrentWeather> currentWeathers) {
                if (!currentWeathers.isEmpty()) {

                    currentWeather = currentWeathers.get(0);
                    if (Connection.isTimePass(currentWeather.getStoreTimestamp())) {
                        Log.d(TAG, "onChanged: requestign new weather");
                        requestWeather(currentWeather.getCityName(), false);
                    } else {
                        setViews(currentWeathers.get(0));
                    }
                }
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Set search view
     */
    private void setupView() {
        binding.toolbarLayout.searchView.setVoiceSearch(false);
        binding.toolbarLayout.searchView.setHint(getString(R.string.search_label));
        binding.toolbarLayout.searchView.setCursorDrawable(R.drawable.cursor_color);
        binding.toolbarLayout.searchView.setEllipsize(true);
        binding.toolbarLayout.searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                binding.toolbarLayout.rlAction.setVisibility(View.GONE);
            }

            @Override
            public void onSearchViewClosed() {
                binding.toolbarLayout.rlAction.setVisibility(View.VISIBLE);
            }
        });
        binding.toolbarLayout.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                requestWeather(query, true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        binding.toolbarLayout.iconSearch.setOnClickListener(v -> binding.toolbarLayout.searchView.showSearch());

        binding.toolbarLayout.listIcon.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.action_currentWeatherFragment_to_favouriteCitiesFragment);
        });
        binding.favIcon.setOnClickListener(v -> {
            mViewModel.setAsFavourite(currentWeather.getId());
        });
    }

    private void showEmptyLayout() {
        Glide.with(getActivity()).load(R.drawable.no_city).into(binding.contentEmptyLayout.noCityImageView);
        binding.contentEmptyLayout.emptyLayout.setVisibility(View.VISIBLE);
        binding.container.setVisibility(View.GONE);
    }

    private void hideEmptyLayout() {
        binding.contentEmptyLayout.emptyLayout.setVisibility(View.GONE);
        binding.container.setVisibility(View.VISIBLE);
    }

    private void requestWeather(String query, boolean b) {
        if (Connection.isNetworkAvailable(getContext())) {
            mViewModel.getCurrentWeather(query);
            observableChanges();

        } else {
            Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Observer DB changes and Save into Data
     */
    private void observableChanges() {
        mViewModel.currentWeatherResponseMutableLiveData.observe(getViewLifecycleOwner(), currentWeatherResponse -> {
            Log.d(TAG, "observableChanges: " + currentWeatherResponse);
            if (currentWeatherResponse != null) {
                mViewModel.preparCurrentWeatherData(currentWeatherResponse);
            }else {
                Toast.makeText(getActivity(), "Select City not found", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Infate views
     * @param currentWeather
     */
    private void setViews(CurrentWeather currentWeather) {
        binding.setWeatherCondition(currentWeather);

        hideEmptyLayout();
    }


}
