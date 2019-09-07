package co.com.ceiba.clean.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import co.com.ceiba.clean.R;

public class RecyclerViewHolderHistoriales extends RecyclerView.ViewHolder {

    TextView textPlaca;

    TextView textTipo;

    TextView textCilindraje;

    TextView textFechaIngreso;

    TextView textFechaSalida;

    TextView textCosto;

    ImageView imagenTipoVehiculo;
    
    RecyclerViewHolderHistoriales(@NonNull View itemView) {
        super(itemView);

        textPlaca = itemView.findViewById(R.id.historialesTextPlaca);
        textTipo = itemView.findViewById(R.id.historialesTextTipo);
        textCilindraje = itemView.findViewById(R.id.historialesTextCilindraje);
        textFechaIngreso = itemView.findViewById(R.id.historialesTextFechaIngreso);
        textFechaSalida = itemView.findViewById(R.id.historialesTextFechaSalida);
        textCosto = itemView.findViewById(R.id.historialesTextTotal);
        imagenTipoVehiculo = itemView.findViewById(R.id.historialesImagen);

    }
}
