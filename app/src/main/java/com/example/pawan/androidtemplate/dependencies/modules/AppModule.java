package com.example.pawan.androidtemplate.dependencies.modules;

import android.content.Context;

import com.example.pawan.androidtemplate.TemplateApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final TemplateApp app;

    public AppModule(TemplateApp templateApp) {
        this.app = templateApp;
    }

    @Singleton
    @Provides
    public Context provideApplicationContext() {
        return app;
    }
}
