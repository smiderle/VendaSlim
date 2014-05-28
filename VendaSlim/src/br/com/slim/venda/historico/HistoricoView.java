package br.com.slim.venda.historico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.AdapterView.OnItemSelectedListener;
import org.holoeverywhere.widget.Spinner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import br.com.slim.venda.R;
import br.com.slim.venda.pedido.PedidoModel;
import br.com.slim.venda.pedido.PedidoDAO;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedido.PedidoView;
import br.com.slim.venda.widget.Alert;
import br.com.slim.venda.widget.PickersDatePickerFragment;


public class HistoricoView extends Activity implements OnItemClickListener{

	private ListView listView;

	ArrayList<PedidoVO> lsPedidoVo;

	EditText edDtInicio;
	EditText edDtFim;
	EditText edFiltro;
	Spinner spFiltroTroca;
	//Button btnTrocaFiltro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		getSupportActionBar().setTitle("  Historico");
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.historico_activity);

		edDtInicio = (EditText) findViewById(R.id.edDtInicio);
		edDtFim = (EditText) findViewById(R.id.edDtFim);
		edFiltro = (EditText) findViewById(R.id.edFiltro);
		edFiltro.setSelected(false);
		//spFiltroTroca = (Spinner) findViewById(R.id.spFiltro);
		//spFiltroTroca.setOnItemSelectedListener(new FiltroListner());
		/* btnTrocaFiltro = (Button) findViewById(R.id.btnTrocarFiltro);
	       btnTrocaFiltro.setOnClickListener(new TrocaFiltro());*/
		edDtInicio.setOnClickListener(new OnFocusInDtInicial());
		edDtFim.setOnClickListener(new OnFocusInDtFinal());
		edFiltro.addTextChangedListener(new BuscaClienteKeyListner());
		edDtInicio.addTextChangedListener(new DateChangeListner());
		edDtFim.addTextChangedListener(new DateChangeListner());
		edDtFim.setVisibility(View.INVISIBLE);
		edDtInicio.setVisibility(View.INVISIBLE);

		listView = (ListView) findViewById(R.id.listHistoricoPedidos);
		listView.setOnItemClickListener(this);
		carragaPedidos();
		carregaListView();
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			/*Setado um result cancelado porque o CabecalhoFragments 
			tmb pode chamar essa activity e esta esperando algum retorno*/
			setResult(RESULT_CANCELED, null);
			this.finish();

			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);		
			
			break;			
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		SubMenu subMenu = menu.addSubMenu(" Filtrar Por");
		subMenu.add(1,1,1,"Por Cliente");
		subMenu.add(1,2,2,"Por Data");

		com.actionbarsherlock.view.MenuItem subMenuItem = subMenu.getItem();		
		subMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		
		return super.onCreateOptionsMenu(menu);
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			break;
			
		case 1:
			edDtInicio.setVisibility(View.INVISIBLE);
			edDtFim.setVisibility(View.INVISIBLE);
			edFiltro.setVisibility(View.VISIBLE);
			edFiltro.setFocusable(true);
			edFiltro.setText("");
			carragaPedidos();
			carregaListView();
			break;
			
		case 2:
			edDtInicio.setVisibility(View.VISIBLE);
			edDtFim.setVisibility(View.VISIBLE);
			edFiltro.setVisibility(View.INVISIBLE);
			edDtInicio.setText("");
			edDtFim.setText("");
			carragaPedidos();
			carregaListView();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
*/

	public void carragaPedidos(){
		PedidoDAO pedidoDAO = new PedidoDAO(HistoricoView.this);
		lsPedidoVo = pedidoDAO.getAll();
	}

	public void carregaListView(){
		HistoricoAdapter historicoAdapter = new HistoricoAdapter(HistoricoView.this, lsPedidoVo);
		listView.setAdapter(historicoAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		PedidoVO pedidoVO = lsPedidoVo.get(position);
		PedidoModel pedidoModel = new PedidoModel(HistoricoView.this);
		pedidoModel.carregaPedido(pedidoVO);
		Intent it = new Intent(HistoricoView.this, PedidoView.class);
		it.putExtra("PEDIDO_SELECIONADO", pedidoVO);
		setResult(RESULT_OK,it);
		startActivity(it);
		overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
	}


	private class OnFocusInDtInicial implements View.OnClickListener{		

		@Override
		public void onClick(View v) {	
			PickersDatePickerFragment p= new PickersDatePickerFragment(edDtInicio);
			p.show(HistoricoView.this);
		}		
	}
	
	private class OnFocusInDtFinal implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			PickersDatePickerFragment p= new PickersDatePickerFragment(edDtFim);
			p.show(HistoricoView.this);
		}       	
	}


		private void carregarPedidosPorPrefixo(){
			lsPedidoVo.clear();
			PedidoDAO pedidoDAO = new PedidoDAO(HistoricoView.this);
			Integer idPedidoCliente  = 0;

			try {
				idPedidoCliente = Integer.parseInt(edFiltro.getText().toString());
			} catch (NumberFormatException e) {}

			lsPedidoVo = pedidoDAO.getAllByPrefixo(idPedidoCliente, edFiltro.getText().toString());
		}

		private void carregarPedidosPorData(){
			try {
				lsPedidoVo.clear();
				PedidoDAO pedidoDAO = new PedidoDAO(HistoricoView.this);
				long dtInicial;
				long dtFinal;
				if(!edDtInicio.getText().toString().equals("")){
					dtInicial = new SimpleDateFormat("dd/MM/yyyy").parse(edDtInicio.getText().toString()).getTime();
				} else {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.MONTH, -1);
					dtInicial = c.getTimeInMillis();
				}

				if(!edDtFim.getText().toString().equals("")){
					Calendar c =  Calendar.getInstance();
					c.setTimeInMillis(new SimpleDateFormat("dd/MM/yyyy").parse(edDtFim.getText().toString()).getTime());
					c.add(Calendar.DAY_OF_MONTH, 1);						
					dtFinal = c.getTime().getTime(); 
				} else {
					dtFinal = new Date().getTime();
				}

				lsPedidoVo = pedidoDAO.getAllByDate(dtInicial, dtFinal);
				carregaListView();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		private final class BuscaClienteKeyListner implements TextWatcher{		

			@Override
			public void afterTextChanged(Editable s) {			
				carregarPedidosPorPrefixo();
				carregaListView();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {}

		}
		
		
		private final class DateChangeListner implements TextWatcher{		

			@Override
			public void afterTextChanged(Editable s) {}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {		
				carregarPedidosPorData();
			}

		}
		
		@Override
		@SuppressLint("NewApi")
		public void onBackPressed() {	
			super.onBackPressed();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
		}
}
