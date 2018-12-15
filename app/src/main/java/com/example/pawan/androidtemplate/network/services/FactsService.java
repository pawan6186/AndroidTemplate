package com.example.pawan.androidtemplate.network.services;

import com.example.pawan.androidtemplate.models.Fact;
import com.example.pawan.androidtemplate.models.FactResponse;
import com.example.pawan.androidtemplate.network.ApiUrls;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FactsService {
    @GET(ApiUrls.FACTS_URL)
    Call<FactResponse> getFacts();
}
