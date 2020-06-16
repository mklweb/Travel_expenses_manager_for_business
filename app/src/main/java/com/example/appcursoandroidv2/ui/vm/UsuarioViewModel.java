package com.example.appcursoandroidv2.ui.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appcursoandroidv2.entidades.Usuario;


public class UsuarioViewModel extends ViewModel {
    private MutableLiveData<Usuario> muser;
    public UsuarioViewModel() {
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

