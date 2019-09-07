package co.com.ceiba.clean.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import co.com.ceiba.clean.R;

public class RecyclerViewHolderParqueado extends RecyclerView.ViewHolder {

    TextView textPlaca;

    TextView textTipo;

    TextView textCilindraje;

    TextView textFechaIngreso;

    ImageView imagenTipoVehiculo;

    Button botonSalida;

    RecyclerViewHolderParqueado(@NonNull View itemView) {
        super(itemView);

        textPlaca = itemView.findViewById(R.id.parqueadosTextPlaca);
        textTipo = itemView.findViewById(R.id.parqueadosTextTipo);
        textCilindraje = itemView.findViewById(R.id.parqueadosTextCilindraje);
        textFechaIngreso = itemView.findViewById(R.id.parqueadosTextFechaIngreso);
        imagenTipoVehiculo = itemView.findViewById(R.id.parqueadosImagen);
        botonSalida = itemView.findViewById(R.id.parqueadosBotonSalir);

    }
}
