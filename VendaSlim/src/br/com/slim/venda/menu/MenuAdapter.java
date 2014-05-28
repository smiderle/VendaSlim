package br.com.slim.venda.menu;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.slim.venda.R;


public class MenuAdapter extends BaseAdapter{
	
	private LayoutInflater minflater;
	private ArrayList<ItemListView> itens;
	
	public MenuAdapter(Context context,  ArrayList<ItemListView> itens) {
		this.itens = itens;
		minflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itens.size();
	}

	public ItemListView getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {		
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		ItemListView item = itens.get(position);
		view = minflater.inflate(R.layout.list_row, null);
       ((TextView) view.findViewById(R.id.opcao)).setText(item.getOpcao());
       ((ImageView) view.findViewById(R.id.list_image)).setImageResource(item.getIconeRid());
		return view;
	}
}
