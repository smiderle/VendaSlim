package br.com.slim.venda.sincroniza;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.Button;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import br.com.slim.venda.R;
import br.com.slim.venda.pedido.PedidoDAO;
import br.com.slim.venda.pedido.PedidoVO;

public class SincronizarView extends Activity{
		
	private ArrayList<PedidoVO> lsPedidoVO;
	private SincronizaAdapter adapter;
	private Button btnEnviar;
	private ListView listView;
	private Button btnNotification;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
		
        setContentView(R.layout.sincroniza_pedidos_nao_sincronizados);        
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("  Sincronização");
        getSupportActionBar().setHomeButtonEnabled(true);
        
        btnNotification =(Button) findViewById(R.id.btnNotification);
		btnNotification.setVisibility(View.INVISIBLE);
		
        bindingFilds();
		carregaPedidos();
		createListView();
				
		
		setListners();
	}
		
	private void bindingFilds(){		
		btnEnviar = (Button) findViewById(R.id.btnSincronizar);	
		listView = (ListView) findViewById(R.id.lvPedidos);
	}
	
	private void setListners(){
		btnEnviar.setOnClickListener(new SincronizarListner());		
	}
	
	private void carregaPedidos(){
		if(lsPedidoVO != null){
			lsPedidoVO.clear();
		}
		lsPedidoVO = new PedidoDAO(this).getAllNaoSincronizado();
	}
	
	private void createListView(){
		adapter = new SincronizaAdapter(SincronizarView.this, lsPedidoVO);
		listView.setAdapter(adapter);
	}
	
	
	private class SincronizarListner implements View.OnClickListener {
		
		@Override
		public void onClick(View v) {			
			SincronizaDialog sincronizaDialog = new SincronizaDialog(SincronizarView.this, btnNotification, adapter, lsPedidoVO);
			sincronizaDialog.show(SincronizarView.this);
			
		}
	}
	
	private void mostrarNotificacao(final String msg){	
		Animation animBounce;		
		animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_in_move_out);
		btnNotification.setAnimation(animBounce);
		btnNotification.setText(msg);
		btnNotification.setVisibility(View.VISIBLE);
	
	}
	
	
	@SuppressLint("ValidFragment")	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
}
