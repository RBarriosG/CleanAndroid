package co.com.ceiba.clean.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import co.com.ceiba.clean.Injeccion;
import co.com.ceiba.clean.R;
import co.com.ceiba.clean.activity.MainActivity;
import co.com.ceiba.clean.adapter.RecyclerAdapterParqueado;
import co.com.ceiba.clean.dialogo.DialogoAgregarParqueo;
import co.com.ceiba.clean.viewmodel.ParqueadoViewModel;
import co.com.ceiba.clean.viewmodel.ViewModelFactory;
import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionIngresoPlacaVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionMaximoCupoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionVehiculoNoSeEncuentraEnParqueadero;
import co.com.ceiba.domain.excepcion.ExcepcionVehiculoYaEstaEnParqueadero;
import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.modelo.Vehiculo;

public class ParqueadoFragment extends Fragment {

    private RecyclerAdapterParqueado adapter;

    private RecyclerView recycler;

    @Inject
    ViewModelFactory viewModelFactory;

    public static ParqueadoViewModel parqueadoViewModel;

    private EditText editTextPlaca;

    private List<Historial> parqueados = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new RecyclerAdapterParqueado(((MainActivity) getActivity()), parqueados);
        viewModelFactory = Injeccion.provideVieModelFactory(getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_parqueados, container, false);

        parqueadoViewModel = new ViewModelProvider(this, viewModelFactory).get(ParqueadoViewModel.class);

        recycler = root.findViewById(R.id.recyclerParqueados);

        editTextPlaca = root.findViewById(R.id.editTextPlaca);

        try {
            AsyncTask.execute(() -> adapter.setListaParqueos(parqueados = parqueadoViewModel.listarParqueados()));
        } catch (NullPointerException npe) {
            Toast.makeText(getContext(), "No hay vehiculos parqueados", Toast.LENGTH_SHORT).show();
        }

        actualizarRecycler();

        (root.findViewById(R.id.fabGuardar)).setOnClickListener(view -> guardar());

        (root.findViewById(R.id.parqueadosImagenBuscar)).setOnClickListener(view -> buscar());

        return root;
    }

    private void actualizarRecycler() {
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();
    }

    private void buscar() {
        String placa = editTextPlaca.getText().toString().trim();
        if (!placa.isEmpty()) {
            List<Historial> historialesBuscado = new ArrayList<>();

            try {
                AsyncTask.execute(() -> parqueadoViewModel.buscarVehiculoParqueado(placa).map(historial -> historialesBuscado.add(historial)));
            } catch (ExcepcionVehiculoNoSeEncuentraEnParqueadero e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                adapter.setListaParqueos(parqueados);
            }

            adapter.setListaParqueos(historialesBuscado);
        } else {
            Toast.makeText(getContext(), "No hay datos a buscar", Toast.LENGTH_SHORT).show();
            adapter.setListaParqueos(parqueados);
        }
    }

    private void guardar() {
        final DialogoAgregarParqueo dialogoAgregarParqueo = new DialogoAgregarParqueo();
        dialogoAgregarParqueo.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "Dialogo Guardar");
        getActivity().getSupportFragmentManager().executePendingTransactions();

        ((AlertDialog) Objects.requireNonNull(dialogoAgregarParqueo.getDialog())).getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(view -> {
                    String placa = Objects.requireNonNull(dialogoAgregarParqueo.placaEditText.getText()).toString().toUpperCase().trim();
                    int radioSeleccionado = dialogoAgregarParqueo.radioGrupoTipo.getCheckedRadioButtonId();

                    int cilindraje = Integer.parseInt(Objects.requireNonNull(dialogoAgregarParqueo.cilindrajeEditText.getText()).toString().trim());

                    TipoVehiculo tipo;
                    if (radioSeleccionado == R.id.parqueosRbCarro) {
                        tipo = TipoVehiculo.CARRO;
                    } else {
                        tipo = TipoVehiculo.MOTO;
                    }

                    Vehiculo vehiculo = new Vehiculo(placa, cilindraje, tipo);
                    Historial historial = new Historial(vehiculo, LocalDateTime.now());


                    try {
                        AsyncTask.execute(() -> parqueados.add(parqueadoViewModel.ingresarVehiculo(historial)));
                    } catch (ExcepcionIngresoPlacaVehiculo | ExcepcionVehiculoYaEstaEnParqueadero | ExcepcionMaximoCupoVehiculo e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    adapter.setListaParqueos(parqueados);
                    adapter.notifyDataSetChanged();
                    dialogoAgregarParqueo.dismiss();
                });
    }

}