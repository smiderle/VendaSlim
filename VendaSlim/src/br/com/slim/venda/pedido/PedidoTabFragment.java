package br.com.slim.venda.pedido;

import java.util.ArrayList;

import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.TabSwipeFragment;
import org.holoeverywhere.widget.Toast;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import br.com.slim.venda.PedidoConcluidoView;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteModel;
import br.com.slim.venda.config.ConfigVO;
import br.com.slim.venda.pedidoItem.PedidoItemCestaFragment;
import br.com.slim.venda.pedidoItem.PedidoItemFragment;
import br.com.slim.venda.pedidoItem.PedidoItemVO;
import br.com.slim.venda.widget.Alert;

@SuppressLint("ValidFragment")
public class PedidoTabFragment extends TabSwipeFragment {

	ArrayList<Integer> lsItensIdsAdicionados = new ArrayList<Integer>();

	PedidoVO pedidoVO = new PedidoVO();
	PedidoModel pedidoModel;
	boolean veioDoHistorico = false;

	public PedidoTabFragment() {}
	
	public PedidoTabFragment(PedidoVO pedidoVO) {
		this.pedidoVO = pedidoVO;
	}
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		
		if(pedidoVO != null){			
			veioDoHistorico = true;
			carregaItensAdicionados();
		} else {
			pedidoVO = new PedidoVO();
		}

		//Pedido Novo
		if(pedidoVO.getIdPedido() == 0){
			PedidoDAO pedidoDAO = new PedidoDAO(getActivity());
			pedidoVO.setIdPedido((int)pedidoDAO.getNextId());
		}
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setHasOptionsMenu(true);
		
		
/*
		this.setOnTabSelectedListener(new OnTabSelectedListener() {
			
			@Override
			public void onTabSelected(int position) {
				Toast.makeText(getActivity(), position+"", Toast.LENGTH_LONG).show();
				getTabAt(position);
			}
		});*/
	}	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {		
		if(!pedidoVO.isSincronizado()){
		menu.clear();	
		if(veioDoHistorico){
			inflater.inflate(R.menu.menu_pedido_excluir, menu);
		}		
		inflater.inflate(R.menu.menu_pedido_salvar, menu);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
        case R.id.menuSave:
			
			String msgCampos =  validateFields();
			if(msgCampos.equals("")){
				if(validaLimiteCredito()){
																			
					final Handler handler = new Handler();
					new Thread() {
						public void run() {
							handler.post(new Runnable() {
								@Override
								public void run() {
									PedidoModel pedidoBO = new PedidoModel(getActivity());
									int inserts = pedidoBO.save(pedidoVO);
									
									if(inserts > 0) {
										Intent it = new Intent(getActivity(), PedidoConcluidoView.class);
										it.putExtra("PEDIDO", pedidoVO);
										startActivity(it);
									}									
									getActivity().finish();
									getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
								}
							});
						}
					}.start();
				}
			} else {				
				new Alert().showDialog(getActivity(), "Campos invalidos", msgCampos, "OK", null);				
			}
            break;
        case R.id.menuExcluir:        
			showDialogConfirmExclusion();
			break;
        
        
        default:
            return super.onOptionsItemSelected(menuItem);
    }
    return true;
}
	
	
	AlertDialog alert;
	private void showDialogConfirmExclusion(){		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Excluir pedido");
		builder.setMessage("Deseja relamente excluir este pedido?");
		builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				alert.dismiss();

			}
		});

		builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				PedidoModel pedidoBO2 = new PedidoModel(getActivity());
				int deletes = pedidoBO2.excluir(pedidoVO);
				if(deletes > 0){
					Toast.makeText(getActivity(), "Pedido excluido com sucesso!", Toast.LENGTH_SHORT).show();
					pedidoVO = new PedidoVO();					
				}
				alert.dismiss();
				getActivity().finish();
				getActivity().overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			}
		});

		alert = builder.create();
		alert.show();		
	}

	
	public String validateFields(){
		StringBuilder sb = new StringBuilder("");

		if(pedidoVO.getClienteVO() == null || pedidoVO.getClienteVO().getIdCliente() == null || pedidoVO.getClienteVO().getIdCliente() == 0){
			sb.append("* Informe um cliente\n\n");
		}
		if(pedidoVO.getPedidoItemVO() == null || pedidoVO.getPedidoItemVO().size() == 0){
			sb.append("* O carrinho esta vazio\n\n");
		}

		if(pedidoVO.getPedidosPgtoVO() == null || pedidoVO.getPedidosPgtoVO().size() == 0 || pedidoVO.getParcelamentoVO().getIdParcelamento() == null || pedidoVO.getParcelamentoVO().getIdParcelamento()== 0){
			sb.append("* Informe uma forma de parcelamento\n");
		}
		return sb.toString();
	}
	
	private boolean validaLimiteCredito(){
		boolean permitir = false;
		ClienteModel clienteModel = new ClienteModel(getActivity());
		if(ConfigVO.acaoVendaTitulosVencidos != 0 && ConfigVO.acaoVendaTitulosVencidos == ConfigVO.PREFERENCES_ACAO_LIBERAR){
			permitir = true;
		} 

		double limiteDisponivel ;
		if(pedidoVO.getDtEmisao() > 0){
			limiteDisponivel = clienteModel.limiteDisponivelMenosPedidoAtual(pedidoVO.getClienteVO(), pedidoVO);
		} else {
			limiteDisponivel = clienteModel.limiteDisponivel(pedidoVO.getClienteVO());			
		}

		if(limiteDisponivel - pedidoVO.getValorTotalPedido() > 0){
			permitir = true;
		} else if(ConfigVO.acaoLimiteCredito != 0 && ConfigVO.acaoLimiteCredito == ConfigVO.PREFERENCES_ACAO_BLOQUEAR){
			new Alert().showDialog(getActivity(), "Venda não permitida", 
					"Cliente não possui limite de crédito suficiente.\n Limite disponivel: R$ "+limiteDisponivel, "OK", null);
			permitir = false;
		} else if(ConfigVO.acaoLimiteCredito != 0 && ConfigVO.acaoLimiteCredito == ConfigVO.PREFERENCES_ACAO_AVISAR){
			/*Toast.makeText(getActivity(), "Limite disponivel: R$ "+
					Util.arredondaDouble(limiteDisponivel - pedidoVO.getValorTotalPedido()), Toast.LENGTH_LONG).show();*/			
			permitir = true;
		}	
		return permitir;
	}


	private void carregaItensAdicionados(){
		for(PedidoItemVO pedidoItemVO : pedidoVO.getPedidoItemVO()){
			lsItensIdsAdicionados.add(pedidoItemVO.getItemVO().getIdItem());
		}

	}



	@Override
	public void onHandleTabs() {
		Bundle bundle = new Bundle();
		bundle.putParcelable("PEDIDO", pedidoVO);
		bundle.putIntegerArrayList("ITEMS_ADICIONADOS", lsItensIdsAdicionados);
				
		addTab("Dados", CabecalhoFragments.class, bundle);
		addTab("Items", PedidoItemFragment.class, bundle);
		addTab("Cesta", PedidoItemCestaFragment.class, bundle);
		addTab("Pagamento", PedidoPgtoFragments.class, bundle);
		
		TextView tv = new TextView(getActivity());
		tv.setText("Dados");
		tv.setTextColor(Color.WHITE);
		tv.setTextSize(12);
		tv.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.dados2	, 0, 0);
		
		
		TextView tv3 = new TextView(getActivity());
		tv3.setText("Carrinho");
		tv3.setTextColor(Color.WHITE);
		tv3.setTextSize(12);
		tv3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.carrinho	, 0, 0);

		TextView tv2 = new TextView(getActivity());
		tv2.setText("Itens");
		tv2.setTextColor(Color.WHITE);
		tv2.setTextSize(12);
		tv2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.product	, 0, 0);

		TextView tv4 = new TextView(getActivity());
		tv4.setText("Pagamento");
		tv4.setTextColor(Color.WHITE);
		tv4.setTextSize(12);
		tv4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.pagamento, 0, 0);

		
		getSupportActionBar().getTabAt(0).setCustomView(tv);
		getSupportActionBar().getTabAt(1).setCustomView(tv2);
		getSupportActionBar().getTabAt(2).setCustomView(tv3);
		getSupportActionBar().getTabAt(3).setCustomView(tv4);
		
	}
}
