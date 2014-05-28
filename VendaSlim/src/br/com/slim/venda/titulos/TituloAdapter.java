package br.com.slim.venda.titulos;

import java.util.ArrayList;
import br.com.slim.venda.R;

import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.util.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TituloAdapter extends BaseAdapter{
	
	private LayoutInflater minflater;
	ArrayList<ClienteVO> lsClienteVO;
	
	public TituloAdapter(Context context, ArrayList<ClienteVO> lsClienteVO) {
		this.minflater = LayoutInflater.from(context);
		this.lsClienteVO = lsClienteVO;
	}

	@Override
	public int getCount() {
		return lsClienteVO.size();
	}

	@Override
	public Object getItem(int position) {		
		return lsClienteVO.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ClienteVO clienteVO = lsClienteVO.get(position);
		view = minflater.inflate(R.layout.titulo_total_row, null) ;
		((TextView)view.findViewById(R.id.tvClienteNome)).setText(clienteVO.getIdCliente() +" - "+clienteVO.getNome());
		((TextView)view.findViewById(R.id.tvValorTotal)).setText(Util.arredondaDouble(clienteVO.getValorTitulos())+"");
		return view;
	}
	
}
