package br.com.slim.venda.pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.Spinner;
import org.holoeverywhere.widget.Toast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.cliente.ClienteView;
import br.com.slim.venda.pedidoItem.PedidoItemFragment;
import br.com.slim.venda.tabelaPreco.TabelaPrecoDAO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.util.Convert;
import org.holoeverywhere.widget.AdapterView.OnItemSelectedListener;
//import android.widget.Spinner;

@SuppressLint("ValidFragment")
public class CabecalhoFragments extends Fragment  {
	
	//http://developer.android.com/training/basics/fragments/communicating.html#Deliver
	
		
	public CabecalhoFragments() {}
	
	View view;
	Spinner spinnerTabPreco;
	Spinner spinnerFormaPgto;
	ImageButton btnFindCliente;
	EditText edClienteNome;
	EditText edObservacao;
	EditText edIdPedido;
	EditText edDtEmissao;
	EditText edIdCliente;
	PedidoVO pedidoVO;
	
	private ArrayAdapter<TabelaPrecoVO> arrayAdapter;
		
	@Override
	public View onCreateView(org.holoeverywhere.LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		Bundle bundle = this.getArguments();
		this.pedidoVO = bundle.getParcelable("PEDIDO");
		
		view =  inflater.inflate(R.layout.pedido_cabecalho_activity, container, false);
		edClienteNome = (EditText) view.findViewById(R.id.edClienteNome);
		spinnerTabPreco = (Spinner) view.findViewById(R.id.spTabPreco);
		spinnerFormaPgto = (Spinner) view.findViewById(R.id.spFormaPgto);
		btnFindCliente = (ImageButton) view.findViewById(R.id.btnFindCliente);
		edObservacao = (EditText) view.findViewById(R.id.edObservacao);
		edDtEmissao = (EditText) view.findViewById(R.id.edDtEmissao);
		edIdPedido = (EditText) view.findViewById(R.id.edIdPedido);
		edIdCliente = (EditText) view.findViewById(R.id.edIdCliente);
		btnFindCliente.setOnClickListener(new FindCliente());
		spinnerTabPreco.setOnItemSelectedListener(new TabPrecoListner());
		//pedidoVO.setDtEmisao(new Date().getTime());
		if(pedidoVO.getDtEmisao() == 0)
			pedidoVO.setDtEmisao(new GregorianCalendar().getTimeInMillis());
		
		edDtEmissao.setText(Convert.toDateTimeStr(pedidoVO.getDtEmisao()));
		edDtEmissao.setEnabled(false);
		edIdPedido.setEnabled(false);
		edIdPedido.setText(pedidoVO.getIdPedido()+"");
		populaSpinnerTabPreco();
		return view;
	}
	
	public void populaSpinnerTabPreco(){
		ArrayList<TabelaPrecoVO> lsTabPreco = new TabelaPrecoDAO(view.getContext()).getAll();		
		arrayAdapter = new ArrayAdapter<TabelaPrecoVO>(view.getContext(), R.layout.simple_spinner_item_custon, lsTabPreco);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		spinnerTabPreco.setAdapter(arrayAdapter);
	}
	
	private void setTabelaCliente(){
		if(pedidoVO.getClienteVO() != null && pedidoVO.getClienteVO().getTabPrecovo() != null){
			spinnerTabPreco.setSelection(arrayAdapter.getPosition(pedidoVO.getClienteVO().getTabPrecovo()));
		}
	}
	
	private class TabPrecoListner implements OnItemSelectedListener {	
		
		@Override
		public void onItemSelected(
				org.holoeverywhere.widget.AdapterView<?> parent, View view,
				int position, long id) {
			pedidoVO.setTabelaPrecoVO((TabelaPrecoVO) spinnerTabPreco.getSelectedItem());
			PedidoItemFragment pedidoItemFragment = getPedidoItemFragment();
			pedidoItemFragment.updateFragment();
			
		}

		@Override
		public void onNothingSelected(
				org.holoeverywhere.widget.AdapterView<?> parent) {		
		}		
	}
	
	
	private class FindCliente implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			Intent it = new Intent(getActivity(), ClienteView.class);
			it.putExtra("CHAMADOR",ClienteView.CHAMADOR_PEDIDO);
			startActivityForResult(it, 1);
			getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
		}
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if(resultCode == Activity.RESULT_OK){
			ClienteVO clienteSelecionado = (ClienteVO) intent.getParcelableExtra("CLIENTE_SELECIONADO");
			pedidoVO.setClienteVO(clienteSelecionado);
			edClienteNome.setText(clienteSelecionado.getNome());
			setTabelaCliente();
			
		}
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
	
	public void valorizaAtributos(){
		pedidoVO.setTabelaPrecoVO((TabelaPrecoVO)spinnerTabPreco.getSelectedItem());
		pedidoVO.setObservacao(edObservacao.getText().toString());
		//pedidoVO.setDtEmisao(Convert.dateTimeToLong(edDtEmissao.getText().toString()));
	}
	
	public void valorizaCampos(){
		if(pedidoVO.getClienteVO() != null){
			edClienteNome.setText(pedidoVO.getClienteVO().getNome());
			edIdCliente.setText(pedidoVO.getClienteVO().getIdCliente()+"");
		}
		if(pedidoVO.getObservacao() != null){
			edObservacao.setText(pedidoVO.getObservacao());
		}
		if(pedidoVO.isEdition()){
			edDtEmissao.setText(Convert.toDateTimeStr(pedidoVO.getDtEmisao()));
		} else {
			edDtEmissao.setText(Convert.toDateTimeStr(new Date().getTime()));
		}
		
		int position = arrayAdapter.getPosition(pedidoVO.getTabelaPrecoVO());
		spinnerTabPreco.setSelection(position, true);
	}
	
	private PedidoItemFragment getPedidoItemFragment(){
		List<android.support.v4.app.Fragment> a = (List<android.support.v4.app.Fragment>) getParentFragment().getChildFragmentManager().getFragments();
		for (android.support.v4.app.Fragment fragment : a) {
			if(fragment != null){
				if(fragment.getClass().equals(PedidoItemFragment.class)){
					return (PedidoItemFragment) fragment;					
				}
			}
		}	
		return null;
	}
}
