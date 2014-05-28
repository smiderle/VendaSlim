package br.com.slim.venda.cargaInicial;

import java.util.ArrayList;

import br.com.slim.venda.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SincronizacaoItensAdapter extends BaseAdapter {
	
	private LayoutInflater minflater;
	private ArrayList<SincronizacaoItensVO> lsSincronizacaoItens;
	
	public SincronizacaoItensAdapter(Context context, ArrayList<SincronizacaoItensVO> lsSincronizacaoItens) {
		this.lsSincronizacaoItens = lsSincronizacaoItens;
		minflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return lsSincronizacaoItens.size();
	}

	@Override
	public Object getItem(int position) {
		return lsSincronizacaoItens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		SincronizacaoItensVO sincronizacao = (SincronizacaoItensVO) getItem(position);
		view = minflater.inflate(R.layout.sincronizacao_item_row, null);
		((TextView) view.findViewById(R.id.tvItem)).setText(sincronizacao.getItemDescricao());
		String  info = sincronizacao.getCount() + " "+ sincronizacao.getItemDescricao() + " recebidos.";
		((TextView) view.findViewById(R.id.tvDescricao)).setText(info);
		ProgressBar pb = (ProgressBar) view.findViewById(R.id.pbSincronizacao);
		
		ImageView imgView =(ImageView)view.findViewById(R.id.imgSincronizado);
		if(sincronizacao.getStatusSincronizacao() == SincronizacaoItensVO.SINCRONIZADO){
			imgView.setImageResource(R.drawable.check);
			imgView.setVisibility(View.VISIBLE);
			pb.setVisibility(View.INVISIBLE);
		} else if(sincronizacao.getStatusSincronizacao() == SincronizacaoItensVO.FALHA){
			imgView.setImageResource(R.drawable.error);
			imgView.setVisibility(View.VISIBLE);
			pb.setVisibility(View.INVISIBLE);
		}  else if(sincronizacao.getStatusSincronizacao() == SincronizacaoItensVO.PENDENTE){
			imgView.setImageResource(R.drawable.error);
			imgView.setVisibility(View.INVISIBLE);
			pb.setVisibility(View.VISIBLE);
		}	
		
		return view;
	}
}
