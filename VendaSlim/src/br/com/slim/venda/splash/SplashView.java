package br.com.slim.venda.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.com.slim.venda.R;
import br.com.slim.venda.cargaInicial.TipoCargaView;
import br.com.slim.venda.filial.FilialController;
import br.com.slim.venda.login.Login;


public class SplashView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_view);
		
		new Handler().postDelayed(new Runnable() {			
			@Override
			public void run() {
				FilialController controller = new FilialController(SplashView.this);				
				if(controller.existeFiliais()){
					startActivity(new Intent(SplashView.this, Login.class));
					overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
				} else{
					startActivity(new Intent(SplashView.this, TipoCargaView.class));					
					overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
				}
				close();
					
			}
		}, 2000);
	}
	
	private void close(){		
		this.finish();
	}
	
	
}





