package co.com.ceiba.clean.dialogo;

import android.app.AlertDialog;
import android.content.Context;

import co.com.ceiba.clean.R;

public class DialogoExepcion {

    public static AlertDialog dialogoExepciones(Context context, String mensaje) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
        dialogo.setTitle(R.string.atencion);
        dialogo.setMessage(mensaje);
        dialogo.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss());
        return dialogo.create();
    }

}
