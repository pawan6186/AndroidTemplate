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
import android.widget.Toast;

import com.example.pawan.androidtemplate.BaseFragment;
import com.example.pawan.androidtemplate.R;
import com.example.pawan.androidtemplate.databinding.FragmentHomeBinding;
import com.example.pawan.androidtemplate.models.Fact;
import com.example.pawan.androidtemplate.utils.Utilities;

import java.lang.ref.WeakReference;
import java.util.List;

import timber.log.Timber;

public class HomeFragment extends BaseFragment<HomeViewModel> {
    private FragmentHomeBinding mFragmentHomeBinding;
    private FactsAdapter mFactAdapter;

    public static HomeFragment newInstance() {
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
        mFragmentHomeBinding.goToStylesheet.setOnClickListener(view -> {
            if (mMainNavigation.get() != null) {
                mMainNavigation.get().navigateToStyleSheet();
            }
        });
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

