package com.example.appcursoandroidv2.ui.listagastos;

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
import com.example.appcursoandroidv2.dao.GastoDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Gasto;
import com.example.appcursoandroidv2.ui.ediciongasto.EdicionGastoActivity;
import com.example.appcursoandroidv2.utils.DateParser;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Date;
import java.util.List;

public class AdapterGastos
        extends RecyclerView.Adapter<AdapterGastos.ViewHolderGastos>
        implements View.OnClickListener {

    private List<Gasto> listGastos;
    private View.OnClickListener listener;

    public AdapterGastos(List<Gasto> listGastos) {
        this.listGastos = listGastos;
    }

    @NonNull
    @Override
    public ViewHolderGastos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gasto, null, false);
        view.setOnClickListener(this);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolderGastos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGastos holder, int position) {
        //holder.cargaDatos(listGastos.get(position));
        Gasto gasto = listGastos.get(position);
        holder.tvIdGasto.setText(String.valueOf(gasto.getId()));
        //Convertimos milisegundos en Date y Date en String dd/mm/aaaa
        long longFecha = listGastos.get(position).getFecha();
        Date dateFecha = new Date(longFecha);
        DateParser dp = new DateParser(dateFecha);
        String strFecha = dp.getDateInTextFormat();
        holder.tvDateGasto.setText(strFecha);
        holder.tvProDepGasto.setText(gasto.getDep() + gasto.getPro());
        holder.tvTotalGasto.setText(String.valueOf(gasto.getTotal()));

        //=== ASIGNAMOS LOS LISTENER A LOS BOTONES DEL ViewHolder ===//
        holder.asignarListeners();
    }

    @Override
    public int getItemCount() {
        return listGastos.size();
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

    //=== SETTER PARA PASARLE LA LISTA DE GASTOS DESDE EL VIEWMODEL ===//

    public class ViewHolderGastos extends RecyclerView.ViewHolder implements View.OnClickListener {
        //== NECESITAMOS EL CONTEXTO PARA HACER INTENT ===//
        Context context;

        TextView tvIdGasto;
        TextView tvDateGasto;
        TextView tvProDepGasto;
        TextView tvTotalGasto;

        ImageButton btnEditarGasto;
        ImageButton btnBorrarGasto;

        public ViewHolderGastos(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvIdGasto = itemView.findViewById(R.id.tv_recycler_id_gasto);
            tvDateGasto = itemView.findViewById(R.id.tv_recycler_date_gasto);
            tvProDepGasto = itemView.findViewById(R.id.tv_project_deparment_gasto);
            tvTotalGasto = itemView.findViewById(R.id.tv_total_gasto);
            btnBorrarGasto = itemView.findViewById(R.id.btn_borrar_gasto);
            btnEditarGasto = itemView.findViewById(R.id.btn_editar_gasto);
        }

        /**
         * CREAMOS ESTE MÉTODO NOSOTROS. SE LLAMA DESDE EL MÉTODO onBinViewHolder
         * del Adapter porque es el que tiene el Item PERO SE ASIGNAN LOS LISTENERS
         * AQUÍ PORQUE EL ViewHolder es quien tiene la vista que incluye los botones.
         * Y también necesitamos el contexto de esta vista para poder hacer Intent.
          */
        public void asignarListeners() {
            btnEditarGasto.setOnClickListener(this);
            btnBorrarGasto.setOnClickListener(this);
        }

        //=== EL MÉTODO onClick para los botones del Item ===//
        @Override
        public void onClick(View v) {

            final String idGasto = tvIdGasto.getText().toString();
            switch (v.getId()) {
                case R.id.btn_editar_gasto:
                    Toast.makeText(context,"Vas editar el id: " + idGasto, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, EdicionGastoActivity.class);
                    Bundle bundle = new Bundle();
                    Gasto gasto = listGastos.get(getAdapterPosition());
                    bundle.putSerializable("gasto", gasto);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    break;
                case R.id.btn_borrar_gasto:
                    MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(context);
                    dialogBuilder.setTitle("Diálogo de confirmación")
                            .setMessage("¿Seguro que quieres borrar el gasto?")
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
                                    GastoDAOImpl gastoDAO = new GastoDAOImpl(db);
                                    try {
                                        int n = gastoDAO.remove(idGasto);
                                        Toast.makeText(context, n + " registro/s eliminado/s", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Intent intent = new Intent(context, ListaGastosActivity.class);
                                    context.startActivity(intent);
                                }
                            }).show();
                    break;
            }

        }

    }
}
