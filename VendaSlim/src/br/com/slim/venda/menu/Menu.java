package br.com.slim.venda.menu;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteView;
import br.com.slim.venda.config.ConfigBO;
import br.com.slim.venda.historico.HistoricoView;
import br.com.slim.venda.integration.Integration;
import br.com.slim.venda.item.ItemView;
import br.com.slim.venda.pedido.PedidoView;
import br.com.slim.venda.sincroniza.SincronizarView;
import br.com.slim.venda.titulos.TituloView;

public class Menu extends Activity  implements OnItemClickListener{
	private ListView listView;
	private ArrayList<ItemListView> itens;
	private MenuAdapter menuAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.menu_activity);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		listView = (ListView) findViewById(R.id.list);
		listView.setOnItemClickListener(this);
		createListView();
		//applyPreferences();
		ConfigBO configBO = new ConfigBO(Menu.this);
    	configBO.applyPreferences();
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
	
		
		
		
	
	private void createListView(){
		itens = new ArrayList<ItemListView>();
		ItemListView pedido = new ItemListView("Novo Pedido", R.drawable.pedidos);
		ItemListView clientes = new ItemListView("Clientes", R.drawable.clientes);
		ItemListView produtos = new ItemListView("Produtos", R.drawable.produtos2);
		ItemListView titulos = new ItemListView("Titulos", R.drawable.dollar);
		ItemListView historico = new ItemListView("Historico de Pedidos", R.drawable.calendar);
		//ItemListView cadastroComplementar = new ItemListView("Cadastros Complementares", R.drawable.cadastros);
		//ItemListView configuracao = new ItemListView("Configuração", R.drawable.config);
		ItemListView sincronizar = new ItemListView("Sincronizar", R.drawable.enviar);
		ItemListView sair = new ItemListView("Sair", R.drawable.sair);
		
		itens.add(pedido);
		itens.add(clientes);
		itens.add(produtos);
		itens.add(titulos);
		itens.add(historico);
		//itens.add(cadastroComplementar);
		//itens.add(configuracao);
		itens.add(sincronizar);
		itens.add(sair);
		
		//Cria o adapter
		menuAdapter = new MenuAdapter(this, itens);
		//Define o Adapter
		listView.setAdapter(menuAdapter);
		//Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
		
	}
	

	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		 //Pega o item que foi selecionado.
        ItemListView item = menuAdapter.getItem(position);
        
        switch (position) {
		case 0:
			startActivity(new Intent(Menu.this, PedidoView.class));
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			break;
		case 1:
			startActivity(new Intent(Menu.this, ClienteView.class));
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			break;
		case 2:
			startActivity(new Intent(Menu.this, ItemView.class));
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			break;
		case 3:
			startActivity(new Intent(Menu.this, TituloView.class));
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			break;
		case 4:
			startActivity(new Intent(Menu.this, HistoricoView.class));
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			break;		
		/*case 5:
			startActivity(new Intent(Menu.this, ConfigView.class));
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			break;*/
		case 5:
			startActivity(new Intent(Menu.this, SincronizarView.class));
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			/*startActivity(new Intent(Menu.this, SincronizacaoItensView.class));
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);*/
			
/*			Integration it   = new Integration(Menu.this);
			it.atualizar();*/
			
			break;	
		case 6:
			finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			break;	
		default:
			break;
		}
		
	}
	

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
	
	
	private void applyPreferences(){
		/*SharedPreferences prefs  = PreferenceManager.getDefaultSharedPreferences(this);
    	ConfigVO.permiteVendaEstoqueNegativo = prefs.getBoolean("stock_negative", false);
    	ConfigVO.acaoLimiteCredito = prefs.getString("acao_limite_credito", ConfigVO.PREFERENCES_ACAO_BLOQUEAR);
    	ConfigVO.acaoVendaTitulosVencidos = prefs.getString("acao_titulos_vencidos", ConfigVO.PREFERENCES_ACAO_BLOQUEAR);
    	ConfigVO.validarCpf = prefs.getBoolean("valid_cpf", false);
    	ConfigVO.email = prefs.getString("email", null);*/
	}
	}
