package com.example.pawan.androidtemplate.features;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.pawan.androidtemplate.models.Fact;
import com.example.pawan.androidtemplate.repository.FactsRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class HomeViewModel extends ViewModel {

    @Inject
    FactsRepository mFactsRepository;

    @Inject public HomeViewModel(){}

    public LiveData<List<Fact>> getFacts(){
        MutableLiveData <List<Fact>> facts = new MutableLiveData<>();
        new Thread(() -> {
            try {
                facts.postValue(mFactsRepository.getFacts());
            } catch (IOException e) {
                Timber.e(e);
            }
        }).start();
        return facts;
    }
}
