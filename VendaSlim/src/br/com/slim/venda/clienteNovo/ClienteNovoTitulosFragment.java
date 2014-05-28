package br.com.slim.venda.clienteNovo;

import java.util.ArrayList;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.ExpandableListView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.titulos.TituloBO;
import br.com.slim.venda.titulos.TituloExpandAdapter;

@SuppressLint("ValidFragment")
public class ClienteNovoTitulosFragment extends Fragment{
	
	ClienteVO clienteVO;
	View view;
	TituloExpandAdapter expandAdapter;
	private ExpandableListView expandList;
	ArrayList<PedidoVO> lsPedidoVO;
	
	public ClienteNovoTitulosFragment() {
		super();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.titulo_expand_list_view);
		
		Bundle bundle = this.getArguments();
		this.clienteVO = bundle.getParcelable("CLIENTE");

		lsPedidoVO = getTodosPedidoVO();
		if(lsPedidoVO != null){
			expandList = (ExpandableListView) view.findViewById(R.id.listaCadastro);
			expandAdapter = new TituloExpandAdapter(getActivity(), lsPedidoVO);
			expandList.setAdapter(expandAdapter);
		}

		return view;
	}
	
	public ArrayList<PedidoVO> getTodosPedidoVO(){
		if(clienteVO != null && clienteVO.getIdCliente() != null && clienteVO.getIdCliente() != 0){
		 TituloBO tituloBO = new TituloBO();
		 return tituloBO.getAllTitulosTodos(getActivity(), clienteVO.getIdCliente());
		} 
		return null;
		 
	 }
	
}
