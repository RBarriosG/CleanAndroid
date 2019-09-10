package co.com.ceiba.clean.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import javax.inject.Inject;

import co.com.ceiba.clean.di.Injeccion;
import co.com.ceiba.clean.R;
import co.com.ceiba.clean.adapter.RecyclerAdapterHistoriales;
import co.com.ceiba.clean.dialogo.DialogoExepcion;
import co.com.ceiba.clean.viewmodel.HistorialesViewModel;
import co.com.ceiba.clean.viewmodel.ViewModelFactory;

public class HistorialesFragment extends Fragment {

    private RecyclerAdapterHistoriales adapter;

    private RecyclerView recycler;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new RecyclerAdapterHistoriales(getContext(), new ArrayList<>());
        viewModelFactory = Injeccion.provideVieModelFactory(getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_historiales, container, false);

        HistorialesViewModel historialesViewModel = new ViewModelProvider(this, viewModelFactory).get(HistorialesViewModel.class);

        recycler = root.findViewById(R.id.recyclerHistoriales);
        try {
            adapter.setHistoriales(historialesViewModel.listarHistoriales());
        } catch (NullPointerException npe){
            DialogoExepcion.dialogoExepciones(getContext(), getString(R.string.no_hay_historiales_de_vehiculos)).show();
        }
        actualizarRecycler();

        return root;
    }

    private void actualizarRecycler() {
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();
    }

}