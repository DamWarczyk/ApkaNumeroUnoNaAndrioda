package com.example.apkanumerounonaandrioda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MeanViewModel extends ViewModel {
    private MutableLiveData<Double> _mean = new MutableLiveData<>();

    public Double getMean() {
        return _mean.getValue();
    }

    public void setMean(Double mean) {
        this._mean.setValue(mean);
    }
}
