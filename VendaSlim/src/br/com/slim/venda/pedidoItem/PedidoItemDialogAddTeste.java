package br.com.slim.venda.pedidoItem;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import br.com.slim.venda.R;

@SuppressLint("NewApi")
public class PedidoItemDialogAddTeste extends DialogFragment{
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.pedido_item_dialog_add, null);
		builder.setView(view)
		.setPositiveButton("Positibo", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
			
		})
		.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				PedidoItemDialogAddTeste.this.getDialog().cancel();
			}
			
		});
		builder.setTitle("Titulo teste");
		builder.setMessage("aaaaaaaaaaa");
		return builder.create();
	}
}
