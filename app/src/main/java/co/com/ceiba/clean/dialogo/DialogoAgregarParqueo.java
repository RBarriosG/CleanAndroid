package co.com.ceiba.clean.dialogo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import co.com.ceiba.clean.R;

public class DialogoAgregarParqueo extends DialogFragment {

    public TextInputLayout placaInput;

    public TextInputEditText placaEditText;

    public TextInputLayout cilindrajeInput;

    public TextInputEditText cilindrajeEditText;

    public RadioGroup radioGrupoTipo;

    public MaterialButton botonGuadar;

    public MaterialButton botonCancelar;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View dialogView = Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.dialogo_agregar_parqueo, null);

        builder.setView(dialogView);

        placaInput = dialogView.findViewById(R.id.parqueadosTilPlaca);
        placaEditText = dialogView.findViewById(R.id.parqueadosTiePlaca);
        cilindrajeInput = dialogView.findViewById(R.id.parqueadosTilCilindraje);
        cilindrajeEditText = dialogView.findViewById(R.id.parqueadosTieCilindraje);
        radioGrupoTipo = dialogView.findViewById(R.id.parqueosRgTipo);
        botonGuadar = dialogView.findViewById(R.id.parqueadosBotonGuardar);
        botonCancelar = dialogView.findViewById(R.id.parqueadosBotonCancelar);

        return builder.create();

    }
}
