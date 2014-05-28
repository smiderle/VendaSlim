package br.com.slim.venda;

import org.holoeverywhere.content.IntentCompat;
import org.holoeverywhere.widget.Button;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import br.com.slim.venda.login.Login;
import br.com.slim.venda.representante.RepresentanteVO;

public class SincronizacaoFinalizadaView extends Activity {

	private Button btnIniciar;
	private TextView txtUsuario;
	private TextView txtSenha;
	private RepresentanteVO representanteVO =null; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sincronizacao_finalizada_activity);
		
		btnIniciar = (Button) findViewById(R.id.btnIniciar);
		btnIniciar.setOnClickListener(new IniciarButtonListner());
		
		txtUsuario = (TextView) findViewById(R.id.txtUsuario);
		txtSenha = (TextView) findViewById(R.id.txtSenha);
		
		representanteVO =  getIntent().getParcelableExtra("REPRESENTANTE_GERADO");
		
		txtUsuario.setText(representanteVO.getLogin());
		txtSenha.setText(representanteVO.getSenha());
	}
	
	
	private class IniciarButtonListner implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			Intent it = new Intent(SincronizacaoFinalizadaView.this, Login.class);
			it.putExtra("REPRESENTANTE_GERADO", representanteVO);			
			startActivity(it);			
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			finish();
			
		}		
	}
	

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {		
	}
}
