package com.example.appcursoandroidv2.ui.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appcursoandroidv2.entidades.Gasto;

public class GastoModel extends ViewModel {

    private MutableLiveData<Gasto> gasto;

    public MutableLiveData<Gasto> getGasto() {
        if(gasto == null) {
            gasto = new MutableLiveData<Gasto>();
        }
        return gasto;
    }
}
