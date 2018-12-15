package com.example.pawan.androidtemplate.dependencies.modules;

import com.example.pawan.androidtemplate.network.ApiUrls;
import com.example.pawan.androidtemplate.network.services.FactsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Singleton
    @Provides
    Retrofit providesRetrofitClient(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiUrls.getBaseUrl())
                .build();
    }

    @Singleton
    @Provides
    FactsService providesFactsService(Retrofit retrofit){
        return retrofit.create(FactsService.class);
    }
}
