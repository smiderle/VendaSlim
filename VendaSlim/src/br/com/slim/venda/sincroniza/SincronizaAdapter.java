package br.com.slim.venda.sincroniza;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.slim.venda.R;
import br.com.slim.venda.R.color;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.util.Util;

public class SincronizaAdapter extends BaseAdapter{
	
	private LayoutInflater myInflate;
	
	private List<PedidoVO> pedidos;
	public SincronizaAdapter(Context context, List<PedidoVO> pedidos) {
		myInflate = LayoutInflater.from(context);
		this.pedidos = pedidos;
	}

	@Override
	public int getCount() {		
		return pedidos.size();
	}

	@Override
	public Object getItem(int position) {
		return pedidos.get(position);		
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view , ViewGroup parent) {
		PedidoVO pedidoVO = pedidos.get(position);
		
		if(pedidoVO.isSection()){
			view = myInflate.inflate(R.layout.sincroniza_pedidos_section, null);
			((TextView) view.findViewById(R.id.tvDataPedido)).setText(Util.getDataPorExtenso(pedidoVO.getDtEmisao()));
		} else {
			view = myInflate.inflate(R.layout.sincroniza_pedidos_row, null);
			((TextView) view.findViewById(R.id.tvIdPedido)).setText(pedidoVO.getIdPedido()+"");
			((TextView) view.findViewById(R.id.tvClienteEd)).setText(pedidoVO.getClienteVO().getIdCliente()+" - "+ pedidoVO.getClienteVO().getNome());
			((TextView) view.findViewById(R.id.tvHora)).setText(new SimpleDateFormat("HH:mm").format(new Date(pedidoVO.getDtEmisao())));
			((TextView) view.findViewById(R.id.tvValorTotalEd)).setText(Util.arredondaDoubleToString(pedidoVO.getTotalLiquido()));
			
			if(pedidoVO.isSincronizado())
				((View) view.findViewById(R.id.statusSincronizado)).setBackgroundColor(view.getResources().getColor(R.color.status_sincronizacao_concluida));
		}
		
		
		return view;
	}

}
