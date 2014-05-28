package br.com.slim.venda.pedido;

import java.util.ArrayList;
import java.util.Calendar;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.AdapterView;
import org.holoeverywhere.widget.AdapterView.OnItemSelectedListener;
import org.holoeverywhere.widget.ArrayAdapter;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Spinner;
import org.holoeverywhere.widget.TextView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import br.com.slim.venda.R;
import br.com.slim.venda.parcelamento.ParcelamentoDAO;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.pedidoItem.PedidoItemVO;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.util.Util;


@SuppressLint("ValidFragment")
public class PedidoPgtoFragments extends Fragment {
	
	ListView listView;
	View view;
	Spinner spinnerFormaPgto; 
	Spinner spinnerFormaParcelamento;
	TextView tvTotal;
	Button btnVisualizarParcela;
	PedidoParcelasAdapter pedidoParcelasAdapter;
	ArrayAdapter<ParcelamentoVO> parcelamantoAdapter;

	PedidoVO pedidoVO;
	public PedidoPgtoFragments(PedidoVO pedidoVO) {
		this.pedidoVO = pedidoVO;
	}
	
	public PedidoPgtoFragments() {
		
	}
	
	private void setFormaPgtoCliente(){
		if(pedidoVO.getClienteVO() != null && pedidoVO.getClienteVO().getParcelamentoVO() != null && pedidoVO.getParcelamentoVO() == null){
			spinnerFormaParcelamento.setSelection(
					parcelamantoAdapter.getPosition(pedidoVO.getClienteVO().getParcelamentoVO()));
			pedidoVO.setParcelamentoVO(pedidoVO.getClienteVO().getParcelamentoVO());
			}
		//if(pedidoVO.getClienteVO().getIdFormaPagamento() != null){
			spinnerFormaPgto.setSelection(pedidoVO.getClienteVO().getIdFormaPagamento());
		//}		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved) {
		
		Bundle bundle = this.getArguments();
		this.pedidoVO = bundle.getParcelable("PEDIDO");
		
		if(pedidoVO == null)
			pedidoVO = new PedidoVO();
		
		
		view = inflater.inflate(R.layout.pedido_pagamento_activity, group, false);
		spinnerFormaParcelamento = (Spinner) view.findViewById(R.id.spParcelamento);
		spinnerFormaPgto = (Spinner) view.findViewById(R.id.spFormaPgto);
		tvTotal = (TextView) view.findViewById(R.id.tvTotalEd);		
		listView = (ListView) view.findViewById(R.id.listParcelas);		
		btnVisualizarParcela = (Button) view.findViewById(R.id.btnVisualizarParcela);
		spinnerFormaParcelamento.setOnItemSelectedListener(new FormaPagamentoListner());
		populaSpParcelamento();
		btnVisualizarParcela.setOnClickListener(new VisualizarParcelaListner());
		
		PedidoModel pedidoModel = new PedidoModel(view.getContext());
		double somaTotal = pedidoModel.somaTotal(pedidoVO);		
		tvTotal.setText(Util.arredondaDoubleToString(somaTotal));
		valorizaCampos();
		if(pedidoVO.getParcelamentoVO() == null || pedidoVO.getParcelamentoVO().getIdParcelamento() == null || pedidoVO.getParcelamentoVO().getIdParcelamento() == 0){
			if(pedidoVO.getClienteVO() != null)
				pedidoVO.setParcelamentoVO(pedidoVO.getClienteVO().getParcelamentoVO());
		}
		
		return view;
	}
	
	public void populaSpParcelamento(){
		ArrayList<ParcelamentoVO> lsParcelamento = new ParcelamentoDAO(view.getContext()).getAll();
		parcelamantoAdapter = new ArrayAdapter<ParcelamentoVO>(view.getContext(), R.layout.simple_spinner_item_custon, lsParcelamento);
		parcelamantoAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		spinnerFormaParcelamento.setAdapter(parcelamantoAdapter);
	}
	
	
	public void createListView(){
		pedidoParcelasAdapter = new PedidoParcelasAdapter(view.getContext(), pedidoVO.getPedidosPgtoVO());
		listView.setAdapter(pedidoParcelasAdapter);
	}

	
	/*private double somaTotal(){
		double total = 0.0;
		if(pedidoVO.getPedidoItemVO() != null){
			for(PedidoItemVO pedidoItemVO : pedidoVO.getPedidoItemVO()){
				total += pedidoItemVO.getPrecoVenda() * pedidoItemVO.getQuantidade();
			}
		}
		return Util.arredondaDouble(total);
	}*/
	
	//Criado provisoriamente para visualizar o parcelamento no click do botão.
	private class VisualizarParcelaListner implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			geraParcelas();
		}
	}
	
	private void geraParcelas(){
		PedidoModel pedidoModel = new PedidoModel(view.getContext());
		double somaTotal = pedidoModel.somaTotal(pedidoVO);
		if(somaTotal > 0){				
			ParcelamentoVO parcelamentoVO = (ParcelamentoVO) spinnerFormaParcelamento.getSelectedItem();			
			pedidoModel.geraPagamento(pedidoVO, parcelamentoVO);
			}
			createListView();
		
	}
	
	
	
	private class FormaPagamentoListner implements OnItemSelectedListener{
		@Override
		public void onItemSelected(
				AdapterView<?> parent, View view,
				int position, long id) {

			PedidoModel pedidoModel = new PedidoModel(view.getContext());
			double somaTotal = pedidoModel.somaTotal(pedidoVO);
			ParcelamentoVO parcelamentoVO = (ParcelamentoVO) spinnerFormaParcelamento.getSelectedItem();	
			if(somaTotal > 0){				
				//ParcelamentoVO parcelamentoVO = (ParcelamentoVO) spinnerFormaParcelamento.getSelectedItem();			
				pedidoModel.geraPagamento(pedidoVO, parcelamentoVO);
				}
				createListView();
				valorizaAtributos();
			}

		@Override
		public void onNothingSelected(
				AdapterView<?> parent) {}
	}
	
	public void valorizaCampos(){
		if(pedidoVO != null && pedidoVO.getParcelamentoVO() != null ){
			int position = parcelamantoAdapter.getPosition(pedidoVO.getParcelamentoVO());
			spinnerFormaParcelamento.setSelection(position, true);
		}
		if(pedidoVO.getIdFormaPagamento() != 0)
			spinnerFormaPgto.setSelection(pedidoVO.getIdFormaPagamento(), true);
	}
	
	public void valorizaAtributos(){
		int formaPgto = (int)spinnerFormaPgto.getSelectedItemId();
		pedidoVO.getClienteVO().setIdFormaPagamento(formaPgto);
		pedidoVO.setIdFormaPagamento(formaPgto);
		pedidoVO.setParcelamentoVO((ParcelamentoVO)spinnerFormaParcelamento.getSelectedItem());
		pedidoVO.getClienteVO().setParcelamentoVO((ParcelamentoVO)spinnerFormaParcelamento.getSelectedItem());
	}
	
	@Override
	public void onPause() {
		valorizaAtributos();
		super.onPause();
	}
	
	@Override
	public void onResume() {
		valorizaCampos();
		setFormaPgtoCliente();
		super.onResume();
	}
	
	
	public void updateFragment(){
		PedidoModel pedidoModel = new PedidoModel(view.getContext());
		double somaTotal = pedidoModel.somaTotal(pedidoVO);		
		tvTotal.setText(Util.arredondaDoubleToString(somaTotal));
		geraParcelas();
	}
}
