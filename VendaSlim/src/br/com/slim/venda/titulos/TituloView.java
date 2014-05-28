package br.com.slim.venda.titulos;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.Spinner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.pedidopgto.PedidoPgtoDAO;

public class TituloView extends Activity implements OnItemClickListener{
	
	private ListView listView;
	private Spinner spFiltroTitulos;
	TituloAdapter tituloAdapter;
	private ArrayList<ClienteVO> lsCliente = new ArrayList<ClienteVO>();
	//Button btnFiltro;
	
	int filtroSelecionado = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       
	       getSupportActionBar().setTitle("  Titulos");
	       getSupportActionBar().setHomeButtonEnabled(true);
	       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	       	       
	       setContentView(R.layout.titulo_activity);
		   listView = (ListView) findViewById(R.id.listTitulos);		 
		   listView.setOnItemClickListener(this);
		   
		   
		  // btnFiltro.setOnClickListener(new Teste());
		   carregaTitulosAbertos();
		   createListView();
		   //populaFiltro();
	}
	
	private void createListView(){
		tituloAdapter = new TituloAdapter(this, lsCliente);
		listView.setAdapter(tituloAdapter);
		//Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}
	
	
	private void carregaTitulosAbertos(){
		PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(TituloView.this);
		lsCliente.clear();
		this.lsCliente = pedidoPgtoDAO.getTotalTitulosAbertos(); 
	}
	
	private void carregaTodos(){
		PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(TituloView.this);
		lsCliente.clear();		
		this.lsCliente = pedidoPgtoDAO.getTotalTitulosTodos(); 
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
/*		SubMenu subMenu = menu.addSubMenu(" Filtrar Por");
		subMenu.add(1,1,1,"Titulos em Aberto");
		subMenu.add(1,2,2,"Todos os Titulos");

		MenuItem subMenuItem = subMenu.getItem();		*/
		
		getMenuInflater().inflate(R.menu.menu_titulo_filtro, menu);
		
		return super.onCreateOptionsMenu(menu);
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
		case R.id.menuFiltroTituloAbertos:
			carregaTitulosAbertos();
			createListView();
			filtroSelecionado = 0;
			break;
		case R.id.menuFiltroTituloTodos:
			carregaTodos();
			createListView();
			filtroSelecionado = 1;
			break;
	
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {		
		ClienteVO clienteVO = lsCliente.get(position);
		Intent it = new Intent(TituloView.this, TituloDetalheView.class);
		it.putExtra("IDCLIENTE_TITULOS", clienteVO.getIdCliente());
		it.putExtra("FILTRO_TITULOS", filtroSelecionado);
		startActivity(it);
		overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
	}
	
	
	
	/*
	 * Necessario pois a biblioteca holoeverywhere tem um bug no update do listview
	 * Fonte:https://github.com/Prototik/HoloEverywhere/issues/185
	 * 
	 */
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {	
		super.onWindowFocusChanged(hasFocus);
		if(hasFocus){
			listView.onWindowFocusChanged(true);
			listView.invalidate();
			listView.invalidateViews();
		}
		
	}


	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
	

}
