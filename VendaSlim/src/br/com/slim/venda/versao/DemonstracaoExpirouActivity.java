package br.com.slim.venda.versao;

import org.holoeverywhere.widget.Button;

import br.com.slim.venda.R;
import br.com.slim.venda.R.layout;
import br.com.slim.venda.R.menu;
import br.com.slim.venda.cargaInicial.SincronizacaoItensView;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class DemonstracaoExpirouActivity extends Activity {
	
	private Button btnSincronizar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demonstracao_expirou_activity);
		
		btnSincronizar = (Button) findViewById(R.id.btnSincronizar);
		btnSincronizar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(DemonstracaoExpirouActivity.this, SincronizacaoItensView.class));
				overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);				
			}
		});
	}
	

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
}