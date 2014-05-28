package br.com.slim.venda.cargaInicial;

import org.holoeverywhere.app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteView;

public class TipoCargaView extends Activity {
	
	private Button btnPro;
	private Button btnDemonstracao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tipo_carga_activity);
		getSupportActionBar().setTitle("Demostração");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		btnPro = (Button) findViewById(R.id.btnPro);
		btnDemonstracao = (Button) findViewById(R.id.btnDemo);
		btnPro.setOnClickListener(new VersaoProListner());
		btnDemonstracao.setOnClickListener(new VersaoDemostracaoListner());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	
	private class VersaoProListner implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			Intent it = new Intent(TipoCargaView.this, ClienteView.class);			
			startActivity(it);			
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			finish();
		}
	}
	
	private class VersaoDemostracaoListner implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			Intent it = new Intent(TipoCargaView.this, VersaoDemostracaoView.class);			
			startActivity(it);			
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			finish();
		}
	}
	

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}	
	
}