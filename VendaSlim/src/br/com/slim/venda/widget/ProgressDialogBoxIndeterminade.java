package br.com.slim.venda.widget;

import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;
import org.holoeverywhere.app.ProgressDialog;

import android.annotation.SuppressLint;
import android.os.Bundle;

@SuppressLint("ValidFragment")
public class ProgressDialogBoxIndeterminade extends DialogFragment{
	String descricao;
	@SuppressLint("ValidFragment")
	public ProgressDialogBoxIndeterminade(String descricao) {
		this.descricao = descricao;
	}
	
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getSupportActivity(), getTheme());
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(descricao);
        return dialog; 
    }
}
