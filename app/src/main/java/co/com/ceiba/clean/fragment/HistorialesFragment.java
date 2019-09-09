package co.com.ceiba.clean.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.com.ceiba.clean.R;
import co.com.ceiba.clean.adapter.RecyclerAdapterHistoriales;
import co.com.ceiba.clean.viewmodel.HistorialesViewModel;
import co.com.ceiba.domain.modelo.Historial;

public class HistorialesFragment extends Fragment {

    private RecyclerAdapterHistoriales adapter;

    private RecyclerView recycler;

    private HistorialesViewModel historialesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_historiales, container, false);

        historialesViewModel = ViewModelProviders.of(this).get(HistorialesViewModel.class);

        recycler = root.findViewById(R.id.recyclerHistoriales);

        historialesViewModel.listarHistoriales().observe(this, new Observer<List<Historial>>() {
            @Override
            public void onChanged(List<Historial> historiales) {
                adapter.setHistoriales(historiales);
            }
        });

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