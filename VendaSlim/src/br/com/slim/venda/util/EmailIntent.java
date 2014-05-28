package br.com.slim.venda.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class EmailIntent {
	Context context;
	public EmailIntent(Context context) {
		this.context = context;
	}
	
	public void send(String[] para){
		Intent it = new Intent(Intent.ACTION_SEND);
		it.setType("message/rfc822");
		it.putExtra(Intent.EXTRA_EMAIL, para);
		it.putExtra(Intent.EXTRA_SUBJECT,"VendaSlim ");
		it.putExtra(Intent.EXTRA_TEXT, "");		
		try {
			context.startActivity(Intent.createChooser(it, "Titulo"));
		} catch (Exception e) {
			Toast.makeText(context, "Erro ao enviar email", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
}
