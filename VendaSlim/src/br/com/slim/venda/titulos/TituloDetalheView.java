package br.com.slim.venda.titulos;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.ExpandableListView;
import org.holoeverywhere.widget.ExpandableListView.OnChildClickListener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import br.com.slim.venda.R;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;

public class TituloDetalheView extends Activity{
	TituloExpandAdapter expandAdapter;
	ArrayList<PedidoVO> lsPedidoVO;
	private ExpandableListView expandList;
	int filtroSelecionado =0; 
	int  idCliente = 0;
		
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 	
	        super.onCreate(savedInstanceState);

           getSupportActionBar().setTitle("  Detalhes dos Titulos");	
	       getSupportActionBar().setHomeButtonEnabled(true);
	       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	        
	        setContentView(R.layout.titulo_expand_list_view);
	        expandList = (ExpandableListView) findViewById(R.id.listaCadastro);
	        	        
	        
	        filtroSelecionado = getIntent().getIntExtra("FILTRO_TITULOS", 0);
	        //Veio do TitulosView
	        if(idCliente == 0)
	        	idCliente = getIntent().getIntExtra("IDCLIENTE_TITULOS",0);
	        	        
	        if(filtroSelecionado == 0){
	        	lsPedidoVO = getPedidoVOPendentes(idCliente);
	        } else {
	        	lsPedidoVO = getTodosPedidoVO(idCliente);
	        }
	        
	        
	        expandAdapter = new TituloExpandAdapter(TituloDetalheView.this, lsPedidoVO);
	        expandList.setOnChildClickListener(new PagamentoDialog());
	        expandList.setAdapter(expandAdapter);
	        
	        
	        
	 }
	 
	 public ArrayList<PedidoVO> getPedidoVOPendentes(int idCliente){
		 TituloBO tituloBO = new TituloBO();
		 return tituloBO.getAllTitulosPendentes(TituloDetalheView.this, idCliente);
		 
	 }
	 

	 
	 public ArrayList<PedidoVO> getTodosPedidoVO(int idCliente){
		 TituloBO tituloBO = new TituloBO();
		 return tituloBO.getAllTitulosTodos(TituloDetalheView.this, idCliente);
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
	
	 private class PagamentoDialog implements  OnChildClickListener{

		 @Override
		 public boolean onChildClick(ExpandableListView parent, View v,
				 int groupPosition, int childPosition, long id) {
			 PedidoPgtoVO p = lsPedidoVO.get(groupPosition).getPedidosPgtoVO().get(childPosition);
			 TituloQuitarDialog dialog = new TituloQuitarDialog(v.getContext(), p, expandAdapter);
			 dialog.show(getSupportFragmentManager(), "bbbb");
			 //dialog.show();			 
			 return false;
		 }		 
	 }
	 

		@Override
		@SuppressLint("NewApi")
		public void onBackPressed() {	
			super.onBackPressed();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
		}
	 
	 
	 }
