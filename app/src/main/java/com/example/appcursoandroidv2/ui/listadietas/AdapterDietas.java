package com.example.appcursoandroidv2.ui.listadietas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.DietaDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Dieta;
import com.example.appcursoandroidv2.ui.ediciondieta.EdicionDietaActivity;
import com.example.appcursoandroidv2.ui.listadietas.ListaDietasActivity;
import com.example.appcursoandroidv2.utils.DateParser;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Date;
import java.util.List;

public class AdapterDietas
        extends RecyclerView.Adapter<AdapterDietas.ViewHolderDietas>
        implements View.OnClickListener {

    private List<Dieta> listDietas;
    private View.OnClickListener listener;

    public AdapterDietas(List<Dieta> listDietas) {
        this.listDietas = listDietas;
    }

    @NonNull
    @Override
    public ViewHolderDietas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dieta, null, false);
        view.setOnClickListener(this);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolderDietas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDietas holder, int position) {
        //holder.cargaDatos(listDietas.get(position));
        Dieta dieta = listDietas.get(position);
        holder.tvIdDieta.setText(String.valueOf(dieta.getId()));
        //Convertimos milisegundos en Date y Date en String dd/mm/aaaa
        long longStartFecha = listDietas.get(position).getFechaIni();
        long longEndFecha = listDietas.get(position).getFechaFin();
        Date dateStartFecha = new Date(longStartFecha);
        Date dateEndFecha = new Date(longEndFecha);
        DateParser dpStart = new DateParser(dateStartFecha);
        //String strFecha = dpStart.getDateInTextFormat();
        DateParser dpEnd = new DateParser(dateEndFecha);
        String strStartFecha = dpStart.getDateInTextFormat();
        String strEndFecha = dpEnd.getDateInTextFormat();
        holder.tvStartDateDieta.setText(strStartFecha);
        holder.tvEndDateDieta.setText(strEndFecha);
        holder.tvProDepDieta.setText(dieta.getDepartment() + dieta.getProyect());
        holder.tvTotalDieta.setText(String.valueOf(dieta.getTotal()));

        //=== ASIGNAMOS LOS LISTENER A LOS BOTONES DEL ViewHolder ===//
        holder.asignarListeners();
    }

    @Override
    public int getItemCount() {
        return listDietas.size();
    }

    //===INICIO DE MÉTODOS QUE IMPLEMENTAN LA INTERFAZ View.OnClickListener===//
    //===Implementa un listener de evento on click sobre el item del recycler
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onClick(v);
        }

    }
    //=== FIN MÉTODOS QUE IMPLEMENTAN LA INTERFAZ View.OnClickListener ===//

    //=== SETTER PARA PASARLE LA LISTA DE DIETAS DESDE EL VIEWMODEL ===//

    public class ViewHolderDietas extends RecyclerView.ViewHolder implements View.OnClickListener {
        //== NECESITAMOS EL CONTEXTO PARA HACER INTENT ===//
        Context context;

        TextView tvIdDieta;
        TextView tvStartDateDieta;
        TextView tvEndDateDieta;
        TextView tvProDepDieta;
        TextView tvTotalDieta;

        ImageButton btnEditarDieta;
        ImageButton btnBorrarDieta;

        public ViewHolderDietas(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvIdDieta = itemView.findViewById(R.id.tv_recycler_id_dieta);
            tvStartDateDieta = itemView.findViewById(R.id.tv_recycler_start_date_dieta);
            tvEndDateDieta = itemView.findViewById(R.id.tv_recycler_end_date_dieta);
            tvProDepDieta = itemView.findViewById(R.id.tv_project_deparment_dieta);
            tvTotalDieta = itemView.findViewById(R.id.tv_total_dieta);
            btnBorrarDieta = itemView.findViewById(R.id.btnBorrar);
            btnEditarDieta = itemView.findViewById(R.id.btnEditar);
        }

        /**
         * CREAMOS ESTE MÉTODO NOSOTROS. SE LLAMA DESDE EL MÉTODO onBinViewHolder
         * del Adapter porque es el que tiene el Item PERO SE ASIGNAN LOS LISTENERS
         * AQUÍ PORQUE EL ViewHolder es quien tiene la vista que incluye los botones.
         * Y también necesitamos el contexto de esta vista para poder hacer Intent.
          */
        public void asignarListeners() {
            btnEditarDieta.setOnClickListener(this);
            btnBorrarDieta.setOnClickListener(this);
        }

        //=== EL MÉTODO onClick para los botones del Item ===//
        @Override
        public void onClick(View v) {

            final String idDieta = tvIdDieta.getText().toString();
            switch (v.getId()) {
                case R.id.btnEditar:
                    Intent intent = new Intent(context, EdicionDietaActivity.class);
                    Bundle bundle = new Bundle();
                    Dieta dieta = listDietas.get(getAdapterPosition());
                    bundle.putSerializable("dieta", dieta);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    break;
                case R.id.btnBorrar:
                    MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(context);
                    dialogBuilder.setTitle("Diálogo de confirmación")
                            .setMessage("¿Seguro que quieres borrar la dieta?")
                            .setNegativeButton("no", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setPositiveButton("si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SQLiteDatabase db = Conexion.getInstance(context);
                                    DietaDAOImpl dietaDAO = new DietaDAOImpl(db);
                                    try {
                                        dietaDAO.remove(idDieta);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Intent intent = new Intent(context, ListaDietasActivity.class);
                                    context.startActivity(intent);
                                }
                            }).show();
                    break;
            }

        }

    }
}
