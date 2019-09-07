package co.com.ceiba.clean.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.List;

import co.com.ceiba.clean.R;
import co.com.ceiba.clean.activity.MainActivity;
import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.modelo.Historial;


public class RecyclerAdapterParqueado extends RecyclerView.Adapter<RecyclerViewHolderParqueado> {

    private static final String CARRO = "CARRO";
    private static final String MOTO = "MOTO";

    private MainActivity activity;
    private LayoutInflater inflater;

    private List<Historial> parqueados;

    public RecyclerAdapterParqueado(MainActivity activity, List<Historial> parqueados) {
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        this.parqueados = parqueados;
    }


    @NonNull
    @Override
    public RecyclerViewHolderParqueado onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolderParqueado(inflater.inflate(R.layout.item_parqueo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderParqueado holder, int position) {
        holder.imagenTipoVehiculo.setImageDrawable(
                parqueados.get(position).getVehiculo().getTipo() == TipoVehiculo.CARRO ?
                        activity.getDrawable(R.drawable.ic_coche) :
                        activity.getDrawable(R.drawable.ic_motocicleta));
        holder.textPlaca.setText(parqueados.get(position).getVehiculo().getPlaca());
        holder.textTipo.setText(parqueados.get(position).getVehiculo().getTipo() == TipoVehiculo.CARRO ? CARRO : MOTO);
        holder.textCilindraje.setText(String.valueOf(parqueados.get(position).getVehiculo().getCilindraje()));
        holder.textFechaIngreso.setText(parqueados.get(position).getFechaIngreso().toString());

        holder.botonSalida.setOnClickListener(view -> salidaVehiculo(position));
    }

    @Override
    public int getItemCount() {
        return parqueados.size();
    }

    public void setListaParqueos(List<Historial> parqueados){
        this.parqueados = parqueados;
        notifyDataSetChanged();
    }

    private void salidaVehiculo(int posicion) {
        Historial parqueo = parqueados.get(posicion);
        Historial historialActualizado = new Historial(parqueo.getVehiculo(), parqueo.getFechaIngreso(), LocalDateTime.now(), 1000);
        confirmarSalida(posicion, historialActualizado).show();
    }

    private AlertDialog confirmarSalida(int posicion, Historial historial){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Total a cobrar")
                .setMessage("  $ "+historial.getCobro())
                .setPositiveButton("OK", (dialogInterface, i) -> {

                    //Implementar metodo para salida de vehiculo

                    parqueados.remove(posicion);
                    notifyDataSetChanged();
                })
                .setNegativeButton("CANCELAR", (dialogInterface, i) -> dialogInterface.cancel());
        return builder.create();
    }

}
