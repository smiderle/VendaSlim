package br.com.slim.venda.pedidoItem;

import java.util.ArrayList;

import br.com.slim.venda.R;
import br.com.slim.venda.util.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PedidoItemCestaAdapter extends BaseAdapter{

	private LayoutInflater minflater;
	private ArrayList<PedidoItemVO> lsPedidoItemVO;
	
	public PedidoItemCestaAdapter(Context context, ArrayList<PedidoItemVO> lsPedidoItemVO) {
		minflater = LayoutInflater.from(context);
		this.lsPedidoItemVO = lsPedidoItemVO;
	}
	
	@Override
	public int getCount() {		
		return lsPedidoItemVO.size();
	}

	@Override
	public Object getItem(int position) {
		return lsPedidoItemVO.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		PedidoItemVO pedidoItemVO = lsPedidoItemVO.get(position);
		view = minflater.inflate(R.layout.pedido_item_cesta_row, null);
		((TextView)view.findViewById(R.id.tvDescricao)).setText(pedidoItemVO.getItemVO().getDescricao());
		((TextView)view.findViewById(R.id.tvQtdEd)).setText(String.valueOf(pedidoItemVO.getQuantidade()));
		((TextView)view.findViewById(R.id.tvCodigoEd)).setText(pedidoItemVO.getItemVO().getIdItem()+"");
		((TextView)view.findViewById(R.id.tvPrecoVendaEd)).setText(Util.arredondaDoubleToString(pedidoItemVO.getPrecoVenda()));
		((TextView)view.findViewById(R.id.tvDescontoEd)).setText(Util.arredondaDoubleToString(pedidoItemVO.getDesconto()));
		return view;
	}

}
