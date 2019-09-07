package co.com.ceiba.clean.dialogo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import co.com.ceiba.clean.R;

public class DialogoAgregarParqueo extends DialogFragment {

    private View dialogView;

    public TextInputLayout placaInput;

    public TextInputEditText placaEditText;

    public TextInputLayout cilindrajeInput;

    public TextInputEditText cilindrajeEditText;

    public RadioGroup radioGrupoTipo;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialogo_agregar_parqueo, null);

        builder.setView(dialogView);

        builder.setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.cancel());

        builder.setPositiveButton("Guardar", null);

        placaInput = dialogView.findViewById(R.id.parqueadosTilPlaca);
        placaEditText = dialogView.findViewById(R.id.parqueadosTiePlaca);
        cilindrajeInput = dialogView.findViewById(R.id.parqueadosTilCilindraje);
        cilindrajeEditText = dialogView.findViewById(R.id.parqueadosTieCilindraje);
        radioGrupoTipo = dialogView.findViewById(R.id.parqueosRgTipo);

        return builder.create();

    }
}
