package com.example.pawan.androidtemplate.features;

import android.arch.lifecycle.Observer;
import android.content.Context;
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

import java.lang.ref.WeakReference;
import java.util.List;

import timber.log.Timber;

public class HomeFragment extends BaseFragment<HomeViewModel> {
    private FragmentHomeBinding mFragmentHomeBinding;
    private WeakReference<MainNavigation> mMainNavigation;

    public static HomeFragment newInstance() {
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
        mFragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mViewModel.getFacts().observe(getViewLifecycleOwner(), new Observer<List<Fact>>() {
            @Override
            public void onChanged(@Nullable List<Fact> facts) {
                Timber.d(facts.toString());
            }
        });
        initView();
        return mFragmentHomeBinding.getRoot();
    }

    private void initView() {
        mFragmentHomeBinding.goToStylesheet.setOnClickListener(view -> {
            if (mMainNavigation.get() != null) {
                mMainNavigation.get().navigateToStyleSheet();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainNavigation) {
            mMainNavigation = new WeakReference<>((MainNavigation) context);
        } else {
            throw new RuntimeException("Activity is not of type - IMapActivityCallback");
        }
    }
}

