package co.com.ceiba.clean.adapter;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.clean.R;
import co.com.ceiba.clean.activity.MainActivity;
import co.com.ceiba.clean.fragment.ParqueadoFragment;
import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionVehiculoNoSeEncuentraEnParqueadero;
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
        holder.textFechaIngreso.setText(parqueados.get(position).getFechaIngreso().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        holder.botonSalida.setOnClickListener(view -> salidaVehiculo(position));
    }

    @Override
    public int getItemCount() {
        return parqueados.size();
    }

    public void setListaParqueos(List<Historial> parqueados) {
        this.parqueados = parqueados;
        notifyDataSetChanged();
    }

    private void salidaVehiculo(int posicion) {
        Historial parqueo = parqueados.get(posicion);
        Historial historialActualizado = new Historial(parqueo.getVehiculo(), parqueo.getFechaIngreso(), LocalDateTime.now(), 0);
        confirmarSalida(posicion, historialActualizado);
    }

    private void confirmarSalida(int posicion, Historial historial) {
        try {
            List<Double> valor = new ArrayList<>();
            ParqueadoFragment.parqueadoViewModel.actualizarHistorial(historial).map(aDouble -> valor.add(aDouble));
            parqueados.remove(posicion);
            notifyDataSetChanged();
            Toast.makeText(activity, "Valor A cobrar: "+valor.get(0), Toast.LENGTH_LONG).show();
        } catch (ExcepcionVehiculoNoSeEncuentraEnParqueadero e){
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
