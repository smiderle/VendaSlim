package br.com.slim.venda.item;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.CheckBox;
import org.holoeverywhere.widget.Spinner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import br.com.slim.venda.R;
import br.com.slim.venda.analitico.EstoqueAnaliticoView;
import br.com.slim.venda.itemGrupo.GrupoProdutoDAO;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.widget.Alert;


//import android.widget.Spinner;

public class ItemNovoView extends Activity {
	
	ArrayAdapter<GrupoProdutoVO> arrayAdapter;
	Spinner spGrupo;
	EditText edItemId;
	EditText edDescricao;
	EditText edDescMax;
	EditText edPreco;
	EditText edUnidade;
	EditText edEstoque;
	EditText edPrecoCompra;
	CheckBox cbInativo;
	Button btnMovimentacao;
	boolean isEdition = false;
	
	ItemVO itemVO = new ItemVO();
	ItemModel itemBO;
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
        getSupportActionBar().setTitle("Item");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.item_novo_activity);
        
        bindingFilds();
        populaSpItemGrupo();
        addListners();
        
        btnMovimentacao.setVisibility(View.INVISIBLE);
        
        itemBO = new ItemModel(ItemNovoView.this);
        edItemId.setText(itemBO.getNextId()+"");
        ItemVO itemVO_ = (ItemVO) getIntent().getParcelableExtra("PRODUTO_SELECIONADO");
        if(itemVO_ != null){
        	this.itemVO = itemVO_;
        	this.isEdition = true;
        }
	}	
	
	private void addListners(){
		btnMovimentacao.setOnClickListener(new MovimentacaoListner());
	}
	
	private void bindingFilds(){
		 spGrupo = (Spinner) findViewById(R.id.spGrupo);
	        edItemId = (EditText) findViewById(R.id.edCodigo);
	        edPreco =(EditText) findViewById(R.id.edPreco);
	        edDescMax = (EditText) findViewById(R.id.edDescMax);
	        edDescricao = (EditText) findViewById(R.id.edDescricao);
	        edUnidade = (EditText) findViewById(R.id.edUnidade);	        
	        edEstoque= (EditText) findViewById(R.id.edEstoque);
	        cbInativo = (CheckBox) findViewById(R.id.cbInativo);
	        edPrecoCompra = (EditText) findViewById(R.id.edPrecoCompra);
	        btnMovimentacao = (Button) findViewById(R.id.btnMovimentacao);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		 menu.add(0, 1, 1, "Salvar")
         .setIcon(R.drawable.save);        
		 return super.onCreateOptionsMenu(menu);
		
	}
	
	public void populaSpItemGrupo(){
		ArrayList<GrupoProdutoVO> lsItemGrupo = new GrupoProdutoDAO(ItemNovoView.this).getAll();
		GrupoProdutoVO itemGrupoVo =new GrupoProdutoVO(-1, "Selecione um Grupo");
		lsItemGrupo.add(0,itemGrupoVo);
		arrayAdapter = new ArrayAdapter<GrupoProdutoVO>(ItemNovoView.this, android.R.layout.simple_spinner_item, lsItemGrupo);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spGrupo.setAdapter(arrayAdapter);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			break;
		case 1:
			new Alert().showDialog(this, "", "Disponivel somente na versão PRÓ", "OK", null);
			//Descomentar para permitir editar produto
			/*valorizaAtributos();
			long inserts = itemBO.save(itemVO, isEdition);
			if(inserts > 0)
				Toast.makeText(ItemNovoView.this, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();			
			setResult(RESULT_OK, null);
			finish();*/
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void valorizaAtributos(){		
		GrupoProdutoVO itemGrupoVo =  (GrupoProdutoVO) spGrupo.getSelectedItem();
		itemVO.setDescMax(Convert.toDouble(edDescMax.getText().toString()));
		itemVO.setDescricao(edDescricao.getText().toString());
		itemVO.setEstoque(Convert.toDouble(edEstoque.getText().toString()));
		itemVO.setIdEmpresa(UsuarioVO.idEmpresa);
		itemVO.setInativo(cbInativo.isChecked());
		itemVO.setItemGrupo(itemGrupoVo);
		itemVO.setIdItem(Integer.parseInt(edItemId.getText().toString().trim()));
		itemVO.setPreco(Convert.toDouble(edPreco.getText().toString()));	
		itemVO.setInativo(cbInativo.isChecked());
		itemVO.setPrecoCompra(Convert.toDouble(edPrecoCompra.getText().toString()));
	}
	
	public void valorizaCampos(){
		edDescMax.setText(itemVO.getDescMax()+"");
		edDescricao.setText(itemVO.getDescricao());
		edEstoque.setText(itemVO.getEstoque()+"");
		cbInativo.setChecked(itemVO.isInativo());
		int position = arrayAdapter.getPosition(itemVO.getItemGrupo());
		spGrupo.setSelection(position);
		edPreco.setText(itemVO.getPreco()+"");		
	}
	
	@Override
	protected void onResume() {	
		super.onResume();
		if(itemVO.getIdItem() != 0){
			edDescricao.setText(itemVO.getDescricao());
			edPreco.setText(itemVO.getPreco()+"");
			edDescMax.setText(itemVO.getDescMax()+"");
			//edUnidade.setText(itemVO.getUnidade);
			edEstoque.setText(itemVO.getEstoque()+"");
			edItemId.setText(itemVO.getIdItem()+"");
			cbInativo.setChecked(itemVO.isInativo());
			edPrecoCompra.setText(itemVO.getPrecoCompra()+"");
			
		}		
	}
	
	private class MovimentacaoListner implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			Intent it = new Intent(ItemNovoView.this, EstoqueAnaliticoView.class);
			it.putExtra("ItemVO", itemVO);
			startActivity(it);
			overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
		}
		
	}
	
	@Override
	protected void onDestroy() {	
		super.onDestroy();
		itemBO.closeConnection();
	}
	
	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}

}
