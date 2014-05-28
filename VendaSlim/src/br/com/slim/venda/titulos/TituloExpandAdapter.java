package br.com.slim.venda.titulos;

import java.util.ArrayList;
import java.util.Date;

import br.com.slim.venda.R;
import br.com.slim.venda.parcelamento.ParcelamentoVO.Parcela;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedido.PedidoView;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.util.Util;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class TituloExpandAdapter extends BaseExpandableListAdapter {

	private Context context;
	ArrayList<PedidoVO> lsPedido;

	public TituloExpandAdapter(Context context, ArrayList<PedidoVO> lsPedido) {
		this.lsPedido = lsPedido;
		this.context = context;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		  ArrayList<PedidoPgtoVO> lsPedidoPgtoVO = lsPedido.get(groupPosition).getPedidosPgtoVO();		  
		          return lsPedidoPgtoVO.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {

		PedidoPgtoVO pedidoPgtoVO = (PedidoPgtoVO) getChild(groupPosition, childPosition);

		if (view == null) {
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);		
			view = infalInflater.inflate(R.layout.titulo_pedido_parcela_row, null);		
		}

		((TextView) view.findViewById(R.id.tvNrParcela)).setText(pedidoPgtoVO.getIdSequencia()+"");
		((TextView) view.findViewById(R.id.tvValorParcelaEd)).setText(pedidoPgtoVO.getValorParcela()+"");
		((TextView) view.findViewById(R.id.tvVencimentoEd)).setText(Convert.toDateStr(pedidoPgtoVO.getDtVencimento()));

		TextView edStatus = ((TextView) view.findViewById(R.id.tvStatusParcela));
		
		if(pedidoPgtoVO.getParcelaPaga() == PedidoPgtoVO.PAGAMENTO_TOTAL){
			edStatus.setText("Paga");
			edStatus.setTextColor(view.getResources().getColor(R.color.green));
		} else if(pedidoPgtoVO.getParcelaPaga() == PedidoPgtoVO.PAGAMENTO_PENDENTE
				&& pedidoPgtoVO.getDtVencimento() < new Date().getTime()){
			edStatus.setText("Atrazada");
			edStatus.setTextColor(view.getResources().getColor(R.color.red));
		} else {
			edStatus.setText("Pendente");
			edStatus.setTextColor(view.getResources().getColor(R.color.yellow));
		}
		
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		ArrayList<PedidoPgtoVO> lsPedidoPgto = lsPedido.get(groupPosition).getPedidosPgtoVO();
		return lsPedidoPgto.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return lsPedido.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return lsPedido.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View view, ViewGroup parent) {
		PedidoVO pedidoVO =(PedidoVO) getGroup(groupPosition);

		if (view == null) {
			LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.titulo_pedido_row, null);
		}

		((TextView) view.findViewById(R.id.tvIdPedidoEd)).setText(pedidoVO.getIdPedido()+"");
		((TextView) view.findViewById(R.id.dtEmissaoEd)).setText(Convert.toDateStr(pedidoVO.getDtEmisao()));
		((TextView) view.findViewById(R.id.tvParcelamentoEd)).setText(pedidoVO.getParcelamentoVO().getDescricao());
		((TextView) view.findViewById(R.id.tvValorTotal)).setText(Util.arredondaDouble(pedidoVO.getTotalLiquido())+"");

		return view;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
