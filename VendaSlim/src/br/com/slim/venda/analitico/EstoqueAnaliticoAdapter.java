package br.com.slim.venda.analitico;

import java.util.ArrayList;


import br.com.slim.venda.R;
import br.com.slim.venda.util.Convert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EstoqueAnaliticoAdapter extends BaseAdapter{
	
	
	private ArrayList<EstoqueAnaliticoVO> lsAnalitico;
	LayoutInflater minflater;
	public EstoqueAnaliticoAdapter(Context context, ArrayList<EstoqueAnaliticoVO> lsAnalitico) {
		this.minflater = LayoutInflater.from(context);
		this.lsAnalitico = lsAnalitico;
	}

	@Override
	public int getCount() {
		return lsAnalitico.size();
	}

	@Override
	public Object getItem(int position) {
		return lsAnalitico.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		EstoqueAnaliticoVO analitico = lsAnalitico.get(position);
		view = minflater.inflate(R.layout.estoque_analitico_row, null);
		
		
		double quantidade = analitico.getQuantidade();
		TextView tvQtd = (TextView) view.findViewById(R.id.tvQtdMovEd);
				
		if(analitico.isEntry()){
			tvQtd.setTextColor(view.getResources().getColor(R.color.blue));
			((TextView) view.findViewById(R.id.tvEntradaSaida)).setText("Entrada");
		} else {
			quantidade *= -1;
			tvQtd.setTextColor(view.getResources().getColor(R.color.red));
			((TextView) view.findViewById(R.id.tvEntradaSaida)).setText("Saida");			
		}
		
		tvQtd.setText(quantidade+"");
		
		
		((TextView) view.findViewById(R.id.tvDtMovEd)).setText(Convert.toDateStr(analitico.getDateMov()));
		((TextView) view.findViewById(R.id.tvTipoMov)).setText(analitico.getTipoMovDescricao());
		
		return view;
	}

}
