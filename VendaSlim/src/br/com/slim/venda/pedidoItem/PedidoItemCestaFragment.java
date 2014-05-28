package br.com.slim.venda.pedidoItem;

import java.util.ArrayList;
import java.util.List;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.TextView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import br.com.slim.venda.R;
import br.com.slim.venda.pedido.PedidoPgtoFragments;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.util.Util;


@SuppressLint("ValidFragment")
public class PedidoItemCestaFragment extends Fragment {
	
	PedidoItemCestaAdapter pedidoItemCestaAdapter;
	//ArrayList<PedidoItemVO> lsPedidoItemVO;
	View view;
	ListView listView;
	ArrayList<Integer> lsItensIdsAdicionados;
	TextView tvValorTotal;
	TextView tvDescontoTotalEd;
	TextView tvDescontoTotal;
	PedidoVO pedidoVO;
	
	public PedidoItemCestaFragment() {
	}	
	
	public PedidoItemCestaFragment(PedidoVO pedidoVO,  ArrayList<Integer> lsItensIdsAdicionados) {
		this.pedidoVO = pedidoVO;
		this.lsItensIdsAdicionados = lsItensIdsAdicionados;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved)
	{		
		
		Bundle bundle = this.getArguments();
		this.lsItensIdsAdicionados = bundle.getIntegerArrayList("ITEMS_ADICIONADOS");
		this.pedidoVO = bundle.getParcelable("PEDIDO");
		
		if(pedidoVO == null)
			pedidoVO = new PedidoVO();
		
		view = inflater.inflate(R.layout.pedido_item_cesta_activity, group, false);
		tvValorTotal = (TextView) view.findViewById(R.id.tvValorTotal);
		tvDescontoTotalEd = (TextView) view.findViewById(R.id.tvDescontoTotal);
		tvDescontoTotal = (TextView) view.findViewById(R.id.tvTotalDesc);
		
		listView = (ListView) view.findViewById(R.id.listItemCesta);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setOnItemClickListener(new ListViewListner());
		createListView();
		somaValorTotal();
		return view;
	}
	
	public void updateFragment(){		
		createListView();
		somaValorTotal();
	}

	private void createListView(){
		/*PedidoItemCestaAdapter */pedidoItemCestaAdapter = new PedidoItemCestaAdapter(view.getContext(), pedidoVO.getPedidoItemVO());
		listView.setAdapter(pedidoItemCestaAdapter);
	}
	
	public void somaValorTotal(){
		double valorTotal = 0.0;
		double descontosTotal = 0.0;
		for(PedidoItemVO pedidoItemVO : pedidoVO.getPedidoItemVO()){
			descontosTotal += pedidoItemVO.getDesconto()*pedidoItemVO.getQuantidade();
			valorTotal += pedidoItemVO.getPrecoVenda()*pedidoItemVO.getQuantidade();
		}
		setDescontoTotal(descontosTotal);
		tvValorTotal.setText(Util.arredondaDoubleToString(valorTotal));
	}
	
	private void setDescontoTotal(double valor){
		if(valor < 0){
			tvDescontoTotal.setText("Acrescimo Total R$:");
			
			tvDescontoTotalEd.setTextColor(view.getResources().getColor(R.color.green));
			tvDescontoTotalEd.setText(Util.arredondaDoubleToString(valor*(-1)));
			
		} else {
			tvDescontoTotal.setText("Desconto Total R$:");
			tvDescontoTotalEd.setTextColor(view.getResources().getColor(R.color.red));
			tvDescontoTotalEd.setText(Util.arredondaDoubleToString(valor));
		}
	}
	
	
	/*@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		PedidoItemVO pedidoItemVO = pedidoVO.getPedidoItemVO().get(position);
		mostraDialog(pedidoItemVO);
	}*/
	
	private void mostraDialog(PedidoItemVO pedidoItemVO, PedidoItemFragment pedidoItemFragment, PedidoPgtoFragments pedidoPgtoFragment ){
		final PedidoItemCestaDialog dialog = new PedidoItemCestaDialog(view.getContext(),pedidoItemVO, pedidoVO,pedidoItemCestaAdapter, lsItensIdsAdicionados, pedidoItemFragment, pedidoPgtoFragment, this);
		dialog.show(getActivity().getSupportFragmentManager(), "Tag");
	}	
	
	private class ListViewListner implements android.widget.AdapterView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {
			
			PedidoItemFragment pedidoItemFragment = getPedidoItemFragment();
			PedidoPgtoFragments pedidoPgtoFragment = getPedidoPgtoFragment();
			PedidoItemVO pedidoItemVO = pedidoVO.getPedidoItemVO().get(position);
			mostraDialog(pedidoItemVO, pedidoItemFragment, pedidoPgtoFragment);
			
		}
		
	}
	
	private PedidoItemFragment getPedidoItemFragment(){
		List<android.support.v4.app.Fragment> a = (List<android.support.v4.app.Fragment>) getParentFragment().getChildFragmentManager().getFragments();
		for (android.support.v4.app.Fragment fragment : a) {
			if(fragment != null){
				if(fragment.getClass().equals(PedidoItemFragment.class)){
					return (PedidoItemFragment) fragment;					
				}
			}
		}	
		return null;
	}
	
	private PedidoPgtoFragments getPedidoPgtoFragment(){
		List<android.support.v4.app.Fragment> a = (List<android.support.v4.app.Fragment>) getParentFragment().getChildFragmentManager().getFragments();
		for (android.support.v4.app.Fragment fragment : a) {
			if(fragment != null){
				if(fragment.getClass().equals(PedidoPgtoFragments.class)){
					return (PedidoPgtoFragments) fragment;					
				}
			}
		}	
		return null;
	}

}
