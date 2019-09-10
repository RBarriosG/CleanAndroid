package co.com.ceiba.clean.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

import co.com.ceiba.clean.di.Injeccion;
import co.com.ceiba.clean.R;
import co.com.ceiba.clean.activity.MainActivity;
import co.com.ceiba.clean.adapter.RecyclerAdapterParqueado;
import co.com.ceiba.clean.dialogo.DialogoAgregarParqueo;
import co.com.ceiba.clean.dialogo.DialogoExepcion;
import co.com.ceiba.clean.viewmodel.ParqueadoViewModel;
import co.com.ceiba.clean.viewmodel.ViewModelFactory;
import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionIngresoPlacaVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionMaximoCupoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionVehiculoNoSeEncuentraEnParqueadero;
import co.com.ceiba.domain.excepcion.ExcepcionVehiculoYaEstaEnParqueadero;
import co.com.ceiba.domain.excepcion.ExepcionLongitudValor;
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
            adapter.setListaParqueos(parqueados = parqueadoViewModel.listarParqueados());
        } catch (NullPointerException npe) {
            DialogoExepcion.dialogoExepciones(getContext(), getString(R.string.no_hay_vehiculos_parqueados)).show();
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
                if (parqueadoViewModel.buscarVehiculoParqueado(placa).isPresent()) {
                    historialesBuscado.add(parqueadoViewModel.buscarVehiculoParqueado(placa).get());
                }
            } catch (ExcepcionVehiculoNoSeEncuentraEnParqueadero e) {
                DialogoExepcion.dialogoExepciones(getContext(), e.getMessage()).show();
            }

            adapter.setListaParqueos(historialesBuscado);
        } else {
            adapter.setListaParqueos(parqueados);
        }
    }

    private void guardar() {
        final DialogoAgregarParqueo dialogoAgregarParqueo = new DialogoAgregarParqueo();
        dialogoAgregarParqueo.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), getString(R.string.dialogo_guardar));
        getActivity().getSupportFragmentManager().executePendingTransactions();

        ((AlertDialog) Objects.requireNonNull(dialogoAgregarParqueo.getDialog())).getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(view -> {

                    String placa;

                    if (Objects.requireNonNull(dialogoAgregarParqueo.placaEditText.getText()).toString().equals("") ||
                            dialogoAgregarParqueo.placaEditText.getText().toString().length() < 5 ||
                            dialogoAgregarParqueo.placaEditText.getText().toString().length() > 6) {

                        dialogoAgregarParqueo.placaInput.setError("Placa vacia o menor a 5 caracteres o mayor a 6 ");
                        return;

                    } else {
                        placa = Objects.requireNonNull(dialogoAgregarParqueo.placaEditText.getText()).toString().toUpperCase().trim();
                    }

                    int radioSeleccionado = dialogoAgregarParqueo.radioGrupoTipo.getCheckedRadioButtonId();

                    TipoVehiculo tipo;
                    if (radioSeleccionado == R.id.parqueosRbCarro) {
                        tipo = TipoVehiculo.CARRO;
                    } else {
                        tipo = TipoVehiculo.MOTO;
                    }

                    int cilindraje;

                    if (Objects.requireNonNull(dialogoAgregarParqueo.cilindrajeEditText.getText()).toString().equals("") && tipo == TipoVehiculo.MOTO) {
                        dialogoAgregarParqueo.cilindrajeInput.setError("Debe ingresar el cilindraje");
                        return;
                    } else if (dialogoAgregarParqueo.cilindrajeEditText.getText().toString().equals("") && tipo == TipoVehiculo.CARRO) {
                        cilindraje = 0;
                    } else {
                        cilindraje = Integer.parseInt(Objects.requireNonNull(dialogoAgregarParqueo.cilindrajeEditText.getText()).toString().trim());
                    }

                    try {
                        Vehiculo vehiculo = new Vehiculo(placa, cilindraje, tipo);
                        Historial historial = new Historial(vehiculo, LocalDateTime.now());
                        parqueados.add(parqueadoViewModel.ingresarVehiculo(historial));
                    } catch (ExepcionLongitudValor | ExcepcionIngresoPlacaVehiculo |
                            ExcepcionVehiculoYaEstaEnParqueadero | ExcepcionMaximoCupoVehiculo e) {
                        DialogoExepcion.dialogoExepciones(getContext(), e.getMessage()).show();
                    }
                    adapter.setListaParqueos(parqueados);
                    adapter.notifyDataSetChanged();
                    dialogoAgregarParqueo.dismiss();
                });
    }

}