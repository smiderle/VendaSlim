package br.com.slim.venda.cliente;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import br.com.slim.venda.R;
import br.com.slim.venda.clienteNovo.ClienteNovoView;
import br.com.slim.venda.config.ConfigVO;


public class ClienteView extends Activity implements OnItemClickListener{
	
	public static final int CHAMADOR_PEDIDO = 1;
		
	private ListView listView;
	private ArrayList<ClienteVO> lsCliente = new ArrayList<ClienteVO>();
	ClienteAdapter clienteAdapter;
	EditText edFiltro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		  	//setTheme(R.style.Theme_Sherlock);
	       super.onCreate(savedInstanceState);
	       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	       setContentView(R.layout.cliente_activity);
		   listView = (ListView) findViewById(R.id.listCliente);
		   listView.setOnItemClickListener(this);
			
		   edFiltro = (EditText) findViewById(R.id.edFiltro);
		   edFiltro.addTextChangedListener(new BuscaProdutoKeyListner());
		   
			carregaTodosClientes();
			createListView();
	}
	

	private void createListView(){
		clienteAdapter = new ClienteAdapter(this, lsCliente);
		listView.setAdapter(clienteAdapter);
		//Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
		
	}
	
	private void carregaTodosClientes(){
		ClienteDAO clienteDAO = new ClienteDAO(this);
		lsCliente.clear();
		lsCliente = clienteDAO.getAll();
	}
	
	private void carregaClientePorFiltro(String filtro){
		ClienteDAO clienteDAO = new ClienteDAO(ClienteView.this);
		lsCliente.clear();
		lsCliente = clienteDAO.getAllByFiltro(filtro);
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 //menu.add(R.menu.menu_cliente_novo);
		 
		 getMenuInflater().inflate(R.menu.menu_cliente_novo, menu);
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
		case R.id.userAdd:
			Intent it = new Intent(this, ClienteNovoView.class);
			startActivityForResult(it, 1);
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if(resultCode == Activity.RESULT_OK){
			carregaTodosClientes();
			createListView();
		}
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		ClienteDAO clienteDAO = new ClienteDAO(this);
		if(getIntent().getIntExtra("CHAMADOR",0) == CHAMADOR_PEDIDO){			
			ClienteVO clienteVO = lsCliente.get(position);
			ClienteVO clienteVO_ = clienteDAO.getClienteByID(clienteVO.getIdCliente());
			Intent it = new Intent();
			it.putExtra("CLIENTE_SELECIONADO", clienteVO_);
			setResult(RESULT_OK,it);
			finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
		} else {
			ClienteVO clienteVO = lsCliente.get(position);		
			ClienteVO clienteVO_ = clienteDAO.getClienteByID(clienteVO.getIdCliente());
			
			
			Intent it = new Intent(ClienteView.this, ClienteNovoView.class);
			it.putExtra("CLIENTE_SELECIONADO", clienteVO_);
			startActivity(it);
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			//Toast.makeText(view.getContext(), "Teste", Toast.LENGTH_SHORT).show();
		}
			
	}

	private boolean permitirVenda(){
		if(ConfigVO.acaoVendaTitulosVencidos == ConfigVO.PREFERENCES_ACAO_AVISAR){
			
		} else if(ConfigVO.acaoVendaTitulosVencidos == ConfigVO.PREFERENCES_ACAO_BLOQUEAR){
			
		}
		return false;
	}
	
	private final class BuscaProdutoKeyListner implements TextWatcher{		

		@Override
		public void afterTextChanged(Editable s) {
			if(s != null && s.toString().length() >=3){
				carregaClientePorFiltro(s.toString());
				createListView();
			}
			
			if(s != null && s.toString().length() >=1){
				carregaClientePorFiltro(s.toString());
				createListView();
			} else {
				carregaTodosClientes();
				createListView();
			}
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			if((after < count && after == 2) || (start == 2 && count == 1)){
				carregaTodosClientes();
				createListView();
			}
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {		
			
		}
		
	}
	

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
}




















































/*
package br.com.slim.venda.cliente;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import br.com.slim.venda.R;
import br.com.slim.venda.clienteNovo.ClienteNovoView;
import br.com.slim.venda.config.ConfigVO;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class ClienteView extends SherlockActivity implements OnItemClickListener{
	
	public static final int CHAMADOR_PEDIDO = 1;
		
	private ListView listView;
	private ArrayList<ClienteVO> lsCliente = new ArrayList<ClienteVO>();
	ClienteAdapter clienteAdapter;
	EditText edFiltro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		  	//setTheme(R.style.Theme_Sherlock);
	       super.onCreate(savedInstanceState);
	       
	       getSupportActionBar().setTitle("  Clientes");
	       getSupportActionBar().setHomeButtonEnabled(true);
	       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	       
	       setContentView(R.layout.cliente_activity);
		   listView = (ListView) findViewById(R.id.listCliente);
		   listView.setOnItemClickListener(this);
			
		   edFiltro = (EditText) findViewById(R.id.edFiltro);
		   edFiltro.addTextChangedListener(new BuscaProdutoKeyListner());
		   
			carregaTodosClientes();
			createListView();
	}
	

	private void createListView(){
		clienteAdapter = new ClienteAdapter(this, lsCliente);
		listView.setAdapter(clienteAdapter);
		//Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
		
	}
	
	private void carregaTodosClientes(){
		ClienteDAO clienteDAO = new ClienteDAO(this);
		lsCliente.clear();
		lsCliente = clienteDAO.getAll();
	}
	
	private void carregaClientePorFiltro(String filtro){
		ClienteDAO clienteDAO = new ClienteDAO(ClienteView.this);
		lsCliente.clear();
		lsCliente = clienteDAO.getAllByFiltro(filtro);
	}
	
	
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 menu.add(0, 1, 1, "Novo Cliente")
		 .setTitle("Novo Cliente")
         .setIcon(R.drawable.adduser)
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		 return super.onCreateOptionsMenu(menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Setado um result cancelado porque o CabecalhoFragments 
			tmb pode chamar essa activity e esta esperando algum retorno
			setResult(RESULT_CANCELED, null);
			this.finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			break;
		case 1:
			Intent it = new Intent(this, ClienteNovoView.class);
			startActivityForResult(it, 1);
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if(resultCode == Activity.RESULT_OK){
			carregaTodosClientes();
			createListView();
		}
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		ClienteDAO clienteDAO = new ClienteDAO(this);
		if(getIntent().getIntExtra("CHAMADOR",0) == CHAMADOR_PEDIDO){			
			ClienteVO clienteVO = lsCliente.get(position);
			ClienteVO clienteVO_ = clienteDAO.getClienteByID(clienteVO.getIdCliente());
			Intent it = new Intent();
			it.putExtra("CLIENTE_SELECIONADO", clienteVO_);
			setResult(RESULT_OK,it);
			finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
		} else {
			ClienteVO clienteVO = lsCliente.get(position);		
			ClienteVO clienteVO_ = clienteDAO.getClienteByID(clienteVO.getIdCliente());
			
			
			Intent it = new Intent(ClienteView.this, ClienteNovoView.class);
			it.putExtra("CLIENTE_SELECIONADO", clienteVO_);
			startActivity(it);
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
			//Toast.makeText(view.getContext(), "Teste", Toast.LENGTH_SHORT).show();
		}
			
	}

	private boolean permitirVenda(){
		if(ConfigVO.acaoVendaTitulosVencidos == ConfigVO.PREFERENCES_ACAO_AVISAR){
			
		} else if(ConfigVO.acaoVendaTitulosVencidos == ConfigVO.PREFERENCES_ACAO_BLOQUEAR){
			
		}
		return false;
	}
	
	private final class BuscaProdutoKeyListner implements TextWatcher{		

		@Override
		public void afterTextChanged(Editable s) {
			if(s != null && s.toString().length() >=3){
				carregaClientePorFiltro(s.toString());
				createListView();
			}
			
			if(s != null && s.toString().length() >=1){
				carregaClientePorFiltro(s.toString());
				createListView();
			} else {
				carregaTodosClientes();
				createListView();
			}
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			if((after < count && after == 2) || (start == 2 && count == 1)){
				carregaTodosClientes();
				createListView();
			}
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {		
			
		}
		
	}
	

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
}
*/