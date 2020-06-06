package com.example.appcursoandroidv2.ui.inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appcursoandroidv2.entidades.Usuario;


public class InicioViewModel extends ViewModel {
    private MutableLiveData<Usuario> muser;
    public InicioViewModel() {
         muser = new MutableLiveData<>();
         muser.setValue(new Usuario());
    }
    public LiveData<Usuario> getUser() {
            return muser;
        }
    public void setUser(Usuario user) {
        muser.postValue(user);
    }
}

