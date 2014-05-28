package br.com.slim.venda.clienteNovo;

import java.util.ArrayList;

import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.ListView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.historico.HistoricoAdapter;
import br.com.slim.venda.pedido.PedidoDAO;
import br.com.slim.venda.pedido.PedidoVO;

@SuppressLint("ValidFragment")
public class ClienteNovoPedidosFragment extends Fragment{
	
	private ListView listView;
	View view;
	ArrayList<PedidoVO> lsPedidoVo;
	ClienteVO clienteVO;
	
	
	public ClienteNovoPedidosFragment() {
		super();
	}
		
	@Override
	public View onCreateView(org.holoeverywhere.LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.cliente_novo_pedidos);
		
		Bundle bundle = this.getArguments();
		this.clienteVO = bundle.getParcelable("CLIENTE");
		
		listView = (ListView) view.findViewById(R.id.listClientePedidos);		
		if(clienteVO != null && clienteVO.getIdCliente() != null){
			carregaPedidos();
			carregaListView();
		}

		return view;


	}
	
	public void carregaListView(){
		HistoricoAdapter historicoAdapter = new HistoricoAdapter(view.getContext(), lsPedidoVo);
		listView.setAdapter(historicoAdapter);
	}
	
	public void carregaPedidos(){
		PedidoDAO pedidoDAO = new PedidoDAO(view.getContext());
		lsPedidoVo = pedidoDAO.getAllByCliente(clienteVO.getIdCliente());
	}

}
