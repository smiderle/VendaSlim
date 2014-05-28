package br.com.slim.venda;

import org.holoeverywhere.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedido.PedidoView;
import br.com.slim.venda.util.Convert;

public class PedidoConcluidoView extends Activity   {

	private PedidoVO pedidoVO;
	
	private TextView tvPedidoId;
	private TextView tvCliente;
	private TextView tvTabela;
	private TextView tvParcelamento;
	private TextView tvTotal;
	private Button btnMenu;
	private Button btnPedido;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pedido_concluido_activity);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Detalhes Pedido");
		this.pedidoVO = (PedidoVO) getIntent().getParcelableExtra("PEDIDO");
		
		tvPedidoId = (TextView) findViewById(R.id.tvPedidoId);
		tvCliente = (TextView) findViewById(R.id.tvCliente);
		tvTabela = (TextView) findViewById(R.id.tvTabela);
		tvParcelamento = (TextView) findViewById(R.id.tvParcelamento);
		tvTotal = (TextView) findViewById(R.id.tvTotalPedido);		
		
		tvTotal.setText("R$: "+Convert.toDouble(String.valueOf(pedidoVO.getTotalLiquido())));		
		tvPedidoId.setText(pedidoVO.getIdPedido()+"");
		tvCliente.setText(pedidoVO.getClienteVO().getNome());
		tvParcelamento.setText(pedidoVO.getParcelamentoVO().getDescricao());
		tvTabela.setText(pedidoVO.getTabelaPrecoVO().getDescricao());
		btnMenu = (Button) findViewById(R.id.btnMenu);
		btnPedido = (Button) findViewById(R.id.btnPedido);
		
		btnMenu.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PedidoConcluidoView.this, br.com.slim.venda.menu.Menu.class));
				overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
				finish();
			}
		});
		
		
		btnPedido.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PedidoConcluidoView.this, PedidoView.class));
				overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
				finish();
			}
		});
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
