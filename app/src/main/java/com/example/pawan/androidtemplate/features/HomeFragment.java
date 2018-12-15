package com.example.pawan.androidtemplate.features;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pawan.androidtemplate.BaseFragment;
import com.example.pawan.androidtemplate.R;
import com.example.pawan.androidtemplate.databinding.FragmentHomeBinding;
import com.example.pawan.androidtemplate.models.Fact;

import java.util.List;

import timber.log.Timber;

public class HomeFragment extends BaseFragment<HomeViewModel> {
    private FragmentHomeBinding mFragmentHomeBinding;
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
        initViewModel(HomeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        mViewModel.getFacts().observe(getViewLifecycleOwner(), new Observer<List<Fact>>() {
            @Override
            public void onChanged(@Nullable List<Fact> facts) {
                Timber.d(facts.toString());
            }
        });
        return mFragmentHomeBinding.getRoot();
    }
}

