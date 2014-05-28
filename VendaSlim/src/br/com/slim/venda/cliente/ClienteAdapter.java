package br.com.slim.venda.cliente;

import java.util.ArrayList;

import br.com.slim.venda.R;

//import com.actionbarsherlock.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClienteAdapter extends BaseAdapter{
	
	private LayoutInflater minflater;
	private ArrayList<ClienteVO> lsClienteVO;
	
	
	public ClienteAdapter(Context context, ArrayList<ClienteVO> lsClienteVO) {
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
		view = minflater.inflate(R.layout.cliente_row, null) ;
		
		String fone  = "";
		String foneEd  = "";
		String endereco ="";
		if(clienteVO.getFoneComercial() != null && !clienteVO.getFoneComercial().equals("")){
			fone = "Fone Com.: ";
			foneEd  = clienteVO.getFoneComercial() ;
		} else if(clienteVO.getFoneResidencial() != null && !clienteVO.getFoneResidencial().equals("")){
			fone = "Fone Res.: ";
			foneEd  = clienteVO.getFoneResidencial() ;
		} else if(clienteVO.getFoneCelular() != null && !clienteVO.getFoneCelular().equals("")){
			fone = "Celular  : ";
			foneEd  = clienteVO.getFoneCelular();
		}
		
		if(clienteVO.getRua() != null && !clienteVO.getRua().equals("")){
			endereco = clienteVO.getRua();
			if(clienteVO.getNumero() != null && !clienteVO.getNumero().equals("")){
				endereco += " - "+clienteVO.getNumero();
			}else {
				endereco += " - S/N";
			}
		}
		
				
		((TextView)view.findViewById(R.id.tvNome)).setText(clienteVO.getNome());
		((TextView)view.findViewById(R.id.tvCodigoEd)).setText(clienteVO.getIdCliente()+"");
		((TextView)view.findViewById(R.id.tvFoneEd)).setText(foneEd);
		((TextView)view.findViewById(R.id.tvFone)).setText(fone);
		((TextView)view.findViewById(R.id.tvEnderecoEd)).setText(endereco);
		
		
		return view;
	}

}
