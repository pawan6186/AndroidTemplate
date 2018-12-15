package com.example.pawan.androidtemplate.dependencies;

import com.example.pawan.androidtemplate.MainActivity;
import com.example.pawan.androidtemplate.dependencies.modules.AppModule;
import com.example.pawan.androidtemplate.dependencies.modules.DataModule;
import com.example.pawan.androidtemplate.dependencies.modules.NetworkModule;
import com.example.pawan.androidtemplate.dependencies.modules.ViewModelModule;
import com.example.pawan.androidtemplate.features.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ViewModelModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent{
    void inject(MainActivity mainActivity);
    void inject(HomeFragment fragment);
}
