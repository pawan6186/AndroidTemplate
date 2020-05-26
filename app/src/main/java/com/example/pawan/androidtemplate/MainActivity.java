package com.example.pawan.androidtemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pawan.androidtemplate.features.HomeFragment;
import com.example.pawan.androidtemplate.features.MainNavigation;
import com.example.pawan.androidtemplate.features.StyleSheetFragment;

public class MainActivity extends BaseActivity implements MainNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAppComponent().inject(this);
        if (savedInstanceState == null) {
            swapFragment(HomeFragment.newInstance(), true, false);
        }
    }

    @Override
    protected int getContainerId() {
        return R.id.container;
    }

    @Override
    public void navigateToStyleSheet() {
        swapFragment(StyleSheetFragment.newInstance(), true, false);
    }
}
