package com.example.pawan.androidtemplate.features;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pawan.androidtemplate.BaseFragment;
import com.example.pawan.androidtemplate.R;
import com.example.pawan.androidtemplate.databinding.FragmentHomeBinding;
import com.example.pawan.androidtemplate.models.Fact;
import com.example.pawan.androidtemplate.utils.Utilities;

import java.util.List;

import timber.log.Timber;

public class HomeFragment extends BaseFragment<HomeViewModel> {
    private FragmentHomeBinding mFragmentHomeBinding;
    private FactsAdapter mFactAdapter;

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
        initViews();
        loadData();
        mViewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (!TextUtils.isEmpty(error)) {
                Activity activity = getActivity();
                Utilities.showError(activity, error);
            }
        });

        return mFragmentHomeBinding.getRoot();
    }

    private void loadData() {
        if (Utilities.isOnline(getContext())) {
            mViewModel.getFacts().observe(getViewLifecycleOwner(), facts -> {
                loadDataToView(facts);
                mFragmentHomeBinding.swipeContainer.setRefreshing(false);
            });
        } else {
            Utilities.showError(getActivity(), getString(R.string.not_connected_to_internet));
        }
    }

    private void initViews() {
        mFactAdapter = new FactsAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mFragmentHomeBinding.recyclerView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentHomeBinding.recyclerView.setLayoutManager(layoutManager);
        mFragmentHomeBinding.recyclerView.setHasFixedSize(true);
        mFragmentHomeBinding.recyclerView.setAdapter(mFactAdapter);
        mFragmentHomeBinding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void showData(boolean show) {
        int visibility = show ? View.GONE : View.VISIBLE;
        mFragmentHomeBinding.emptyView.setVisibility(visibility);
    }

    private void loadDataToView(List<Fact> facts) {
        if (facts != null) {
            if (facts.size() > 0) {
                mFactAdapter.updateData(facts);
                showData(true);
            } else {
                showData(false);
            }
        }
    }
}

