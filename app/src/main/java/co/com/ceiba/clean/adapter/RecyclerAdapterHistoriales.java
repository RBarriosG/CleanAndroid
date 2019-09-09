package co.com.ceiba.clean.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

import co.com.ceiba.clean.R;
import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.modelo.Historial;

public class RecyclerAdapterHistoriales extends RecyclerView.Adapter<RecyclerViewHolderHistoriales> {

    private static final String CARRO = "CARRO";
    private static final String MOTO = "MOTO";

    private Context context;
    private LayoutInflater inflater;

    private List<Historial> historiales;

    public RecyclerAdapterHistoriales(Context context, List<Historial> historiales) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.historiales = historiales;
    }

    @NonNull
    @Override
    public RecyclerViewHolderHistoriales onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolderHistoriales(inflater.inflate(R.layout.item_historial, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderHistoriales holder, int position) {
        holder.imagenTipoVehiculo.setImageDrawable(
                historiales.get(position).getVehiculo().getTipo() == TipoVehiculo.CARRO ?
                        context.getDrawable(R.drawable.ic_coche) :
                        context.getDrawable(R.drawable.ic_motocicleta));
        holder.textPlaca.setText(historiales.get(position).getVehiculo().getPlaca());
        holder.textTipo.setText(historiales.get(position).getVehiculo().getTipo() == TipoVehiculo.CARRO ? CARRO : MOTO);
        holder.textCilindraje.setText(String.valueOf(historiales.get(position).getVehiculo().getCilindraje()));
        holder.textFechaIngreso.setText(historiales.get(position).getFechaIngreso().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        holder.textFechaSalida.setText(historiales.get(position).getFechaSalida().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        holder.textCosto.setText(String.format("$ %s", historiales.get(position).getCobro()));
    }

    @Override
    public int getItemCount() {
        return historiales.size();
    }

    public void setHistoriales(List<Historial> historiales){
        this.historiales = historiales;
        notifyDataSetChanged();
    }
}
