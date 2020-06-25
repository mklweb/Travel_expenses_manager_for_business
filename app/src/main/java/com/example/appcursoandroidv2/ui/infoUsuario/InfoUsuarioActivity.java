package com.example.appcursoandroidv2.ui.infoUsuario;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.UsuarioDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.vm.UsuarioViewModel;
import com.example.appcursoandroidv2.utils.DateParser;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Date;

public class InfoUsuarioActivity extends AppCompatActivity {
    Usuario user;
    TextView tvNombreApellido;
    TextView tvUserName;
    TextView tvDni;
    TextView tvLastConection;
    NetworkImageView avatar;
    String strFecha;
    MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);
        tvNombreApellido= findViewById(R.id.tv_nombre_apellido);
        tvUserName= findViewById(R.id.tv_username);
        tvDni= findViewById(R.id.tv_dni);
        tvLastConection= findViewById(R.id.tv_last_conection);
        toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle(R.string.info_usuario);
        avatar = (NetworkImageView)findViewById(R.id.iv_profile_avatar);
        UsuarioViewModel model = new ViewModelProvider(this).get(UsuarioViewModel.class);
        SQLiteDatabase db = Conexion.getInstance(InfoUsuarioActivity.this);
        UsuarioDAOImpl userDao = new UsuarioDAOImpl(db);
        try {
            user = userDao.findById(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setUser(user);
        RequestQueue mRequestQueue = Volley.newRequestQueue(InfoUsuarioActivity.this);
        final ImageLoader mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
        long longFecha = user.getLastConection();
        Date dateFecha = new Date(longFecha);
        DateParser dp = new DateParser(dateFecha);
        final String strFecha = dp.getDateTimeInTextFormat();

        model.getUser().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario user) {
                tvNombreApellido.setText(user.getNameSurname());
                tvUserName.setText(user.getUserName());
                tvDni.setText(user.getDni());
                tvLastConection.setText(strFecha);
                avatar.setImageUrl(user.getSrc(),mImageLoader);//URL en BBDD
            }
        });
    }
}
