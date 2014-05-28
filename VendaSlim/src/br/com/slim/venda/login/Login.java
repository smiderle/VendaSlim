package br.com.slim.venda.login;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.EditText;
import org.holoeverywhere.widget.Spinner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.slim.venda.R;
import br.com.slim.venda.config.ConfigVO;
import br.com.slim.venda.filial.FilialController;
import br.com.slim.venda.filial.FilialVO;
import br.com.slim.venda.geolocalizacao.MyLocationManager;
import br.com.slim.venda.menu.Menu;
import br.com.slim.venda.representante.RepresentanteVO;
import br.com.slim.venda.versao.DemonstracaoExpirouActivity;
import br.com.slim.venda.versao.VersaoBO;
import br.com.slim.venda.versao.VersaoPdaVO;
import br.com.slim.venda.widget.Alert;

public class Login extends Activity {

	private final String USUARIO_INVALIDO = "Usuário ou Senha Inválidos.";
	private final String CAMPOS_INVALIDO = "Informe Todos os Campos.";
	
	Animation animBounce;
	private Button btnLogin;	
	private Button btnNotification;
	Spinner spEmpresa;
	ArrayAdapter<FilialVO> arrayAdapter;
	private EditText edSenha;
	private EditText edUsuario;
	private TextView tvVersao;
	private TextView tvDtExpiracao;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.login_activity);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new LoginClickListner());
		btnNotification =(Button) findViewById(R.id.btnNotification);
		btnNotification.setVisibility(View.INVISIBLE);
		btnNotification.setText(USUARIO_INVALIDO);
		spEmpresa = (Spinner) findViewById(R.id.spEmpresa);
		edSenha = (EditText) findViewById(R.id.edSenha);
		edUsuario = (EditText) findViewById(R.id.edUsuario);
		tvVersao = (TextView) findViewById(R.id.tvVersao);
		tvDtExpiracao = (TextView) findViewById(R.id.tvDtExpiracao);
		
		populaSpEmpresa();
		recuperaLogin();
		
		//Foi gerado na carga inicial
		RepresentanteVO repVO = getIntent().getParcelableExtra("REPRESENTANTE_GERADO");
		if(repVO != null){
			edUsuario.setText(repVO.getLogin());
			edSenha.setText(repVO.getSenha());
		}
		
		verificaVersao();
		
		
		//Fonte http://www.androidhive.info/2013/06/android-working-with-xml-animations/#bounce
		//animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
		//btnLogin.setAnimation(animBounce);
		
		/*if(ConfigVO.obterLocalizacao)
			startServiceGeo();*/
	}
	
	public void verificaVersao(){
		VersaoBO versaoBO = new VersaoBO(Login.this);
		VersaoPdaVO versaoPda = versaoBO.getVersao();
		if(versaoPda != null && versaoPda.getDataExpiracao() != null){
			tvDtExpiracao.setVisibility(View.VISIBLE);
			if(versaoBO.demonstracaoExpirou()){
				tvDtExpiracao.setText("Versão Demonstração Expirou.");
			} else {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				tvDtExpiracao.setText("Expira em "+format.format(versaoPda.getDataExpiracao()));
			}
			
			tvVersao.setVisibility(View.VISIBLE);
			tvVersao.setText("Demonstração");
			
		}
	}
	
	public void recuperaLogin(){
		SharedPreferences prefs = this.getSharedPreferences("br.com.slim.venda", Context.MODE_PRIVATE);
		edUsuario.setText(prefs.getString("usuario", ""));
	}
	
	public void populaSpEmpresa(){
		ArrayList<FilialVO> lsFiliais = new FilialController(Login.this).getAllBasic();		
				
		arrayAdapter = new ArrayAdapter<FilialVO>(Login.this, R.layout.simple_spinner_item_custon_white, lsFiliais);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		spEmpresa.setAdapter(arrayAdapter);
	}
	
	private class LoginClickListner implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			LoginController controller = new LoginController(Login.this);
			VersaoBO versaoBO = new VersaoBO(Login.this);
			FilialVO filial = (FilialVO) spEmpresa.getSelectedItem();
			try {
				
				if(versaoBO.demonstracaoExpirou()){
					startActivity(new Intent(Login.this, DemonstracaoExpirouActivity.class));
					overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
				}else {
					boolean loginValido = controller.validarLogin(edUsuario.getText().toString(), edSenha.getText().toString(),filial);
					if(loginValido){
						Intent it = new Intent(Login.this, Menu.class);						
						startActivity(it);
						overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
					}
				}
				
			} catch (RuntimeException e) {
				e.printStackTrace();
				mostrarNotificacao(e.getMessage());
			}			
		}
		
	}
	
	private void mostrarNotificacao(final String msg){
		btnNotification.setText(msg);
		btnNotification.setVisibility(View.VISIBLE);
		animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_in_move_out);
		btnNotification.setAnimation(animBounce);		
	
	}
	
	private void startServiceGeo(){
		MyLocationManager myLocationManager = new MyLocationManager(getApplicationContext());
		myLocationManager.onLocationUpdate();
		myLocationManager.getLocation();
		boolean alarmRunning = (PendingIntent.getBroadcast(Login.this, 0, 
		        new Intent("br.com.slim.venda.START_SERVICE_GEO"), 
		        PendingIntent.FLAG_NO_CREATE) != null);
		
		if(!alarmRunning){
			sendBroadcast(new Intent("br.com.slim.venda.START_SERVICE_GEO"));
		}
	}
	

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
}
