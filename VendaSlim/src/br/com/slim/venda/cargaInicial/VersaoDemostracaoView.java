package br.com.slim.venda.cargaInicial;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.EditText;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import br.com.slim.venda.R;
import br.com.slim.venda.widget.Alert;

public class VersaoDemostracaoView extends Activity {

	private Button btnGerarDemostracao;
	private ArrayList<SincronizacaoItensVO> lsSincronizacaoIntesVO;
	private EditText txtEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.versao_demostracao_activity);
		getSupportActionBar().setTitle("Carga Inicial");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		btnGerarDemostracao = (Button) findViewById(R.id.btnGerarDemostracao);
		
		btnGerarDemostracao.setOnClickListener(new GerarDadosDemostracaoListner());
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
	
	private class GerarDadosDemostracaoListner implements View.OnClickListener {
		//SincronizacaoDialog dialog = new SincronizacaoDialog(VersaoDemostracaoView.this, lsSincronizacaoIntesVO);
		
		@Override
		public void onClick(View v) {
			String email = txtEmail.getText().toString();
			
			if(email != null && !email.equals("")){
				if(email.indexOf("@") > -1){
					Intent it = new Intent(VersaoDemostracaoView.this, SincronizacaoItensView.class);
					it.putExtra("CARGA_INICIAL",1);
					it.putExtra("EMAIL", email);
					startActivityForResult(it, 1);
					overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
					finish();
				} else {
					new Alert().showDialog(VersaoDemostracaoView.this, "Email invalido", "Por favor, informe um email válido", "OK", null);
				}
			} else {
				new Alert().showDialog(VersaoDemostracaoView.this, "Email invalido", "Por favor, informe um email", "OK", null);
			}
			//dialog.show();
			
		}		

	}	
	
	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
	
}