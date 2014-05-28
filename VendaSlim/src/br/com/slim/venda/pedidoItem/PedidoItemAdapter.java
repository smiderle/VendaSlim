package br.com.slim.venda.pedidoItem;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.slim.venda.R;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.util.Util;

public class PedidoItemAdapter extends BaseAdapter{

	private LayoutInflater minflater;
	private ArrayList<ItemVO> lsItemVO;
	ArrayList<Integer> lsItensIdsAdicionados;	
	private boolean mostarPlus;
	public PedidoItemAdapter(Context context, ArrayList<ItemVO> lsItemVO,  ArrayList<Integer> lsItensIdsAdicionados, boolean mostrarPlus) {
		minflater = LayoutInflater.from(context);
		this.lsItemVO = lsItemVO;
		this.lsItensIdsAdicionados = lsItensIdsAdicionados;
		this.mostarPlus = mostrarPlus;
	}
	
	@Override
	public int getCount() {		
		return lsItemVO.size();
	}

	@Override
	public Object getItem(int position) {		
		return lsItemVO.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
//
	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		ItemVO itemVO = lsItemVO.get(position);
		view = minflater.inflate(R.layout.pedido_item_row, null);		
		((TextView)view.findViewById(R.id.tvDescricao)).setText(itemVO.getDescricao());
		TextView tvEstoque = (TextView)view.findViewById(R.id.tvEstoqueEd);		
		((TextView)view.findViewById(R.id.tvCodigoEd)).setText(itemVO.getIdItem()+"");
		((TextView)view.findViewById(R.id.tvPrecoEd)).setText(Util.arredondaDoubleToString(itemVO.getPrecoTabPreco()));
		
		tvEstoque.setText(" "+itemVO.getEstoque());
		if(itemVO.getEstoque() <= 0){
			tvEstoque.setTextColor(view.getResources().getColor(R.color.red));
			((TextView)view.findViewById(R.id.tvEstoque)).setTextColor(view.getResources().getColor(R.color.red));
		}
		if(mostarPlus){
			ImageView adicionado =(ImageView)view.findViewById(R.id.imgViewAdicionar);
			if(lsItensIdsAdicionados.contains(itemVO.getIdItem())){
				adicionado.setImageResource(R.drawable.check);
				itemVO.setAdicionadoCesta(true);
			} else {
				adicionado.setImageResource(R.drawable.plus);
			}
		}
		/*if(itemVO.isAdicionadoCesta()){
			ImageView adicionado =(ImageView)view.findViewById(R.id.imgViewAdicionar);		
			adicionado.setImageResource(R.drawable.check);
		}*/

		return view;
	}

}
