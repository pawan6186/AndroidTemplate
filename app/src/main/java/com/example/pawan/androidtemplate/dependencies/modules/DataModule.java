package com.example.pawan.androidtemplate.dependencies.modules;

import com.example.pawan.androidtemplate.network.services.FactsService;
import com.example.pawan.androidtemplate.repository.FactsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    @Singleton
    @Provides
    FactsRepository providesFactRepository(FactsService factsService){
        return new FactsRepository(factsService);
    }
}
