package br.com.slim.venda.pedido;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.TextView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import br.com.slim.venda.R;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;
import br.com.slim.venda.util.Util;


public class PedidoParcelasAdapter extends BaseAdapter {
	 ArrayList<PedidoPgtoVO> lsPedidoPgto;
	 LayoutInflater minflater;
	 
	public PedidoParcelasAdapter(Context context, ArrayList<PedidoPgtoVO> lsPedidoPgto) {
		minflater = LayoutInflater.from(context);
		this.lsPedidoPgto = lsPedidoPgto;
	}

	@Override
	public int getCount() {		
		return lsPedidoPgto.size();
	}

	@Override
	public Object getItem(int position) {
		return lsPedidoPgto.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {		
		PedidoPgtoVO pedidoPgto = lsPedidoPgto.get(position);
		view = minflater.inflate(R.layout.pedido_pagamento_parcelas_row, null);
		String nroParcela = pedidoPgto.getIdSequencia() +"/"+ pedidoPgto.getParcelamentoVO().getNroParcela();
		((TextView) view.findViewById(R.id.tvPareclaNroEd)).setText(nroParcela);
		((TextView) view.findViewById(R.id.tvValorParcela)).setText(Util.arredondaDoubleToString(pedidoPgto.getValorParcela()));
		((TextView) view.findViewById(R.id.tvDtVencimentoEd)).setText(new SimpleDateFormat("dd/MM/yyyy").format(pedidoPgto.getDtVencimento()));
		return view;
	}

}
