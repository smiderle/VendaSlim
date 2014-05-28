package br.com.slim.venda.pedidoItem;

import java.util.ArrayList;
import java.util.List;

import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.AdapterView.OnItemSelectedListener;
import org.holoeverywhere.widget.ArrayAdapter;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Spinner;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Toast;
import br.com.slim.venda.R;
import br.com.slim.venda.item.ItemModel;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.itemGrupo.GrupoProdutoDAO;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO;
import br.com.slim.venda.pedido.PedidoVO;
//import android.widget.ListView;
//import android.widget.Spinner;


@SuppressLint("ValidFragment")
public class PedidoItemFragment extends Fragment implements OnItemClickListener{
	

	public PedidoItemFragment() {

	}


	EditText edFiltro;
	Button btnItemGrupo;
	Spinner spFiltroItem;

	ListView listView;
	View view;
	ArrayList<ItemVO> lsItemVO;
	//ArrayList<PedidoItemVO> lsPedidoItemVO;
	ArrayAdapter<GrupoProdutoVO> arrayAdapter;
	PedidoItemAdapter pedidoItemAdapter;
	ArrayList<Integer> lsItensIdsAdicionados;
	PedidoVO pedidoVO;
	@SuppressLint("ValidFragment")
	public PedidoItemFragment(ArrayList<Integer> lsItensIdsAdicionados, PedidoVO pedidoVO) {
		this.lsItensIdsAdicionados = lsItensIdsAdicionados;
		this.pedidoVO = pedidoVO;
	}

	@Override
	public View onCreateView(org.holoeverywhere.LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {


		Bundle bundle = this.getArguments();
		this.lsItensIdsAdicionados = bundle.getIntegerArrayList("ITEMS_ADICIONADOS");
		this.pedidoVO = bundle.getParcelable("PEDIDO");

		if(pedidoVO == null)
			pedidoVO = new PedidoVO();


		view = inflater.inflate(R.layout.pedido_item_activity, container, false);
		edFiltro = (EditText) view.findViewById(R.id.edFiltro);
		btnItemGrupo = (Button) view.findViewById(R.id.spItemGrupo);
		spFiltroItem = (Spinner) view.findViewById(R.id.spFiltroPedidoItem);
		spFiltroItem.setOnItemSelectedListener(new TrocaFiltro());
		//btnTrocaFiltro = (Button) view.findViewById(R.id.btnTrocarFiltro);


		btnItemGrupo.setVisibility(View.INVISIBLE);		
		listView = (ListView) view.findViewById(R.id.listItem);
		//listView.setBackgroundColor(Color.rgb(34,34,34));
		//btnTrocaFiltro.setOnClickListener(new TrocaFiltro());
		edFiltro.addTextChangedListener(new BuscaProdutoKeyListner());
		edFiltro.setSelected(false);
		listView.setOnItemClickListener(this);
		btnItemGrupo.setOnClickListener(new ButtonFilterGroupPressed());
		carregaTodosItens();
		createListView();
		populaSpItemGrupo();
		return view;
	}


	public void carregaTodosItens(){
		ItemModel itemBO = new ItemModel(view.getContext());
		lsItemVO = itemBO.getAll(pedidoVO.getTabelaPrecoVO());
	}

	public void carregaItensByGrupo(Integer idGrupo){
		ItemModel itemBO = new ItemModel(view.getContext());
		lsItemVO.clear();
		lsItemVO = itemBO.getAllByGrupo(idGrupo, pedidoVO.getTabelaPrecoVO());
	}

	public void carregaItensByPrefixo(String prefixo){
		ItemModel itemBO = new ItemModel(view.getContext());
		lsItemVO.clear();
		lsItemVO = itemBO.getAllByPrefixoActive(prefixo, pedidoVO.getTabelaPrecoVO());
	}

	public void populaSpItemGrupo(){
		ArrayList<GrupoProdutoVO> lsItemGrupo = new GrupoProdutoDAO(view.getContext()).getAll();
		arrayAdapter = new ArrayAdapter<GrupoProdutoVO>(view.getContext(), R.layout.simple_list_item_1, lsItemGrupo);		

	}

	@Override
	public void onActivityCreated (Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}

	private void createListView(){
		pedidoItemAdapter = new PedidoItemAdapter(view.getContext(), lsItemVO, lsItensIdsAdicionados, true);
		listView.setAdapter(pedidoItemAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		ItemVO itemVO = lsItemVO.get(position);
		if(itemVO.isAdicionadoCesta()){
			Toast.makeText(view.getContext(), "Item já adicionao  ao carrinho", Toast.LENGTH_LONG).show();
		} else {		
			 		
			PedidoItemCestaFragment pedidoItemCestaFragment = getPedidoItemCestaFragment();
					
			
			mostrarDialogAdd(itemVO, pedidoItemCestaFragment);
		}
	}
	
	private PedidoItemCestaFragment getPedidoItemCestaFragment(){
		List<android.support.v4.app.Fragment> a = (List<android.support.v4.app.Fragment>) getParentFragment().getChildFragmentManager().getFragments();
		for (android.support.v4.app.Fragment fragment : a) {
			if(fragment != null){
				if(fragment.getClass().equals(PedidoItemCestaFragment.class)){
					return (PedidoItemCestaFragment) fragment;					
				}
			}
		}	
		return null;
	}


	private void mostrarDialogAdd(ItemVO itemVO,PedidoItemCestaFragment pedidoItemCestaFragment){ 
		final PedidoItemDialogAdd dialog = new PedidoItemDialogAdd(view.getContext(),itemVO, pedidoVO,pedidoItemAdapter, lsItensIdsAdicionados, pedidoItemCestaFragment);
		dialog.show(getActivity().getSupportFragmentManager(), "bbbb");
		/*final PedidoItemDialogAddTeste teste = new PedidoItemDialogAddTeste();
		teste.show(getActivity().getSupportFragmentManager(), "");*/
	}

	private final class TrocaFiltro  implements OnItemSelectedListener{
		@Override
		public void onItemSelected(
				org.holoeverywhere.widget.AdapterView<?> parent, View view,
				int position, long id) {
			if(position == 0){
				btnItemGrupo.setVisibility(View.INVISIBLE);
				edFiltro.setVisibility(View.VISIBLE);
				edFiltro.setText("");
			} else {
				btnItemGrupo.setVisibility(View.VISIBLE);
				edFiltro.setVisibility(View.INVISIBLE);

			}
		}

		@Override
		public void onNothingSelected(
				org.holoeverywhere.widget.AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	}

	/*private final class TrocaFiltro implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			if(spItemGrupo.getVisibility() == View.VISIBLE){
				spItemGrupo.setVisibility(View.INVISIBLE);
				edFiltro.setVisibility(View.VISIBLE);
				edFiltro.setText("");
				//btnTrocaFiltro.setText("Por Descrição");
			} else {
				spItemGrupo.setVisibility(View.VISIBLE);
				edFiltro.setVisibility(View.INVISIBLE);
				spItemGrupo.setSelection(0);
				//btnTrocaFiltro.setText("Por Grupo");
			}

			carregaTodosItens();
			createListView();
		}		
	}*/

	private final class BuscaProdutoKeyListner implements TextWatcher{		

		@Override
		public void afterTextChanged(Editable s) {
			if(s != null && s.toString().length() >=1){
				carregaItensByPrefixo(s.toString());
				createListView();				
				//pedidoItemAdapter.notifyDataSetChanged();
			} else if(s != null && s.toString().length() ==0){
				carregaTodosItens();
				createListView();
			}

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			/*if((after < count && after == 2) || (start == 2 && count == 1)){
				carregaTodosItens();
				createListView();
			}*/

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {}

	}



	private final class ButtonFilterGroupPressed implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			showOptionsGroupFilter();
		}
	}

	/**
	 * Carregar produtos por grupo
	 */
	private void loadingProductsByGroup(GrupoProdutoVO grupoProduto){
		btnItemGrupo.setText(grupoProduto.getDescricao());
		carregaItensByGrupo(grupoProduto.getIdGrupo());
		createListView();
	}


	private void showOptionsGroupFilter(){
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(view.getContext());	
		builderSingle.setTitle("Selecione um grupo");
		builderSingle.setNegativeButton("Cancelar", new DialogFilterGroupCancelClickListner());
		builderSingle.setInverseBackgroundForced(true);

		builderSingle.setAdapter(arrayAdapter, new DialogFilterGroupClickListner());
		builderSingle.show();

	}

	/**
	 * Quando cancelar o dialog do grupo
	 * @author LADAIR
	 *
	 */
	private class DialogFilterGroupCancelClickListner implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	}



	/**
	 * Quando for selecionado um grupo
	 * @author LADAIR
	 *
	 */
	private class DialogFilterGroupClickListner implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			GrupoProdutoVO grupoProdutoVO =  arrayAdapter.getItem(which);
			loadingProductsByGroup(grupoProdutoVO);
		}

	};
	
	
	public void updateFragment(){
		carregaTodosItens();
		createListView();
	}

}


