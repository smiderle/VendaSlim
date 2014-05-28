package br.com.slim.venda.item;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.AdapterView.OnItemSelectedListener;
import org.holoeverywhere.widget.Spinner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import br.com.slim.venda.R;
import br.com.slim.venda.itemGrupo.GrupoProdutoDAO;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO;
import br.com.slim.venda.pedidoItem.PedidoItemAdapter;
import br.com.slim.venda.tabelaPreco.TabelaPrecoDAO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.widget.Alert;

public class ItemView extends Activity {

	
	
	EditText edFiltro;
	Spinner spItemGrupo;
	Spinner spinnerTabPreco;
	
	ArrayList<ItemVO> lsItemVO;
	PedidoItemAdapter pedidoItemAdapter;
	ListView listView;
	ArrayAdapter<GrupoProdutoVO> arrayAdapter;
	ArrayAdapter<TabelaPrecoVO> arrayAdapterTabPreco;
	TabelaPrecoVO tabPrecoSelecionada = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {				
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.item_activity);
				
		edFiltro = (EditText) findViewById(R.id.edFiltro);
		edFiltro.setSelected(false);
		spItemGrupo = (Spinner) findViewById(R.id.spItemGrupo);

		
		//btnTrocaFiltro = (Button) findViewById(R.id.btnTrocarFiltro);
		spinnerTabPreco = (Spinner) findViewById(R.id.spTabPreco);
		//btnTrocaFiltro.setOnClickListener(new TrocaFiltro());
		edFiltro.addTextChangedListener(new BuscaProdutoKeyListner());
		spItemGrupo.setOnItemSelectedListener(new SpIdGrupoListner());
		spItemGrupo.setVisibility(View.INVISIBLE);
		listView = (ListView) findViewById(R.id.listItem);		
		listView.setOnItemClickListener(new ListViewClikListner());
		spinnerTabPreco.setOnItemSelectedListener(new TabPrecoListner());
		
		carregaTodosItens();
		createListView();
		populaSpItemGrupo();
		populaSpinnerTabPreco();
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		
		 //getMenuInflater().inflate(R.menu.menu_cliente_novo, menu);
		
		/* menu.add(0, 1, 1, "Novo Produto")
			.setTitle("Novo Produto")
	        .setIcon(R.drawable.add);
		 
		SubMenu subMenu = menu.addSubMenu(" Filtrar Por");
		subMenu.add(1,2,1,"Descrição");
		subMenu.add(1,3,2,"Grupo");
		
		MenuItem subMenuItem = subMenu.getItem();		
		*/
		getMenuInflater().inflate(R.menu.menu_item_filtro, menu);
				 
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
		case 1:
			
			new Alert().showDialog(this, "", "Disponivel somente na versão PRÓ", "OK", null);
			//Descomentar para permitir cadastrar produto
			/*startActivityForResult(new Intent(this, ItemNovoView.class), Activity.RESULT_OK);
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);*/
			break;
		case R.id.menuFiltroDescricao:
			spItemGrupo.setVisibility(View.INVISIBLE);
			edFiltro.setVisibility(View.VISIBLE);
			edFiltro.setText("");			
			carregaTodosItens();
			createListView();
			break;
			
		case R.id.menuFiltroGrupo:
			spItemGrupo.setVisibility(View.VISIBLE);
			edFiltro.setVisibility(View.INVISIBLE);
			spItemGrupo.setSelection(0);
			carregaTodosItens();
			createListView();
			
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	
	private class ListViewClikListner implements OnItemClickListener {
		@Override
		public void onItemClick(android.widget.AdapterView<?> arg0, View arg1,
				int position, long arg3) {		
			
			Intent it = new Intent(ItemView.this, ItemNovoView.class);
			it.putExtra("PRODUTO_SELECIONADO", lsItemVO.get(position));
			startActivityForResult(it,1);
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
		}

	
	}
	
	public void carregaTodosItens(){
		ItemDAO itemDAO = new ItemDAO(ItemView.this);
		lsItemVO = itemDAO.getAll();
	}
	
	private void createListView(){
		pedidoItemAdapter = new PedidoItemAdapter(ItemView.this, lsItemVO, new ArrayList<Integer>(), false);
		listView.setAdapter(pedidoItemAdapter);
	}
	
	public void populaSpItemGrupo(){
		ArrayList<GrupoProdutoVO> lsItemGrupo = new GrupoProdutoDAO(ItemView.this).getAll();
		GrupoProdutoVO itemGrupoVo =new GrupoProdutoVO(-1, "Selecione um Grupo");
		lsItemGrupo.add(0,itemGrupoVo);
		arrayAdapter = new ArrayAdapter<GrupoProdutoVO>(ItemView.this, R.layout.simple_spinner_item_custon, lsItemGrupo);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		spItemGrupo.setAdapter(arrayAdapter);
	}
	
	public void carregaItensByGrupo(Integer idGrupo){
		ItemModel itemBO = new ItemModel(ItemView.this);
		lsItemVO.clear();
		lsItemVO = itemBO.getAllByGrupo(idGrupo, tabPrecoSelecionada);
	}
	
	public void carregaItensByPrefixo(String prefixo){
		ItemModel itemBO = new ItemModel(ItemView.this);
		lsItemVO.clear();
		lsItemVO = itemBO.getAllByPrefixo(prefixo, tabPrecoSelecionada);
	}
	
	public void populaSpinnerTabPreco(){
		ArrayList<TabelaPrecoVO> lsTabPreco = new TabelaPrecoDAO(ItemView.this).getAll();		
		arrayAdapterTabPreco = new ArrayAdapter<TabelaPrecoVO>(ItemView.this, R.layout.simple_spinner_item_custon, lsTabPreco);
		arrayAdapterTabPreco.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		spinnerTabPreco.setAdapter(arrayAdapterTabPreco);
	}
	
	private final class SpIdGrupoListner implements OnItemSelectedListener{


		@Override
		public void onItemSelected(
				org.holoeverywhere.widget.AdapterView<?> parent, View view,
				int position, long id) {
			GrupoProdutoVO itemGrupoVO = (GrupoProdutoVO) spItemGrupo.getSelectedItem();
			if(itemGrupoVO.getIdGrupo() != -1){
				carregaItensByGrupo(itemGrupoVO.getIdGrupo());
				createListView();
			}
			
		}

		@Override
		public void onNothingSelected(
				org.holoeverywhere.widget.AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private final class BuscaProdutoKeyListner implements TextWatcher{		

		@Override
		public void afterTextChanged(Editable s) {
			if(s != null && s.toString().length() >=1){
				carregaItensByPrefixo(s.toString());
				createListView();				
				//pedidoItemAdapter.notifyDataSetChanged();
			}
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			if(after == 0){
				carregaTodosItens();
				createListView();
			}
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {}
		
	}
	
	/*private final class BuscaProdutoKeyListner implements TextWatcher{		

		@Override
		public void afterTextChanged(Editable s) {
			if(s != null && s.toString().length() >=3){
				carregaItensByPrefixo(s.toString());
				createListView();				
				//pedidoItemAdapter.notifyDataSetChanged();
			}
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			if((after < count && after == 2) || (start == 2 && count == 1)){
				carregaTodosItens();
				createListView();
			}
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {}
		
	}*/
	
	private class TabPrecoListner implements OnItemSelectedListener{
		@Override
		public void onItemSelected(
				org.holoeverywhere.widget.AdapterView<?> parent, View view,
				int position, long id) {
			TabelaPrecoVO tabPrecoVO = (TabelaPrecoVO) spinnerTabPreco.getSelectedItem();
			ItemModel itemBO = new ItemModel(ItemView.this);
			itemBO.aplicaTabPreco(tabPrecoVO, lsItemVO);
			pedidoItemAdapter.notifyDataSetChanged();
			
			
		}

		@Override
		public void onNothingSelected(
				org.holoeverywhere.widget.AdapterView<?> parent) {}
		
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if(resultCode == Activity.RESULT_OK){
			carregaTodosItens();
			createListView();
		}
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
