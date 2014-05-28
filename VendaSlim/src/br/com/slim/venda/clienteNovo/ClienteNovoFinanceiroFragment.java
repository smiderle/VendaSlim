package br.com.slim.venda.clienteNovo;

import java.util.ArrayList;

import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.Spinner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.parcelamento.ParcelamentoDAO;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoDAO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.util.Convert;
//import android.widget.Spinner;

@SuppressLint("ValidFragment")
public class ClienteNovoFinanceiroFragment extends Fragment{
	View view;
	Spinner spinnerTabPreco;
	Spinner spinnerFormaParcel;
	Spinner spinnerFormaPagamento;
	EditText edLimiteCredito;
	ArrayList<TabelaPrecoVO> lsTabPreco;
	
	ArrayList<ParcelamentoVO> lsParcelamento;
	ClienteVO clienteVO;
	
	public ClienteNovoFinanceiroFragment() {
		
	}
	
	
	@Override
	public View onCreateView(org.holoeverywhere.LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {		
		
		view = inflater.inflate(R.layout.cliente_novo_financeiro, container, false);
		Bundle bundle = this.getArguments();
		this.clienteVO = bundle.getParcelable("CLIENTE");
		
		spinnerTabPreco = (Spinner) view.findViewById(R.id.spTabPreco);
		spinnerFormaParcel = (Spinner) view.findViewById(R.id.spFormaParcel);
		spinnerFormaPagamento = (Spinner) view.findViewById(R.id.spFormaPgto);
		edLimiteCredito = (EditText) view.findViewById(R.id.edLimiteCredito);
		populaSpinnerTabPreco();
		populaSpParcelamento();
		return view;
	}
	
	
	public void populaSpinnerTabPreco(){
		lsTabPreco = new TabelaPrecoDAO(view.getContext()).getAll();		
		ArrayAdapter<TabelaPrecoVO> arrayAdapter = new ArrayAdapter<TabelaPrecoVO>(view.getContext(), R.layout.simple_spinner_item_custon, lsTabPreco);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		spinnerTabPreco.setAdapter(arrayAdapter);
	}
	
	public void populaSpParcelamento(){
		lsParcelamento = new ParcelamentoDAO(view.getContext()).getAll();
		ArrayAdapter<ParcelamentoVO> arrayAdapter = new ArrayAdapter<ParcelamentoVO>(view.getContext(),  R.layout.simple_spinner_item_custon, lsParcelamento);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		spinnerFormaParcel.setAdapter(arrayAdapter);
	}
	
	
	private void valorizaCampos(){
		if(clienteVO.getParcelamentoVO() != null){
			int indexof = lsParcelamento.indexOf(clienteVO.getParcelamentoVO());
			if(indexof > -1){
				spinnerFormaParcel.setSelection(indexof);				
			}
		}
		
		if(clienteVO.getTabPrecovo() != null){
			int indexof = lsTabPreco.indexOf(clienteVO.getTabPrecovo());
			if(indexof > -1){
				spinnerTabPreco.setSelection(indexof);				
			}
		}
		spinnerFormaPagamento.setSelection(clienteVO.getIdFormaPagamento());
		edLimiteCredito.setText(clienteVO.getLimiteCredito()+"");
	}
	
	
	private void valorizaAtributos(){
		clienteVO.setTabPrecovo((TabelaPrecoVO)spinnerTabPreco.getSelectedItem());
		clienteVO.setParcelamentoVO((ParcelamentoVO) spinnerFormaParcel.getSelectedItem());
		clienteVO.setIdFormaPagamento(spinnerFormaPagamento.getSelectedItemPosition());
		clienteVO.setLimiteCredito(Convert.toDouble(edLimiteCredito.getText().toString()));
	}
	
	@Override
	public void onPause() {
		valorizaAtributos();
		super.onPause();
	}
	
	@Override
	public void onResume() {
		valorizaCampos();
		super.onResume();
	}

}
