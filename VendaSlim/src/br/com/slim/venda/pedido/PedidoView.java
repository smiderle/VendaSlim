package br.com.slim.venda.pedido;

import org.holoeverywhere.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import br.com.slim.venda.R;
import br.com.slim.venda.clienteNovo.ClienteNovoView;

public class PedidoView  extends Activity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		PedidoVO pedido = (PedidoVO) getIntent().getParcelableExtra("PEDIDO_SELECIONADO");
	
		setContentView(R.layout.activity_main);
		getSupportActionBar().setTitle("Pedido");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		

		PedidoTabFragment fragmenttab = new PedidoTabFragment(pedido);
		getSupportFragmentManager().beginTransaction()
		.add(R.id.item_detail_container, fragmenttab).commit();
	}	
	
	@Override	
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:			
			this.finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			break;		
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}