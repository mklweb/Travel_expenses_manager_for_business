package com.example.appcursoandroidv2.ui.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appcursoandroidv2.entidades.LoginModel;

public class LoginViewModel extends ViewModel {
        private MutableLiveData<LoginModel> login;
        public LoginViewModel() {
            login = new MutableLiveData<>();
            login.setValue(new LoginModel());
        }
        public LiveData<LoginModel> getLogin() {
            return login;
        }
        public void setLogin(LoginModel log) {
            login.postValue(log);
        }
    }

