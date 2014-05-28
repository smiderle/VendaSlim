package br.com.slim.venda.analitico;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;
import org.holoeverywhere.widget.AdapterView;
import org.holoeverywhere.widget.AdapterView.OnItemSelectedListener;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.NumberPicker;
import org.holoeverywhere.widget.Spinner;
import org.holoeverywhere.widget.TextView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import br.com.slim.venda.R;
import br.com.slim.venda.item.ItemDAO;
import br.com.slim.venda.item.ItemVO;

@SuppressLint("ValidFragment")
public class EstoqueAnaliticoView extends Activity{

	TextView tvDescricao;
	ListView listView;
	ArrayList<EstoqueAnaliticoVO> lsEstoqueAnalitico;
	EstoqueAnaliticoModel estoqueAnaliticoModel;
	ItemVO itemVO;
	Spinner spFiltro;	
	Button btnLancamento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*itemVO = new ItemVO();
		itemVO.setIdItem(2);
		itemVO.setIdEmpresa(UsuarioVO.idEmpresa);*/
		
		itemVO = getIntent().getParcelableExtra("ItemVO");
		
		estoqueAnaliticoModel = new EstoqueAnaliticoModel(this);
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("  Estoque Analitico");
		getSupportActionBar().setHomeButtonEnabled(true);	       	       
		setContentView(R.layout.estoque_analitico);

		bindingFilds();
		addListeners();
		tvDescricao.setText(itemVO.getDescricao());

		carregaAnalitico();
		carregaListView();
	}
	private void addListeners(){
		spFiltro.setOnItemSelectedListener(new FiltroListner());		
		btnLancamento.setOnClickListener(new LancamentoListner());
	}
	
	private void bindingFilds(){
		tvDescricao  = (TextView) findViewById(R.id.tvDescricaoProduto);
		listView = (ListView) findViewById(R.id.lvItemAnalitico);
		spFiltro = (Spinner) findViewById(R.id.spFiltro);
		btnLancamento = (Button) findViewById(R.id.btnEntradaSaida);
		
	}
	
	
	private void carregaAnalitico(){
		lsEstoqueAnalitico = estoqueAnaliticoModel.getAllByItem(itemVO);	
	}
	
	private void carregaAnaliticoTipo(int tipoMov){
		if(lsEstoqueAnalitico != null)
			lsEstoqueAnalitico.clear();
		lsEstoqueAnalitico = estoqueAnaliticoModel.getAllByItemTipo(itemVO, tipoMov);	
	}
	
	

	public void carregaListView(){
		EstoqueAnaliticoAdapter analiticoAdapter = new EstoqueAnaliticoAdapter(this, lsEstoqueAnalitico);
		listView.setAdapter(analiticoAdapter);
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
	
	private class FiltroListner implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			if(position == 0){
				carregaAnalitico();
			} else {
				carregaAnaliticoTipo(position);
			}
			
			carregaListView();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

			
		}
		
	}
	
	private class LancamentoListner implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			PickersNumberEntryOut pkNumber = new PickersNumberEntryOut();
			pkNumber.show(EstoqueAnaliticoView.this);
		}
		
	}
	
	

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {	
		super.onWindowFocusChanged(hasFocus);
		if(hasFocus){
			listView.onWindowFocusChanged(true);
			listView.invalidate();
			listView.invalidateViews();
		}
		
	}
	
	public class PickersNumberEntryOut extends DialogFragment{
		 NumberPicker numberPicker = null;
		private View makeNumberPicker() {
	        View view = getLayoutInflater().inflate(R.layout.picker_number_picker_demo);
	        numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
	        numberPicker.setMinValue(0);
	        numberPicker.setMaxValue(100);
	        numberPicker.setValue(1);
	        
	        return view;
	    }
		
		@Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        AlertDialog.Builder builder = new AlertDialog.Builder(getSupportActivity(), getTheme());
	        builder.setView(makeNumberPicker());
	        builder.setNegativeButton("Saida", new SaidaListner());
	        builder.setPositiveButton("Entrada", new EntradaListner());
	        return builder.create();
	    }
		
		private class SaidaListner implements DialogInterface.OnClickListener{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				estoqueAnaliticoModel.movimentaAnalitico(itemVO, EstoqueAnaliticoVO.ANALITICO_SAIDA_MANUAL,numberPicker.getValue() );
				carregaAnaliticoTipo(EstoqueAnaliticoVO.ANALITICO_SAIDA_MANUAL);
				carregaListView();
				ItemDAO itemDAO = new ItemDAO(getApplicationContext());
				itemDAO.updateEstoque(itemVO.getIdItem(), -numberPicker.getValue());
			}
		}
		
		private class EntradaListner implements DialogInterface.OnClickListener{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				estoqueAnaliticoModel.movimentaAnalitico(itemVO, EstoqueAnaliticoVO.ANALITICO_ENTRADA_MANUAL, numberPicker.getValue());
				carregaAnaliticoTipo(EstoqueAnaliticoVO.ANALITICO_ENTRADA_MANUAL);
				carregaListView();
				ItemDAO itemDAO = new ItemDAO(getApplicationContext());
				itemDAO.updateEstoque(itemVO.getIdItem(), numberPicker.getValue());
			}
		}
		
	}
	
	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
	
}
