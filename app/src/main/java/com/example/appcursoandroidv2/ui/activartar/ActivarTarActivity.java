package com.example.appcursoandroidv2.ui.activartar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.database.Constantes;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivarTarActivity extends AppCompatActivity {

    //Para obtener las coordenadas de longitud y latitud:
    private FusedLocationProviderClient fusedLocationProviderClient;

    TextView tvLastActiveEur, tvLastActiveInt, tvCardEurope, tvCardInternational, tvCurrentCard;
    ImageView ivTarjetaEurope, ivTarjetaInternational;
    String statusCode;

    Map<String, String> records;

    final static String LAST_ACTIVE_EUR = "lastactive_eur";
    final static String LAST_ACTIVE_INT = "lastactive_int";
    final static String CARD_EUROPE = "card_europe";
    final static String CARD_INTERNATIONAL = "card_international";
    final static String CURRENT_CARD = "current_card";
    //final static String UE = "DE AT BE BG CY HR DK SK SI ES EE FI FR EL HU IE IT LV LT LU MT NL PL PT UK CZ RO SE";

    double longitude, latitude;
    private boolean permisosLocation = false;
    private static final int PERMISOS_LOCATION = 1;
    private static String codPais = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activar_tar);
        ivTarjetaEurope = findViewById(R.id.iv_tarjeta_europe);
        ivTarjetaInternational = findViewById(R.id.iv_tarjeta_international);
        getControlViews();

        setEventListeners();

        records = new HashMap<String, String>();

        obtenerCoordenadas();

        sendHttpRequest("http://10.0.2.2:4000/infocards/user1");
    }

    private void getControlViews() {
        tvLastActiveEur = findViewById(R.id.last_active_eur);
        tvLastActiveInt = findViewById(R.id.last_active_int);
        tvCardEurope = findViewById(R.id.card_europe);
        tvCardInternational = findViewById(R.id.card_international);

    }

    private void setEventListeners() {
        ivTarjetaEurope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!permisosLocation) {
                    Toast.makeText(ActivarTarActivity.this, "Sin permisos de localización no se puede activar las tarjetas", Toast.LENGTH_SHORT).show();
                } else if (Constantes.UE.contains(codPais) && !codPais.isEmpty()) {
                    sendHttpRequest("http://10.0.2.2:4000/enablecard/user1/EUROPE");
                } else {
                    Toast.makeText(ActivarTarActivity.this, "La tarjeta no se puede activar porque NO ESTÁS DENTRO DE LA UE", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ivTarjetaInternational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!permisosLocation) {
                    Toast.makeText(ActivarTarActivity.this, "Sin permisos de localización no se puede activar las tarjetas", Toast.LENGTH_SHORT).show();
                } else if (!Constantes.UE.contains(codPais) && !codPais.isEmpty()) {
                    sendHttpRequest("http://10.0.2.2:4000/enablecard/user1/INTERNATIONAL");
                } else {
                    Toast.makeText(ActivarTarActivity.this, "La tarjeta no se puede activar porque NO ESTÁS FUERA DE LA UE", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendHttpRequest(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJSON(response);
                        loadInfo();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivarTarActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    private void parseJSON(JSONObject response) {
        if (response.optString("status").equalsIgnoreCase("ok")) {
            JSONArray jsonArray = response.optJSONArray("records");
            int n = jsonArray.length();
            try {
                for (int i = 0; i < n; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String key = jsonObject.keys().next();
                    String value = jsonObject.optString(key);
                    records.put(key, value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            String message = response.optString("error");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadInfo() {
        String fechaE = records.get(LAST_ACTIVE_EUR);
        String fechaE2 = fechaE.replace("T", "   ");
        String fechaE3 = fechaE2.substring(0, 21);

        String fechaI = records.get(LAST_ACTIVE_INT);
        String fechaI2 = fechaI.replace("T", "   ");
        String fechaI3 = fechaI2.substring(0, 21);

        tvLastActiveEur.setText(fechaE3);
        tvLastActiveInt.setText(fechaI3);
        tvCardEurope.setText(records.get(CARD_EUROPE));
        tvCardInternational.setText(records.get(CARD_INTERNATIONAL));

        String currentcard = records.get(CURRENT_CARD);
        if (currentcard.equals("EUROPE")){
            ivTarjetaEurope.setImageAlpha(255);
            ivTarjetaInternational.setImageAlpha(170);
        }
        if (currentcard.equals("INTERNATIONAL")){
            ivTarjetaEurope.setImageAlpha(170);
            ivTarjetaInternational.setImageAlpha(255);
        }
    }

    private void obtenerCoordenadas() {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //Comprueba si tenemos permisos (checkSelfPermission), si no los tiene los pide (requestPermissions)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //No tiene permisos, pedimos permisos
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISOS_LOCATION);

        } else {
            //Tiene permisos
            permisosLocation = true;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            longitude = location.getLongitude();
                            latitude = location.getLatitude();
                            Geocoder geocoder = new Geocoder(ActivarTarActivity.this);
                            try {
                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                Address address = addresses.get(0);
                                codPais = address.getCountryCode();
                                Toast.makeText(ActivarTarActivity.this, codPais, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISOS_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permisosLocation = true;
                    obtenerCoordenadas();
                } else {
                    Toast.makeText(this, "Sin los permisos de localización no podrás activar las tarjetas", Toast.LENGTH_SHORT).show();
                }
                break;

            // Aquí más casos dependiendo de los permisos
            // case OTRO_CODIGO_DE_PERMISOS...
        }
    }

}