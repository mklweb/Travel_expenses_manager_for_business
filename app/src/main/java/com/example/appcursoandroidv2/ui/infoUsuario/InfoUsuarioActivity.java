package com.example.appcursoandroidv2.ui.infoUsuario;

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
import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.inicio.InicioViewModel;

public class InfoUsuarioActivity extends AppCompatActivity {
    Usuario user;
    TextView tvNombreApellido;
    TextView tvUserName;
    TextView tvDni;
    TextView tvLastConection;
    NetworkImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);
        tvNombreApellido= findViewById(R.id.tv_nombre_apellido);
        tvUserName= findViewById(R.id.tv_username);
        tvDni= findViewById(R.id.tv_dni);
        tvLastConection= findViewById(R.id.tv_last_conection);
        avatar = (NetworkImageView)findViewById(R.id.iv_profile_avatar);
        InicioViewModel model = new ViewModelProvider(this).get(InicioViewModel.class);
        Bundle bundle= getIntent().getExtras();
        if(bundle != null){
             user = (Usuario) bundle.getSerializable("usuario");
            model.setUser(user);
        }

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


        model.getUser().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario user) {
                tvNombreApellido.setText(user.getNameSurname());
                tvUserName.setText(user.getUserName());
                tvDni.setText(user.getDni());
                tvLastConection.setText(user.getLastConection());
                avatar.setImageUrl("https://mikelweb.ml/img/foto_freddieXL.png",mImageLoader);//URL en BBDD
            }
        });
    }
}
