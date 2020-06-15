package com.example.appcursoandroidv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.appcursoandroidv2.databinding.ActivityMainBinding;
import com.example.appcursoandroidv2.ui.login.LoginCallbacks;
import com.example.appcursoandroidv2.ui.login.LoginViewModel;
import com.example.appcursoandroidv2.ui.login.LoginViewModelFactory;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements LoginCallbacks {
    TextView tv_loginError;
    View view;
    Toolbar toolbar2;
    String versionTitle = "Versión";
    String version = "1.0.0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new ViewModelProvider(this, new LoginViewModelFactory(this)).get(LoginViewModel.class));
        tv_loginError = findViewById(R.id.tv_login_error);
        toolbar2 = findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        TextInputLayout itlNombre = findViewById(R.id.itl_nombre);
//        TextInputLayout itlPass =findViewById(R.id.itl_pass);
//        itlNombre.setError("Falta nombre");
//        itlPass.setError("Falta Password");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.mn_about);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                view = item.getActionView();
                switch (id) {
                    case R.id.mn_about:
                        showDialog(view);
                        break;
                }
                return false;
            }
        });
        return true;
    }
    public void showError(String message) {
        tv_loginError.setText(message);
    }
    public void showDialog(View view) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
        builder.setTitle(versionTitle);
        builder.setMessage(version);
        builder.show();
    }

}

